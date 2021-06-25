package com.data.structure.searchtable.staticsearch;

import com.data.structure.searchtable.dynamicsearchtable.impl.BinarySortTreeSearchTableImpl;
import com.data.structure.searchtable.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * BinarySortTreeSearchTableTest:
 *
 * @author sunchen
 * @date 2020/6/26 11:09 下午
 */
public class BinarySortTreeSearchTableTest {

    BinarySortTreeSearchTableImpl binarySortTreeSearchTable = new BinarySortTreeSearchTableImpl();
    
    @Before
    public void insert () {
        binarySortTreeSearchTable.insert(new Element(45, "45"));
        binarySortTreeSearchTable.insert(new Element(24, "24"));
        binarySortTreeSearchTable.insert(new Element(12, "12"));
        binarySortTreeSearchTable.insert(new Element(37, "37"));
        binarySortTreeSearchTable.insert(new Element(53, "53"));
        binarySortTreeSearchTable.insert(new Element(93, "93"));
        binarySortTreeSearchTable.traverse();
        List<String> values = binarySortTreeSearchTable.values;
        List<String> result = new ArrayList<String>();
        result.add("12");
        result.add("24");
        result.add("37");
        result.add("45");
        result.add("53");
        result.add("93");
        Assert.assertEquals(values, result);
    }
    
    @Test
    public void delete () {
        binarySortTreeSearchTable.delete(24);
        binarySortTreeSearchTable.traverse();
        List<String> values = binarySortTreeSearchTable.values;
        List<String> result = new ArrayList<String>();
        result.add("12");
        result.add("37");
        result.add("45");
        result.add("53");
        result.add("93");
        Assert.assertEquals(values, result);
        binarySortTreeSearchTable.delete(45);
        binarySortTreeSearchTable.traverse();
        values = binarySortTreeSearchTable.values;
        result = new ArrayList<String>();
        result.add("12");
        result.add("37");
        result.add("53");
        result.add("93");
        Assert.assertEquals(values, result);
    }
}
