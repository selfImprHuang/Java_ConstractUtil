/*
 * Copyright (c) 2020.
 * made by 志军
 */

package handler;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * 处理自定义的类,必须重写hashCode和equals方法，不然会出现对比偏差.<p>
 *
 * @author 志军
 */
public class SelfHandler {
    public static Boolean contract(Object originChange, Object targetChange, Object originValue, Object targetValue,
                                   MethodAccess methodAccess, String setName) {
        //两个属性都为空情况前面已经处理过了
        if (isOneNull(originChange, targetChange, originValue, targetValue, methodAccess, setName)) {
            return true;
        }
        if (!originValue.equals(targetValue)) {
            methodAccess.invoke(originChange, setName, originValue);
            methodAccess.invoke(targetChange, setName, targetValue);
            return true;
        }

        return false;
    }

    static boolean isOneNull(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) {
        if (originValue == null) {
            methodAccess.invoke(targetChange, setName, targetValue);
            return true;
        } else if (targetValue == null) {
            methodAccess.invoke(originChange, setName, originValue);
            return true;
        }
        return false;
    }
}
