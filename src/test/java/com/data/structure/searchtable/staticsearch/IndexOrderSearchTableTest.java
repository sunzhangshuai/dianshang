package com.data.structure.searchtable.staticsearch;

import com.data.structure.searchtable.staticsearchtable.impl.IndexOrderTableImpl;
import com.data.structure.searchtable.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexOrderSearchTableTest:
 *
 * @author sunchen
 * @date 2020/6/23 5:35 下午
 */
public class IndexOrderSearchTableTest {
    IndexOrderTableImpl indexOrderTable;

    @Before
    public void init() {
        Element[] elements = new Element[]{
                new Element(86, "1"),
                new Element(53, "2"),
                new Element(22, "3"),
                new Element(12, "4"),
                new Element(58, "5"),
                new Element(74, "6"),
                new Element(49, "7"),
                new Element(44, "8"),
                new Element(38, "9"),
                new Element(24, "10"),
                new Element(13, "11"),
                new Element(8, "12"),
                new Element(48, "13"),
                new Element(60, "14"),
                new Element(33, "15"),
                new Element(42, "16"),
                new Element(20, "17"),
                new Element(9, "18"),

        };
        indexOrderTable = new IndexOrderTableImpl(elements);
    }

    @Test
    public void search() {
        Element search = indexOrderTable.search(42);
        Assert.assertEquals("16", search.value);
    }

    @Test
    public void traverse() {
        indexOrderTable.traverse();
        List<String> data = indexOrderTable.data;
        List<String> result = new ArrayList<String>();
        result.add("18");
        result.add("17");
        result.add("12");
        result.add("11");
        result.add("4");
        result.add("3");
        result.add("15");
        result.add("10");
        result.add("9");
        result.add("16");
        result.add("13");
        result.add("8");
        result.add("7");
        result.add("2");
        result.add("14");
        result.add("5");
        result.add("6");
        result.add("1");
        Assert.assertEquals(data, result);
    }
}
