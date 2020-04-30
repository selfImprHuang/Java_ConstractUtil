package util;

import com.google.common.collect.Lists;
import exception.NoGetMethodException;
import exception.NoSetMethodException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Vector;

import static constant.Constant.GET;
import static constant.Constant.SET;

/**
 * @author 志军
 * @date 2020-1-5 16:00:00
 */
public class CheckUtil {

    private static Vector<String> vector = new Vector<>();


    /**
     * 提前检查是否包含了get、set方法
     * TODO 没有考虑is方法
     *
     * @param zClass Class类
     */
    public static void checkGetAndSetMethod(Class zClass) {
        if (vector.contains(zClass.getName())) {
            return;
        }
        List<Field> fieldList = ClassUtil.getAllFieldWithSuper(zClass);
        List<Method> methodList = ClassUtil.getAllMethodWithSuper(zClass);
        List<String> methodNameList = getMethodNameList(methodList);
        for (Field field : fieldList) {
            String fieldName = field.getName();
            final String s = String.valueOf(fieldName.charAt(0)).toUpperCase();
            String setMethodName = SET + s + fieldName.substring(1);
            String getMethodName = GET + s + fieldName.substring(1);
            if (!methodNameList.contains(getMethodName)) {
                throw new NoGetMethodException();
            }
            //这边加上了set的参数校验
            if (!methodNameList.contains(setMethodName) || !setMethodParamCheck(field, setMethodName, methodList)) {
                throw new NoSetMethodException();
            }
        }
        vector.add(zClass.getName());
    }

    private static boolean setMethodParamCheck(Field field, String setMethodName, List<Method> methodList) {
        for (Method method : methodList) {
            if (method.getName().equals(setMethodName) && method.getParameterTypes()[0] == field.getType()) {
                return true;
            }
        }
        return false;
    }

    private static List<String> getMethodNameList(List<Method> methodList) {
        List<String> methodNameList = Lists.newArrayList();
        for (Method method : methodList) {
            methodNameList.add(method.getName());
        }
        return methodNameList;
    }

    /**
     * 检查是否为抽象类或者接口
     */
    public static boolean commonCheck(Class<?> aClass) {
        return !Modifier.isAbstract(aClass.getModifiers()) && !Modifier.isInterface(aClass.getModifiers());
    }

    public static <T> boolean CheckType(T origin, T target) {
        return origin.getClass() == target.getClass();
    }
}
