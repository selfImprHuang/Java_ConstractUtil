/*
 * Copyright (c) 2020.
 * made by 志军
 */

package handler;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.collections4.CollectionUtils;
import util.CollectionUtil;

import java.util.Collection;

/**
 * 处理Collection类型的对比，必须重写equals和hashCode方法
 *
 * @author 志军
 */
public class CollectionHandler {

    /**
     * 对比组合Collection差异的部分
     *
     * @param originChange 原始的变化部分的对象
     * @param targetChange 修改后变化部分的对象
     * @param originValue  原始的对象
     * @param targetValue  修改后的对象
     * @param methodAccess MethodAccess
     * @param setName      set方法名
     * @return 是否变化
     * @throws IllegalAccessException 异常
     * @throws InstantiationException 异常
     */
    public static Boolean contract(Object originChange, Object targetChange, Object originValue, Object targetValue,
                                   MethodAccess methodAccess, String setName) throws IllegalAccessException, InstantiationException {

        boolean changed;
        Class returnValue = originValue.getClass();

        //判断Collection是否相等
        if (CollectionUtils.isEqualCollection((Collection) originValue, (Collection) targetValue)) {
            return false;
        }

        Object originCollection = CollectionUtil.buildDiffCollection(originValue, targetValue, returnValue);
        Object changeCollection = CollectionUtil.buildDiffCollection(targetValue, originValue, returnValue);

        changed = CollectionUtils.isNotEmpty((Collection) originCollection) || CollectionUtils.isNotEmpty((Collection) changeCollection);

        if (changed) {
            methodAccess.invoke(originChange, setName, originCollection);
            methodAccess.invoke(targetChange, setName, changeCollection);
        }

        return changed;
    }
}
