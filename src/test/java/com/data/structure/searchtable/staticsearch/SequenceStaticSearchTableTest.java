package com.data.structure.searchtable.staticsearch;

import com.data.structure.searchtable.staticsearchtable.impl.SequenceStaticSearchTableImpl;
import com.data.structure.searchtable.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * SequenceStaticSearchTableTest:
 *
 * @author sunchen
 * @date 2020/6/19 2:58 下午
 */
public class SequenceStaticSearchTableTest {
    SequenceStaticSearchTableImpl sequenceStaticSearchTable;

    @Before
    public void init() {
        Element[] array = new Element[]{
                new Element(1, "11"),
                new Element(2, "22"),
                new Element(3, "33"),
                new Element(4, "44"),
                new Element(5, "55"),
                new Element(6, "66"),
                new Element(7, "77"),
                new Element(8, "88"),
        };
        sequenceStaticSearchTable = new SequenceStaticSearchTableImpl(array);
    }

    @Test
    public void search() {
        Element search = sequenceStaticSearchTable.search(3);
        Assert.assertEquals("33", search.value);
        sequenceStaticSearchTable.traverse();
        List<String> list = new ArrayList<String>();
        list.add("11");
        list.add("22");
        list.add("44");
        list.add("55");
        list.add("66");
        list.add("77");
        list.add("88");
        list.add("33");
        Assert.assertEquals(list, sequenceStaticSearchTable.data);
    }
}
