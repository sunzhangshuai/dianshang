package com.data.structure.searchtable.staticsearch;

import com.data.structure.searchtable.staticsearchtable.impl.OrderStaticSearchTableImpl;
import com.data.structure.searchtable.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * OrderStaticSearchTableTest:
 * 有序表测试类
 *
 * @author sunchen
 * @date 2020/6/22 10:16 上午
 */
public class OrderStaticSearchTableTest {

    OrderStaticSearchTableImpl orderStaticSearchTable;

    @Before
    public void init() {
        Element[] array = new Element[]{
                new Element("2", "22"),
                new Element("1", "11"),
                new Element("6", "66"),
                new Element("3", "33"),
                new Element("4", "44"),
                new Element("8", "88"),
                new Element("5", "55"),
                new Element("7", "77"),
        };
        orderStaticSearchTable = new OrderStaticSearchTableImpl(array);
    }

    /**
     * 二分查找
     */
    @Test
    public void search() {
        Element search = orderStaticSearchTable.search("3");
        Assert.assertEquals("33", search.value);
    }

    /**
     * 插值查找
     */
    @Test
    public void insertSearch() {
        Element search = orderStaticSearchTable.interpolationSearch(8);
        Assert.assertEquals("88", search.value);
    }

    /**
     * 斐波那契查找
     */
    @Test
    public void fibonacciSearch() {
        Element element = orderStaticSearchTable.fibonacciSearch(3);
        Assert.assertEquals("33", element.value);
    }
}
