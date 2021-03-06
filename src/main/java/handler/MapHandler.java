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

        //这边也只处理只有一个map可能为空的情况，全部为空在前面的方法做了处理
        if (SelfHandler.isOneNull(originChange, originChange, targetValue, originValue, methodAccess, setName)) {
            return true;
        }
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
                boolean hasChange;
                if (tEntry.getKey().equals(key)) {
                    //每一个key都进行比较,如果key相等则比较对应的value，这个value需要重写hashcode和equals方法
                    hasChange = MapHandler.putValue(entry.getValue(), tEntry.getValue(), originMap, targetMap, key);
                    changed = changed || hasChange;
                    break;
                }
            }
        }

        //得到不一致的key的集合.
        List<Map.Entry> originList = SetUtil.findUnLikeValue(originEntry, targetEntry);
        List<Map.Entry> changeList = SetUtil.findUnLikeValue(originEntry, targetEntry);
        //计算changed的值
        changed = changed || !CollectionUtils.isEmpty(originList) || !CollectionUtils.isEmpty(changeList);
        //把不一致的值设置会新的Map中
        MapUtil.putIfNotEmpty(originMap, originList);
        MapUtil.putIfNotEmpty(targetMap, changeList);
        //对对象进行设值
        methodAccess.invoke(originChange, setName, originValue);
        methodAccess.invoke(targetChange, setName, targetValue);
        return changed;
    }

    private static boolean putValue(Object value, Object tValue, Map<Object, Object> originMap,
                                    Map<Object, Object> targetMap, Object key) {
        if (value.equals(tValue)) {
            return false;
        }
        originMap.put(key, value);
        targetMap.put(key, tValue);
        return true;
    }
}
