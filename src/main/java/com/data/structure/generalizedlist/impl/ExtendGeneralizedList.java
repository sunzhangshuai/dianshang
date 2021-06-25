package com.data.structure.generalizedlist.impl;

import com.data.structure.generalizedlist.interfaces.GeneralizedList;
import com.data.structure.generalizedlist.model.ElemTag;
import com.data.structure.generalizedlist.model.GeneralizedNode;
import com.data.structure.stack.impl.SequenceStackImpl;
import com.data.structure.string.impl.SequenceDynamicStringImpl;

/**
 * ExtendGeneralizedList:
 * 扩展链表存储 hp存储第一个孩子，tp存储自己的兄弟结点
 *
 * @author sunchen
 * @date 2020/5/24 9:32 下午
 */
public class ExtendGeneralizedList implements GeneralizedList<ExtendGeneralizedList> , Cloneable{

    GeneralizedNode<ExtendGeneralizedList> node;

    static SequenceStackImpl stack = new SequenceStackImpl();

    public ExtendGeneralizedList() {

    }

    /**
     * hp为第一个孩子结点，hp的tp是第二个孩子结点，依次设置其他孩子结点
     * @param chars
     */
    public ExtendGeneralizedList(char[] chars) {
        SequenceDynamicStringImpl blank = new SequenceDynamicStringImpl(new char[]{'(',')'});
        SequenceDynamicStringImpl left = new SequenceDynamicStringImpl(new char[]{'('});
        //1. 将字符串转化成数组
        SequenceDynamicStringImpl string = new SequenceDynamicStringImpl(chars);
        if (string.strCompare(blank) == 0) {
            return;
        }

        node = new GeneralizedNode<ExtendGeneralizedList>();
        //2. 如果是原子结点
        if (string.index(1,left) != 1) {
            node.tag = ElemTag.ATOM.code;
            node.atom = chars;
            return;
        }

        // 3.表结点
        node.tag = ElemTag.LIST.code;
        //先去掉两边的括号
        string = string.subString(2, string.length-2);
        int point = getBreakPoint(string);
        SequenceDynamicStringImpl str1 = string.subString(1, point-1);
        SequenceDynamicStringImpl str2 = blank;
        if (string.length != point - 1) {
            str2 = string.subString(point + 1, string.length - point);
        }
        node.hp = new ExtendGeneralizedList(str1.toArray());
        GeneralizedNode<ExtendGeneralizedList> childNode = node.hp.node;
        while (childNode != null) {
            if (str2.strCompare(blank) == 0) {
                childNode.tp = new ExtendGeneralizedList(blank.toArray());
            } else {
                int breakPoint = getBreakPoint(str2);
                str1 = str2.subString(1, breakPoint - 1);
                if (str2.length == breakPoint - 1) {
                    str2 = blank;
                } else {
                    str2 = str2.subString(breakPoint + 1, str2.length - breakPoint);
                }
                childNode.tp = new ExtendGeneralizedList(str1.toArray());
            }
            childNode = childNode.tp.node;
        }

    }

    public int getBreakPoint(SequenceDynamicStringImpl string) {
        for (int i = 0; i < string.strLength(); i++) {
            if (string.hString[i] == '(') {
                stack.push("(");
            } else if (string.hString[i] == ')') {
                stack.pop();
            } else if (string.hString[i] == ',' && stack.stackEmpty()) {
                return i + 1;
            }
        }
        return string.strLength() + 1;
    }

    @Override
    public int gListLength() {
        int i = 0;
        if (node == null) {
            return 1;
        }
        GeneralizedNode<ExtendGeneralizedList> childNode = node.hp.node;
        while (childNode != null) {
            i++;
            childNode = childNode.tp.node;
        }
        return i;
    }

    @Override
    public int gListDepth() {
        GeneralizedNode<ExtendGeneralizedList> childNode = node;
        if (childNode == null) {
            return 1;
        }
        if (childNode.tag == ElemTag.ATOM.code) {
            return 0;
        }
        int max = node.hp.gListDepth();
        childNode = node.hp.node;
        while (childNode != null) {
            if (childNode.tp.node == null) {
                break;
            }
            int deep = childNode.tp.gListDepth();
            if (deep > max) {
               max = deep;
            }
            childNode = childNode.tp.node;
        }
        return max+1;
    }

    @Override
    public boolean gListEmpty() {
        return node == null;
    }

    @Override
    public ExtendGeneralizedList getHead() {
        return node.hp;
    }

    /**
     * 外面为什么加了一层括号
     * @return
     */
    @Override
    public ExtendGeneralizedList getTail() {
        ExtendGeneralizedList newTail = new ExtendGeneralizedList();
        newTail.node = new GeneralizedNode<ExtendGeneralizedList>();
        newTail.node.hp = node.hp.node.tp;
        newTail.node.tag = ElemTag.LIST.code;
        return newTail;
    }

    /**
     * 扩展型插入
     * (a,(b,c))
     * @param e
     */
    @Override
    public void insertFirstGl(Object e) {
        ExtendGeneralizedList newAdd = new ExtendGeneralizedList();
        newAdd.node = node;

        GeneralizedNode<ExtendGeneralizedList> newNode = new GeneralizedNode<ExtendGeneralizedList>();
        newNode.tag = ElemTag.ATOM.code;
        newNode.atom = e;
        newNode.tp = newAdd;

        node = newNode;
    }

    /**
     * 扩展类删除
     * @return
     */
    @Override
    public Object deleteFirstGl() {
        Object e = node.hp.node.atom;
        node.hp.node = node.hp.node.tp.node;
        return e;
    }

    /**
     * 只访问原子结点
     */
    @Override
    public void traverseGl() {
        if (node == null) {
            return;
        }
        if (node.tag == ElemTag.ATOM.code) {
            visit(node);
        } else {
            ExtendGeneralizedList list = node.hp;
            while (list.node != null) {
                list.traverseGl();
                list = list.node.tp;
            }
        }
    }

    public void visit(GeneralizedNode<ExtendGeneralizedList> e) {
        System.out.println((char[])e.atom);
    }

    @Override
    public ExtendGeneralizedList clone() throws CloneNotSupportedException {
        ExtendGeneralizedList clone = (ExtendGeneralizedList) super.clone();
        if (node == null) {
           return clone;
        }
        clone.node = new GeneralizedNode<ExtendGeneralizedList>();
        clone.node.tag = node.tag;
        if (node.tag == ElemTag.ATOM.code) {
            clone.node.atom = node.atom;
        } else {
            clone.node.hp = node.hp.clone();
        }
        if (node.tp != null) {
            clone.node.tp = node.tp.clone();
        }
        return clone;
    }

    /**
     * 把广义表转成创建时，传入的字符串
     * @return
     */
    @Override
    public char[] toArray() {
        SequenceDynamicStringImpl right = new SequenceDynamicStringImpl(new char[]{')'});
        SequenceDynamicStringImpl left = new SequenceDynamicStringImpl(new char[]{'('});
        SequenceDynamicStringImpl comma = new SequenceDynamicStringImpl(new char[]{','});
        if (node == null) {
            return new char[0];
        }
        //原子结点
        if (node.tag == ElemTag.ATOM.code) {
            return (char[]) node.atom;
        }

        ExtendGeneralizedList childList = node.hp;
        SequenceDynamicStringImpl result = left;
        while (childList.node != null) {
            result = result.concat(new SequenceDynamicStringImpl(childList.toArray())).concat(comma);
            childList = childList.node.tp;
        }
        if (result.length != 1) {
            result = result.subString(1,result.length-1);
        }
        return result.concat(right).toArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char[] chars = toArray();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }
}
