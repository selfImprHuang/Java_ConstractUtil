import bean.ContrastObject;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.Lists;
import exception.NoSetMethodException;
import exception.TypeMisMatchException;
import util.CheckUtil;
import util.ClassUtil;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static constant.Constant.GET;
import static constant.Constant.SET;

/**
 * @author 志军
 * @description 简单的对象比较工具的实现示例
 */
public abstract class AbstractContrastUtil<T> {
    /**
     * get方法的缓存Map
     */
    private static ConcurrentHashMap<String, List<String>> getMethodCache = new ConcurrentHashMap<>();

    /**
     * set方法的缓存map
     */
    private static ConcurrentHashMap<String, List<String>> setMethodCache = new ConcurrentHashMap<>();

    /**
     * 基本类型的比较方法
     */
    abstract Boolean originContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName);

    /**
     * 自定义类型的比较方法
     */
    abstract boolean selfContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName);

    /**
     * map类型的比较方法
     */
    abstract boolean mapContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) throws InstantiationException, IllegalAccessException;

    /**
     * 集合类型比较方法
     */
    abstract boolean collectionContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) throws InstantiationException, IllegalAccessException;

    /**
     * 特殊类型的处理规则
     */
    abstract boolean specialContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName);

    /**
     * 判断是不是特殊类型
     */
    abstract boolean isSpecialType(Object originValue, Object targetValue);


    /**
     * 入参都为空的处理
     */
    abstract <T> ContrastObject<T> nullProcessor(T origin, T target);

    /**
     * 入参为基本类型的处理
     */
    abstract <T> ContrastObject<T> originProcessor(T origin, T target);

    /**
     * 组装对象比较出参
     */
    abstract <T> ContrastObject<T> setContrastObject(T origin, T target, T originChange, T targetChange);


    @SuppressWarnings("unchecked")
    public <T> ContrastObject<T> contrast(T origin, T target) throws Exception {
        //空的情况的判断
        if (origin == null || target == null) {
            return nullProcessor(origin, target);
        }
        //提前检查 - 接口或者抽象类
        if (!CheckUtil.commonCheck(origin.getClass())) {
            return null;
        }
        //检查类型是否一致
        if (!CheckUtil.checkType(origin, target)) {
            throw new TypeMisMatchException("类型不匹配");
        }

        //因为可能传入的是基本类型，所以这边会直接去处理一下基本类型
        Class clazz = target.getClass();
        if (clazz.getClassLoader() == null) {
            return originProcessor(origin, target);
        }

        //先检查get、set方法是否存在.
        CheckUtil.checkGetAndSetMethod(origin.getClass());

        //初始所有参数
        Class<T> tClass = (Class<T>) origin.getClass();
        T originChange = tClass.newInstance();
        T targetChange = tClass.newInstance();
        MethodAccess methodAccess = MethodAccess.get(tClass);
        //获取get方法的值进行比较、正常情况下Field都会声明为private，reflectASM对static和private自动跳过，所以用MethodAccess进行处理
        List<String> getList = getGetMethod(methodAccess, tClass);
        List<String> setList = getSetMethod(methodAccess, tClass);

        //循环所有的MethodAccess判断是否发生变化
        boolean changed = forContrast(getList, methodAccess, origin, target, setList, originChange, targetChange);

        if (!changed) {
            return null;
        }

        return setContrastObject(origin, target, originChange, targetChange);
    }


    private <T> boolean forContrast(List<String> getList, MethodAccess methodAccess, T origin, T target, List<String> setList,
                                    T originChange, T targetChange) throws Exception {
        boolean changed = false;
        for (String methodName : getList) {
            boolean nowResult;
            //reflectASM会对基本类型进行装箱(比如说int返回Integer),所以这里要考虑的就是自定义类型怎么比较的问题
            Object originValue = methodAccess.invoke(origin, methodName);
            Object targetValue = methodAccess.invoke(target, methodName);
            String setName = null;
            for (String setMethodName : setList) {
                if (setMethodName.substring(3).equals(methodName.substring(3))) {
                    setName = setMethodName;
                    break;
                }
            }
            if (setName == null) {
                throw new NoSetMethodException();
            }
            if (originValue == null && targetValue == null) {
                continue;
            }
            //对比 + 设值
            nowResult = contrastValue(originValue, targetValue, originChange, targetChange, methodAccess, setName);
            changed = changed || nowResult;
        }

        return changed;
    }


    private boolean contrastValue(Object originValue, Object targetValue, Object originChange, Object targetChange,
                                  MethodAccess methodAccess, String setName) throws Exception {
        //特殊拦截类型的处理
        if (isSpecialType(originValue, targetValue)) {
            return specialContract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        }

        //处理Collection
        if (originValue instanceof Collection || targetValue instanceof Collection) {
            return collectionContract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        }

        //Map的处理
        if (originValue instanceof Map || targetValue instanceof Map) {
            return mapContract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        }

        //非自定义类的处理
        Boolean result = originContract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        if (null != result) {
            return result;
        }

        //自定义类型的处理
        return selfContract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
    }

    /**
     * 获取Set方法集合
     *
     * @param methodAccess MethodAccess
     * @param tClass       类.class
     * @param <T>          T
     * @return 方法名
     */
    private static <T> List<String> getGetMethod(MethodAccess methodAccess, Class<T> tClass) {
        if (getMethodCache.get(tClass.getName()) != null) {
            return getMethodCache.get(tClass.getName());
        }
        List<String> methodNameList = Lists.newArrayList();
        String[] methodNames = methodAccess.getMethodNames();
        List<Method> methods = ClassUtil.getAllMethodWithSuper(tClass);

        return buildMethodList(GET, methodNames, methods, methodNameList, getMethodCache, tClass);
    }


    /**
     * 获取Get方法集合
     *
     * @param methodAccess MethodAccess
     * @param tClass       类.class
     * @param <T>          T
     * @return 方法名
     */
    private static <T> List<String> getSetMethod(MethodAccess methodAccess, Class<T> tClass) {
        if (setMethodCache.get(tClass.getName()) != null) {
            return setMethodCache.get(tClass.getName());
        }
        List<String> methodNameList = Lists.newArrayList();
        List<Method> methods = ClassUtil.getAllMethodWithSuper(tClass);
        String[] methodNames = methodAccess.getMethodNames();

        return buildMethodList(SET, methodNames, methods, methodNameList, setMethodCache, tClass);
    }


    private static <T> List<String> buildMethodList(String type, String[] methodNames, List<Method> methods, List<String> methodNameList,
                                                    ConcurrentHashMap<String, List<String>> methodCache, Class<T> tClass) {
        for (String methodName : methodNames) {
            if (methodName.startsWith(type)) {
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        methodNameList.add(methodName);
                        break;
                    }
                }
            }
        }
        methodCache.put(tClass.getName(), methodNameList);
        return methodNameList;
    }

}
