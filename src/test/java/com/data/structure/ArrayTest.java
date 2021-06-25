package com.data.structure;

import com.data.structure.array.impl.ArrayImpl;
import org.junit.Test;

public class ArrayTest {
    @Test
    public void init()  {
        ArrayImpl array = new ArrayImpl(1,2,3);
        array.value(0,0,2);
        array.assign(3,0,0,2);
        Object dd = array.value(0,0,2);
        System.out.println(dd);
    }
}
