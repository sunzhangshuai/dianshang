package com.data.structure.stack.impl;

import com.data.structure.stack.interfaces.DoubleStack;

/**
 * 双向顺序栈
 *
 * @author wenba
 */
public class DoubleSequenceStackImpl implements DoubleStack {

    static int STACK_INIT_SIZE = 100;
    static int STACK_INCREMENT = 10;

    int[] base;
    int[] top;
    int[] stackSize;
    Object[] stack;

    public DoubleSequenceStackImpl() {
        stack = new Object[STACK_INIT_SIZE];
        base = new int[]{0, STACK_INIT_SIZE - 1};
        top = new int[]{0, STACK_INIT_SIZE - 1};
        stackSize = new int[]{STACK_INIT_SIZE, STACK_INIT_SIZE};
    }

    /**
     * 清空栈
     *
     * @param i 栈的标号
     */
    @Override
    public void clearStack(int i) {
        while (top[i] != base[i]) {
            pop(i);
        }
    }

    @Override
    public boolean stackEmpty(int i) {
        return base[i] == top[i];
    }

    @Override
    public int stackLength(int i) {
        return Math.abs(top[i] - base[i]);
    }

    @Override
    public Object getTop(int i) {
        return i == 1 ? stack[top[i] + 1] : stack[top[i] - 1];
    }

    @Override
    public void push(Object e, int i) {
        if (Math.abs(top[i] - base[i]) > stackSize[i]) {
            // todo 扩容
        }
        if (i == 1) {
            stack[top[i]--] = e;
            stackSize[0]--;
        } else {
            stack[top[i]++] = e;
            stackSize[1]--;
        }
    }

    @Override
    public Object pop(int i) {
        if (base[i] == top[i]) {
            throw new RuntimeException("栈已经空了，不能删除");
        }
        if (i == 1) {
            stackSize[0]++;
            return stack[++top[i]];
        }
        stackSize[1]++;
        return stack[--top[i]];
    }

    @Override
    public void stackTraverse(int i) {
        if (i == 0) {
            for (int index = base[i]; index < top[i]; index++) {
                visit(stack[index]);
            }
        } else {
            for (int index = base[i]; index > top[i]; index--) {
                visit(stack[index]);
            }
        }
    }

    public void visit(Object e) {
        System.out.println(e);
    }
}
