package com.data.structure.generalized;

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
public class GeneralizedListImplTest {
    GeneralizedListImpl generalizedList;
    char[] string = {'(','(','a',',','b',')',',','(','g',',','(','d',',','e',')',')',')'};

    @Before
    public void init(){
        generalizedList = new GeneralizedListImpl(string);
        Assert.assertArrayEquals(string, generalizedList.toArray());
    }

    @Test
    public void getDepth(){
        Assert.assertEquals(generalizedList.gListDepth(), 3);
        Assert.assertEquals(generalizedList.gListLength(), 2);
    }
    @Test
    public void copy() throws CloneNotSupportedException {
        GeneralizedListImpl clone = generalizedList.clone();
        Assert.assertArrayEquals(string, clone.toArray());
    }
    @Test
    public void insert(){
//        GeneralizedListImpl tail = generalizedList.getTail();
//        char [] newChar = new char[]{'a'};
//        tail.insertFirstGl(newChar);
//        System.out.println(generalizedList.toArray());
//        Assert.assertArrayEquals(new char[]{'(','(','a',',','b',')',',','a',',','(','g',',','(','d',',','e',')',')',')'},generalizedList.toArray());

        GeneralizedListImpl tail = generalizedList.getTail().getHead().getTail();
        tail.insertFirstGl(new char[]{'f'});
        char[] string = {'(','(','a',',','b',')',',','(','g',',','f',',','(','d',',','e',')',')',')'};
        Assert.assertArrayEquals(string,generalizedList.toArray());

    }
    @Test
    public void delete(){
        generalizedList.deleteFirstGl();
        Assert.assertArrayEquals(new char[]{'(','(','g',',','(','d',',','e',')',')',')'},generalizedList.toArray());
    }
    @Test
    public void getHead(){
        GeneralizedListImpl head = generalizedList.getHead();
        Assert.assertArrayEquals(new char[]{'(','a',',','b',')'},head.toArray());
    }
    @Test
    public void getTail(){
        GeneralizedListImpl tail = generalizedList.getTail();
        Assert.assertArrayEquals(new char[]{'(','(','g',',','(','d',',','e',')',')',')'},tail.toArray());
    }
}
