package com.data.structure.searchtable.dynamic;

import com.data.structure.searchtable.dynamicsearchtable.impl.BalanceBinarySortTreeStackSearchTableImpl;
import com.data.structure.searchtable.model.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * TreeStaticSearchTable:
 * 平衡二叉树测试
 *
 * @author sunchen
 * @date 2020/6/22 10:57 上午
 */
public class BBSTStackSearchTableTest {
    BalanceBinarySortTreeStackSearchTableImpl searchTable;
    @Before
    public void insert() {
        searchTable = new BalanceBinarySortTreeStackSearchTableImpl();
        // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
        searchTable.insert(new Element("Jan", "Jan"));
        searchTable.insert(new Element("Feb", "Feb"));
        searchTable.insert(new Element("Mar", "Mar"));
        searchTable.insert(new Element("Apr", "Apr"));
        searchTable.insert(new Element("May", "May"));
        searchTable.insert(new Element("Jun", "Jun"));
        searchTable.insert(new Element("Jul", "Jul"));
        searchTable.insert(new Element("Aug", "Aug"));
        searchTable.insert(new Element("Sept", "Sept"));
        searchTable.insert(new Element("Oct", "Oct"));
        searchTable.insert(new Element("Nov", "Nov"));
        searchTable.insert(new Element("Dec", "Dec"));
//        searchTable.traverse();
//        List<String> values = searchTable.values;
//        List<String> result = new ArrayList<String>();
//        result.add("Apr");
//        result.add("Aug");
//        result.add("Dec");
//        result.add("Feb");
//        result.add("Jan");
//        result.add("Jul");
//        result.add("Jun");
//        result.add("Mar");
//        result.add("May");
//        result.add("Nov");
//        result.add("Oct");
//        result.add("Sept");
//        Assert.assertEquals(values, result);
    }

    @Test
    public void delete(){
        searchTable.delete("Oct");
        searchTable.traverse();
        List<String> values = searchTable.values;
        List<String> result = new ArrayList<String>();
        result.add("Apr");
        result.add("Aug");
        result.add("Dec");
        result.add("Feb");
        result.add("Jan");
        result.add("Jul");
        result.add("Jun");
        result.add("Mar");
        result.add("May");
        result.add("Nov");
        result.add("Sept");
        Assert.assertEquals(result, values);
    }
}
