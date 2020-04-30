/*
 * Copyright (c) 2020.
 * made by 志军
 */

package handler;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * 处理除Iterable和Map之外的原始类型
 *
 * @author 志军
 */
public class OriginHandler {


    public static Boolean contract(Object originChange, Object targetChange, Object originValue, Object targetValue,
                                   MethodAccess methodAccess, String setName) {
        Class returnValue = originValue == null ? targetValue.getClass() : originValue.getClass();

        try {
            if (returnValue.getClassLoader() == null) {
                return contractValue(originChange, targetChange, originValue, targetValue, methodAccess, setName);
            } else {
                //用来判断是否进行了比较
                return null;
            }
        } catch (NullPointerException ex) {
            //这边是处理Object类型
            return contractValue(originChange, targetChange, originValue, targetValue, methodAccess, setName);
        }
    }


    private static boolean contractValue(Object originChange, Object targetChange, Object originValue, Object targetValue,
                                         MethodAccess methodAccess, String setName) {
        boolean result = (originValue == null ? targetValue.equals(originChange) : originValue.equals(targetValue));
        if (originValue == targetValue || result) {
            return false;
        } else {
            methodAccess.invoke(originChange, setName, originValue);
            methodAccess.invoke(targetChange, setName, targetValue);
            return true;
        }
    }
}
