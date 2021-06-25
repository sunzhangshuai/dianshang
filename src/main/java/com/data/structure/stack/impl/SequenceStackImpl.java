package com.data.structure.stack.impl;

import com.data.structure.stack.interfaces.Stack;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺序动态扩容栈
 *
 * @author wenba
 */
@Component
public class SequenceStackImpl implements Stack {

    static int STACK_INIT_SIZE = 100;
    static int STACK_INCREMENT = 10;
    int top;
    int base;
    Object[] stack;

    public List<Object> datas = new ArrayList<Object>();

    public SequenceStackImpl() {
        stack = new Object[STACK_INIT_SIZE];
    }

    @Override
    public void clearStack() {
        while (top > base) {
            pop();
        }
    }

    @Override
    public boolean stackEmpty() {
        return top == base;
    }

    @Override
    public int stackLength() {
        return top - base;
    }

    @Override
    public Object getTop() {
        if (top == 0) {
            throw new RuntimeException("栈是空的，没有栈顶元素");
        }
        return stack[top - 1];
    }

    @Override
    public void push(Object e) {
        //插入，先判断是否需要扩容，如果需要扩容，先扩容
        //先判断栈是否满了
        if (top - base == STACK_INIT_SIZE) {
            //说明栈满了
            expanded();
        }
        stack[top++] = e;
    }

    @Override
    public Object pop() {
        //先判断栈是不是已经空了，空了就没法删除了
        if (top == 0) {
            throw new RuntimeException("栈是空的，不能删除");
        }
        return stack[--top];
    }

    @Override
    public void stackTraverse() {
        clearData();
        for (int i = 0; i < stackLength(); i++) {
            visit(stack[i]);
        }
    }

    public void visit(Object e) {
        datas.add(e);
    }

    public void clearData() {
        datas = new ArrayList<Object>();
    }

    public void expanded() {
        Object[] newArray = new Object[STACK_INIT_SIZE + STACK_INCREMENT];
        if (STACK_INIT_SIZE >= 0) {
            System.arraycopy(stack, 0, newArray, 0, STACK_INIT_SIZE);
        }
        STACK_INIT_SIZE += STACK_INCREMENT;
        stack = newArray;
    }
}
