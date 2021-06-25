package com.data.basics.design.strategy;

/**
 * StrategyTest:
 * java.util.comparator compare  java.lang.comparator compareTo
 *
 * @author sunchen
 * @date 2020/7/7 6:27 下午
 */
public class StrategyTest<T> {
    /**
     * 如果你想用猫 狗 给对象进行排序呢？
     *
     * @param
     */
    public void sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            T temp;
            boolean change = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Cat[] array = new Cat[]{new Cat(5, 1), new Cat(4,3), new Cat(6,2)};
        CatAgeComparator comparator = new CatAgeComparator();
        StrategyTest<Cat> strategyTest = new StrategyTest<Cat>();
        strategyTest.sort(array, comparator);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
