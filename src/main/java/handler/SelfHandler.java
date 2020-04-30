/*
 * Copyright (c) 2020.
 * made by 志军
 */

package handler;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * 处理自定义的类,必须重写hashCode和equals方法，不然会出现对比偏差.<p>
 * @author 志军
 */
public class SelfHandler {
    public static Boolean contract(Object originChange, Object targetChange, Object originValue, Object targetValue,
                                   MethodAccess methodAccess, String setName) {
        if (!originChange.equals(targetChange)) {
            methodAccess.invoke(originChange, setName, originValue);
            methodAccess.invoke(targetChange, setName, targetValue);
            return true;
        }

        return false;
    }
}
