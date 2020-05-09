/*
 * Copyright (c) 2020.
 * made by 志军
 */

package util;

import java.util.Collection;

/**
 * Collection类型的处理工具
 *
 * @author 志军
 */
public class CollectionUtil {


    @SuppressWarnings("unchecked")
    public static Collection buildDiffCollection(Object originValue, Object targetValue, Class returnValue) throws IllegalAccessException,
            InstantiationException {
        //数组为空的判断这边需要考虑的只有一个为空另一个不为空，因为在外面已经进行了其他两种情况的处理了
        if (null == targetValue) {
            return (Collection) originValue;
        }
        if (null == originValue) {
            return null;
        }

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
