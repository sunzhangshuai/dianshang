package com.data.structure.searchtable.staticsearchtable.impl;

import com.data.structure.searchtable.model.Element;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * OrderStaticSearchTable:
 * 有序表
 *
 * @author sunchen
 * @date 2020/6/21 1:51 下午
 */
public class OrderStaticSearchTableImpl extends AbstractStaticSearchTable<Element> {

    /**
     * 斐波那契数组
     */
    int[] fibonacci;

    /**
     * 用于求mid的斐波那契数组下标
     */
    int maxFibonacci = 0;

    /**
     * 用于存记录的key值和补全的key值（如果记录的总条数小于斐波那契计算出的长度，则用记录的最后一个key补全）
     */
    Integer[] keys;

    public OrderStaticSearchTableImpl(Element[] data) {
        length = data.length;
        elementData = new Element[length + 1];
        elementData[0] = new Element();
        System.arraycopy(data, 0, elementData, 1, length);
        sort();
        initFibonacci();
        initKeys();
    }

    /**
     * 折半查找
     *
     * @param key 关键字
     * @return
     */
    @Override
    public Element search(Object key) {
        int low = 1;
        int high = length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (elementData[mid].equalKey(key) == 0) {
                return elementData[mid];
            } else if (elementData[mid].equalKey(key) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    /**
     * 斐波那契查找
     *
     * @param key 关键字
     * @return
     */
    public Element fibonacciSearch(Integer key) {
        int fibonacciKey = maxFibonacci - 1;
        int low = 1;
        int high = length;
        while (low <= high) {
            //计算mid
            int mid = low + fibonacci[fibonacciKey] - 1;
            if (keys[mid].compareTo(key) > 0) {
                high = high - 1;
                fibonacciKey = fibonacciKey - 1;
            } else if (keys[mid].compareTo(key) < 0) {
                low = low + 1;
                fibonacciKey = fibonacciKey - 2;
            } else {
                if (mid > length) {
                    return elementData[length];
                } else {
                    return elementData[mid];
                }
            }
        }
        throw new RuntimeException("没有找到:" + key);
    }

    /**
     * 格式化斐波那契数组
     */
    private void initFibonacci() {
        fibonacci = new int[length];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        int i = 2;
        while (fibonacci[i - 1] <= length) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            i++;
        }
        maxFibonacci = i - 1;
    }

    /**
     * 格式化keys
     */
    public void initKeys() {
        keys = new Integer[fibonacci[maxFibonacci]];
        for (int i = 1; i < fibonacci[maxFibonacci]; i++) {
            if (i <= length) {
                keys[i] = elementData[i].intKey;
            } else {
                keys[i] = elementData[length].intKey;
            }
        }

    }

    /**
     * 插值查找
     *
     * @param key 关键字
     * @return
     */
    public Element interpolationSearch(Integer key) {
        int low = 1;
        int high = length;
        while (low <= high) {
            int mid = getMiddle(low, high, key);
            if (elementData[mid].intKey.compareTo(key) == 0) {
                return elementData[mid];
            } else if (elementData[mid].intKey.compareTo(key) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        throw new RuntimeException("没有找到:" + key);
    }

    public int getMiddle(int low, int high, Integer key) {
        int length = high - low + 1;
        if (low == high) {
            return low;
        }
        BigDecimal subtract = BigDecimal.valueOf(Float.parseFloat(String.valueOf(key))).subtract(BigDecimal.valueOf(elementData[low].douKey));
        BigDecimal subtract1 = BigDecimal.valueOf(elementData[high].douKey).subtract(BigDecimal.valueOf(elementData[low].douKey));
        BigDecimal divide = subtract1.divide(new BigDecimal(length), RoundingMode.HALF_UP);
        BigDecimal result = subtract.divide(divide, RoundingMode.HALF_UP);
        int mid = low + result.intValue();
        if (mid < low) {
            mid = low;
        }
        if (mid > high) {
            mid = high;
        }
        return mid;
    }
}
