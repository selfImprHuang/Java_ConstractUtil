/*
 * Copyright (c) 2020.
 * made by 志军
 */

package util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

/**
 * Set工具类
 * @author 志军
 */
public class SetUtil {

    /**
     *
     * @param origin 原始Set
     * @param target 目标Set
     * @param <T> Set类型
     * @return 返回origin中不被包含在target的元素集合
     */
    public static <T> List<T> findUnLikeValue(Set<T> origin, Set<T> target) {
        List<T> unLikeList = Lists.newArrayList();
        for (T t : origin) {
            if (target.contains(t)) {
                continue;
            }
            unLikeList.add(t);
        }

        return unLikeList;
    }
}
