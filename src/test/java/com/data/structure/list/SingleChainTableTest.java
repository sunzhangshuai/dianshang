package com.data.structure.list;

import com.data.structure.list.model.ListNode;
import com.data.structure.list.op.SingleLinkedListExtend;
import org.junit.Before;
import org.junit.Test;

public class SingleChainTableTest {
    SingleLinkedListExtend singleChainTable = new SingleLinkedListExtend();
    @Before
    public void createLinkedList () {
        singleChainTable.insFirst(new ListNode(6));
        singleChainTable.insFirst(new ListNode(4));
        singleChainTable.insFirst(new ListNode(1));
    }
    @Test
    public void deleteRepeated(){
        singleChainTable.deleteRepeated();
        singleChainTable.listTraverse();
    }
    // 单链表测试
    @Test
    public void testMerge () {
        SingleLinkedListExtend listExtend = new SingleLinkedListExtend();
        listExtend.insFirst(new ListNode(8));
        listExtend.insFirst(new ListNode(7));
        listExtend.insFirst(new ListNode(5));
        singleChainTable.merge(listExtend);
        singleChainTable.listTraverse();
    }
}
