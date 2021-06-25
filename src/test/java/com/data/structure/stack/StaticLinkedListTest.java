package com.data.structure.stack;

import com.data.structure.list.impl.StaticLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StaticLinkedListTest {
    StaticLinkedList staticLinkedList;
    StaticLinkedList staticLinkedList2;
    @Before
    public void init(){
        staticLinkedList = new StaticLinkedList();
        staticLinkedList.listInsert(1,2);
        staticLinkedList.listInsert(2,3);
        staticLinkedList.listInsert(3,4);
        staticLinkedList.listInsert(4,5);
        staticLinkedList2.listInsert(4,6);
        staticLinkedList2.listInsert(6,"h");
        staticLinkedList2.listInsert(7,"e");
        staticLinkedList2.listInsert(8,"n");
    }
    @Test
    public void insert(){
        staticLinkedList.listTraverse();
    }

    @Test
    public void delete(){
        staticLinkedList.listDelete(5);
        staticLinkedList.listTraverse();
    }

    @Test
    public void otherTest(){
        Object u = staticLinkedList.priorElem("u");
        Assert.assertEquals(u, "s");
        Object n = staticLinkedList.nextElem("n");
        Assert.assertEquals(n, "c");
    }
}
