package util;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static constant.Constant.*;

/**
 * @author 志军
 * @date 2020-1-5 16:00:00
 * 其实你不用担心类似 ##<code>fieldCache.get(zClass.getName())</code>这样的代码在一个方法里面写了很多遍会处理多次
 * <p>可以了解一下##方法内联
 */
public class ClassUtil {

    /**
     * 类名#方法 - 方法索引 缓存
     */
    private static ConcurrentHashMap<String, Integer> cache = new ConcurrentHashMap<String, Integer>();

    /**
     * 类 - Field数组 缓存
     */
    private static ConcurrentHashMap<String, List<Field>> fieldCache = new ConcurrentHashMap<String, List<Field>>();

    /**
     * 类 - method数组 缓存
     */
    private static ConcurrentHashMap<String, List<Method>> methodCache = new ConcurrentHashMap<String, List<Method>>();


    /**
     * 获取get方法
     *
     * @param fieldName 类属性名称
     * @param zClass    类.class
     * @return GetMethod
     */
    public static Integer getGetMethod(String fieldName, Class zClass) {
        //获取下标的方式非常方便，本质上，如果使用getDeclareMethod方法，还需要考虑继承的问题
        MethodAccess access = MethodAccess.get(zClass);
        String getMethodName = GET + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
        String keyName = zClass.getName() + WELL_NUMBER + getMethodName;
        if (cache.get(keyName) == null) {
            cache.put(keyName, access.getIndex(getMethodName));
            return access.getIndex(getMethodName);
        }

        return cache.get(keyName);
    }


    /**
     * 获取set方法
     *
     * @param fieldName 类属性名称
     * @param zClass    类.class
     * @return SetMethod
     */
    public static Integer getSetMethod(String fieldName, Class zClass) {
        String setMethodName = SET + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
        String keyName = zClass.getName() + WELL_NUMBER + setMethodName;
        MethodAccess access = MethodAccess.get(zClass);
        if (cache.get(keyName) != null) {
            return cache.get(keyName);
        }
        cache.put(keyName, access.getIndex(setMethodName));
        return access.getIndex(setMethodName);
    }


    static List<Field> getAllFieldWithSuper(Class zClass) {
        if (fieldCache.get(zClass.getName()) != null) {
            return fieldCache.get(zClass.getName());
        }


        List<Field> fieldList = Lists.newArrayList();
        Class tempClass = zClass;
        while (tempClass != null) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }

        fieldCache.put(zClass.getName(), fieldList);
        return fieldList;
    }


    public static List<Method> getAllMethodWithSuper(Class zClass) {
        if (methodCache.get(zClass.getName()) != null) {
            return methodCache.get(zClass.getName());
        }

        Class tempClass = zClass;
        List<Method> methodList = Lists.newArrayList();
        while (tempClass != null) {
            methodList.addAll(Arrays.asList(tempClass.getDeclaredMethods()));
            tempClass = tempClass.getSuperclass();
        }

        methodCache.put(zClass.getName(), methodList);
        return methodList;
    }

}
