/*
 * Copyright (c) 2020.
 * made by 志军
 */

package handler;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.collections.CollectionUtils;
import util.MapUtil;
import util.SetUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 处理Map对应的对比
 *
 * @author 志军
 */
public class MapHandler {

    @SuppressWarnings("unchecked")
    public static boolean putValue(Object originChange, Object targetChange, Object originValue, Object targetValue,
                                   MethodAccess methodAccess, String setName) throws IllegalAccessException, InstantiationException {
        //参数前置
        boolean changed = false;
        Set<Map.Entry> originEntry = ((Map) originValue).entrySet();
        Set<Map.Entry> targetEntry = ((Map) targetValue).entrySet();
        Map originMap = (Map) originValue.getClass().newInstance();
        Map targetMap = (Map) targetValue.getClass().newInstance();

        //处理key一样的value比较.
        for (Map.Entry entry : originEntry) {
            Object key = entry.getKey();
            for (Map.Entry tEntry : targetEntry) {
                if (tEntry.getKey().equals(key)) {
                    //每一个key都进行比较,如果key相等则比较对应的value，这个value需要重写hashcode和equals方法
                    changed = changed || MapHandler.putValue(originChange, targetChange, originMap, targetMap, key);
                    break;
                }
            }
        }

        //得到不一致的key的集合.
        List<Map.Entry> originList = SetUtil.findUnLikeValue(originEntry, targetEntry);
        List<Map.Entry> changeList = SetUtil.findUnLikeValue(originEntry, targetEntry);
        //计算changed的值
        changed = changed || !CollectionUtils.isEmpty(originList) || CollectionUtils.isEmpty(changeList);
        //把不一致的值设置会新的Map中
        MapUtil.putIfNotEmpty(originMap, originList);
        MapUtil.putIfNotEmpty(targetMap, changeList);
        //对对象进行设值
        methodAccess.invoke(originChange, setName, originValue);
        methodAccess.invoke(targetChange, setName, targetValue);
        return changed;
    }

    private static boolean putValue(Object originChange, Object targetChange, Map<Object, Object> originMap,
                                    Map<Object, Object> targetMap, Object key) {
        if (originChange.equals(targetChange)) {
            return false;
        }
        originMap.put(key, originChange);
        targetMap.put(key, targetChange);
        return true;
    }
}
