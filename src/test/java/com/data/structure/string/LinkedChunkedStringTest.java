package com.data.structure.string;

import com.data.structure.string.impl.LinkedChunkedStringImpl;
import org.junit.Before;
import org.junit.Test;

public class LinkedChunkedStringTest {
    LinkedChunkedStringImpl linked;
    @Before
    public void testInit(){
        char[] chars = {'a','g','f','s','d','e'};
        linked = new LinkedChunkedStringImpl(chars);
        linked.strLength();
    }
    @Test
    public void strCopy() throws CloneNotSupportedException {
        LinkedChunkedStringImpl linkedChunkedString = new LinkedChunkedStringImpl();
        linkedChunkedString.strCopy(linked);
    }
    @Test
    public void insert() throws CloneNotSupportedException {
        char[] chars = {'1','2','3','s','c'};
        LinkedChunkedStringImpl linkedChunkedString = new LinkedChunkedStringImpl(chars);
        linked.strInsert(7,linkedChunkedString);
        System.out.println(linked.strLength());
    }
    @Test
    public void concat(){
        LinkedChunkedStringImpl concat = linked.subString(1,5);
        System.out.println(concat.strLength());
    }
    @Test
    public void delete() throws CloneNotSupportedException {
        linked.strDelete(6, 1);
        linked.strLength();
    }
}
