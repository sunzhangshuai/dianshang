package com.data.basics.collection.set;

import com.data.basics.constructor.Son;
import java.util.Comparator;

/**
 * SonComparator:
 *
 * @author sunchen
 * @date 2020/6/17 6:21 下午
 */
public class SonComparator implements Comparator<Son> {
    @Override
    public int compare(Son o1, Son o2) {
        return o2.getA() - o1.getA();
    }
}
