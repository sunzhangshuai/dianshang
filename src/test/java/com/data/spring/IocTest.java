package com.data.spring;

import com.data.structure.searchtable.dynamicsearchtable.impl.BtreeSortTreeSearchTableImpl;
import com.data.structure.tree.impl.tree.BtreeImpl;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/10 12:43 下午
 */
public class IocTest {
    @Test
    public void test() throws Exception {
//        BtreeSortTreeSearchTableImpl btree = new BtreeSortTreeSearchTableImpl();
//        BtreeImpl tree = new BtreeImpl();
//        System.out.println(tree);
//        //利用反射把tree注入到btree中
//        Class<? extends BtreeSortTreeSearchTableImpl> clazz = btree.getClass();
//        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
//            Autowired annotation = field.getAnnotation(Autowired.class);
//            if (annotation != null) {
//                field.setAccessible(true);
//                Class<?> type = field.getType();
//                try {
//                    Object o = type.newInstance();
//                    field.set(btree, o);
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        System.out.println(btree.getBtree());
    }
}
