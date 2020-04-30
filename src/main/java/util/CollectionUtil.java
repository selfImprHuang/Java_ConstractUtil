/*
 * Copyright (c) 2020.
 * made by 志军
 */

package util;

import java.util.Collection;

/**
 * Collection类型的处理工具
 * @author 志军
 */
public class CollectionUtil {


    @SuppressWarnings("unchecked")
    public static Collection buildDiffCollection(Object originValue, Object targetValue, Class returnValue) throws IllegalAccessException,
        InstantiationException {

        Object collection = returnValue.newInstance();

        for (Object value : (Collection) originValue) {
            if (((Collection) targetValue).contains(value)) {
                continue;
            }
            ((Collection) collection).add(value);
        }

        return (Collection) collection;
    }
}
