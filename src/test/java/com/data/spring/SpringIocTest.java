package com.data.spring;

import com.data.structure.searchtable.dynamicsearchtable.impl.BtreeSortTreeSearchTableImpl;
import com.data.structure.tree.impl.tree.BtreeImpl;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * SpringIoc:
 *
 * @author sunchen
 * @date 2020/7/10 1:29 下午
 */
public class SpringIocTest {
    @Test
    public void newInstance() throws Exception {
        BtreeSortTreeSearchTableImpl btree = new BtreeSortTreeSearchTableImpl();
        Class<? extends BtreeSortTreeSearchTableImpl> clazz = btree.getClass();
        Field field = clazz.getDeclaredField("btree");
        field.setAccessible(true);
        Method method = clazz.getMethod("setBtree", BtreeImpl.class);
     //   method.invoke(btree, tree);
        System.out.println(btree.getBtree());
    }
}
