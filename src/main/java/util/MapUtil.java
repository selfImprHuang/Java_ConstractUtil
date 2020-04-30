/*
 * Copyright (c) 2020.
 * made by 志军
 */

package util;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Map工具类
 * @author 志军
 */
public class MapUtil {

    /**
     * 把List中Entry对应的key、value设置到新的Map中
     * @param map 新的Map
     * @param changeList 变化的List
     */
    public static void putIfNotEmpty(Map<Object, Object> map, List<Map.Entry> changeList) {
        if (CollectionUtils.isEmpty(changeList)) {
            return;
        }


        for (Map.Entry entry : changeList) {
            map.put(entry.getKey(), entry.getValue());
        }
    }
}
