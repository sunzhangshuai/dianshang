package com.data.structure.stack;

import com.data.structure.stack.impl.SequenceStackImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class StackTest {
    SequenceStackImpl sequenceStack = new SequenceStackImpl();
    @Before
    public void createLinkedList () {
        sequenceStack.push("aa");
        sequenceStack.push("bb");
        sequenceStack.push("cc");
    }
    // 单链表测试
    @Test
    public void testPush () {
        sequenceStack.push("dd");
        sequenceStack.stackTraverse();
        assertEquals("[aa, bb, cc, dd]", sequenceStack.datas.toString());
    }

    @Test
    public void testPop () {
        sequenceStack.pop();
        sequenceStack.stackTraverse();
        assertEquals("[aa, bb]", sequenceStack.datas.toString());
    }

    @Test
    public void testGetTop () {
        Object top = sequenceStack.getTop();
        assertEquals("cc", top);
    }

    @Test
    public void testOther () throws IOException {

        //PolynomialImpl polynomial = new PolynomialImpl(5);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader buff;
        buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(buff.readLine());

    }
}
