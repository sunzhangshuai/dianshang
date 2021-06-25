package com.data.basics.design.strategy;

/**
 * Comparator:
 *
 * @author sunchen
 * @date 2020/7/9 1:11 下午
 */
public interface Comparator<T> {
    /**
     * 比较两个数
     * @param o1
     * @param o2
     * @return
     */
    int compare(T o1, T o2);
}
