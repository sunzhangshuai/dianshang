package com.data.basics.design.strategy;

/**
 * CatWeightComparator:
 *
 * @author sunchen
 * @date 2020/7/9 1:04 下午
 */
public class CatAgeComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.age - o2.age;
    }
}
