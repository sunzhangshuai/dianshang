package com.data.structure.generalized;

import com.data.structure.generalizedlist.impl.ExtendGeneralizedList;
import com.data.structure.generalizedlist.impl.GeneralizedListImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * GeneralizedList:
 *
 * @author sunchen
 * @date 2020/5/24 9:53 上午
 */
public class ExtendsGeneralizedListImplTest {
    ExtendGeneralizedList generalizedList;
    char[] string = {'(','(','a',',','b',')',',','(','(','d',',','e',')',')',')'};
    //char[] string = {'(','(',')',')'};
    @Before
    public void init(){
        generalizedList = new ExtendGeneralizedList(string);
        System.out.println(generalizedList.toArray());
        generalizedList.traverseGl();
        Assert.assertArrayEquals(string, generalizedList.toArray());
    }

    @Test
    public void getDepth(){
        Assert.assertEquals(generalizedList.gListDepth(), 3);
        Assert.assertEquals(generalizedList.gListLength(), 2);
    }
    @Test
    public void copy() throws CloneNotSupportedException {
        ExtendGeneralizedList clone = generalizedList.clone();
        Assert.assertArrayEquals(string, clone.toArray());
    }
    @Test
    public void insert(){
        char [] newChar = new char[]{'a'};
        generalizedList.insertFirstGl(newChar);
        Assert.assertArrayEquals(new char[]{'(','a',',','(','a',',','b',')',',','(','(','d',',','e',')',')',')'},generalizedList.toArray());
    }
    @Test
    public void delete(){
        generalizedList.deleteFirstGl();
        Assert.assertArrayEquals(new char[]{'(','(','(','d',',','e',')',')',')'},generalizedList.toArray());
    }
    @Test
    public void getHead(){
        ExtendGeneralizedList head = generalizedList.getHead();
        Assert.assertArrayEquals(new char[]{'(','a',',','b',')'},head.toArray());
    }
    @Test
    public void getTail(){
        ExtendGeneralizedList tail = generalizedList.getTail();
        System.out.println(tail.toArray());
        Assert.assertArrayEquals(new char[]{'(','(','(','d',',','e',')',')',')'},tail.toArray());
    }
}
