import bean.ContrastObject;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.Lists;
import exception.NoSetMethodException;
import exception.TypeMisMatchException;
import handler.CollectionHandler;
import handler.MapHandler;
import handler.OriginHandler;
import handler.SelfHandler;
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
 * @date 2020-1-5 16:00:00
 */
public class ContrastUtil {


    //get方法的缓存Map
    private static ConcurrentHashMap<String, List<String>> getMethodCache = new ConcurrentHashMap<String, List<String>>();

    //set方法的缓存map
    private static ConcurrentHashMap<String, List<String>> setMethodCache = new ConcurrentHashMap<String, List<String>>();

    /**
     * 单对象对比
     *
     * @param origin 原始对象
     * @param target 修改后的对象
     * @param <T>    对象类型
     * @return 对比结果
     */
    @SuppressWarnings("unchecked")
    public static <T> ContrastObject<T> contrast(T origin, T target) throws IllegalAccessException, InstantiationException {
        //空的情况的判断
        if (origin == null || target == null) {
            return nullProcessor(origin, target);
        }
        //提前检查 - 接口或者抽象类
        if (!CheckUtil.commonCheck(origin.getClass())) {
            return null;
        }
        //检查类型是否一致
        if (!CheckUtil.CheckType(origin, target)) {
            throw new TypeMisMatchException("类型不匹配");
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

    private static <T> ContrastObject<T> nullProcessor(T origin, T target) {
        if (origin == null && target == null) {
            return null;
        }

        if (origin == null) {
            return setContrastObject(null, target, null, target);
        }

        return setContrastObject(origin, null, origin, null);
    }

    private static <T> boolean forContrast(List<String> getList, MethodAccess methodAccess, T origin, T target, List<String> setList,
                                           T originChange, T targetChange) throws InstantiationException,
            IllegalAccessException {

        boolean changed = false;
        for (String methodName : getList) {
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
            changed = changed || contrastValue(originValue, targetValue, originChange, targetChange, methodAccess, setName);
        }

        return changed;
    }

    private static <T> ContrastObject<T> setContrastObject(T origin, T target, T originChange, T targetChange) {
        ContrastObject<T> contrastObject = new ContrastObject<>();
        contrastObject.setOrigin(origin);
        contrastObject.setTarget(target);
        contrastObject.setOriginChange(originChange);
        contrastObject.setTargetChange(targetChange);
        return contrastObject;
    }

    /**
     * @param originValue  get方法拿到的原始的值
     * @param targetValue  get方法拿到的变化的值
     * @param originChange 新创建的原始变化对象
     * @param targetChange 新创建的改变变化对象
     * @param methodAccess reflectASM类
     * @param setName      set方法名
     * @throws IllegalAccessException 异常
     * @throws InstantiationException 异常
     */
    private static boolean contrastValue(Object originValue, Object targetValue, Object originChange, Object targetChange,
                                         MethodAccess methodAccess, String setName) throws IllegalAccessException, InstantiationException {
        //处理Collection
        if (originValue instanceof Collection || targetValue instanceof Collection) {
            return CollectionHandler.contract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        }

        //Map的处理
        if (originValue instanceof Map || targetValue instanceof Map) {
            return MapHandler.putValue(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        }

        //非自定义类的处理
        Boolean result = OriginHandler.contract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        if (null != result) {
            return result;
        }

        //自定义类型的处理
        return SelfHandler.contract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
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
}
