package com.data.structure.searchtable.staticsearch;

import com.data.structure.searchtable.staticsearchtable.impl.TreeStaticSearchTableImpl;
import com.data.structure.searchtable.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeStaticSearchTable:
 * 静态树表测试
 *
 * @author sunchen
 * @date 2020/6/22 10:57 上午
 */
public class TreeStaticSearchTableTest {
    TreeStaticSearchTableImpl treeStaticSearchTable;

    @Before
    public void init() {
        Element[] array = new Element[]{
                new Element(2, "22", 2),
                new Element(1, "11", 4),
                new Element(6, "66", 3),
                new Element(3, "33", 10),
                new Element(4, "44", 5),
                new Element(8, "88", 21),
                new Element(5, "55", 12),
                new Element(7, "77", 13),

        };
        treeStaticSearchTable = new TreeStaticSearchTableImpl(array);
    }

    @Test
    public void search() {
        Element search = treeStaticSearchTable.search(3);
        Assert.assertEquals("33", search.value);
    }

    @Test
    public void traverse() {
        treeStaticSearchTable.traverse();
        List<String> list = new ArrayList<String>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("66");
        list.add("77");
        list.add("88");
        List<String> result = treeStaticSearchTable.valueDatas;
        Assert.assertEquals(list, result);
    }
}
