package com.data.structure.list.impl;

import com.data.structure.list.interfaces.Polynomial;
import com.data.structure.list.model.ListNode;
import com.data.structure.list.model.Term;
import com.data.structure.list.op.PolynomialLinkedList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 一元多项式基本操作
 *
 * @author wenba
 */
public class PolynomialImpl implements Polynomial {

    public PolynomialLinkedList linked;

    /**
     * 构造一元多项式
     *
     * @param terms 项
     */
    public PolynomialImpl(List<Term> terms) {
        linked = new PolynomialLinkedList();
        for (Term term : terms) {
            ListNode locateNode = linked.locateElem(term);
            if (locateNode != null) {
                //如果是相等，则系数相加，更改系数的值
                if (term.equals(locateNode.data)) {
                    ((Term) locateNode.data).coef += term.coef;
                    if (((Term) locateNode.data).coef == 0.0f) {
                        linked.delCur(locateNode);
                    }
                } else {
                    ListNode lNode = new ListNode(term);
                    linked.insBefore(locateNode, lNode);
                }
            } else {
                ListNode lNode = new ListNode(term);
                linked.append(lNode);
            }
        }
    }

    /**
     * 输出一元多项式
     */
    @Override
    public String toString() {
        ListNode node = linked.head;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            if (sb.length() != 0) {
                if (((Term) node.data).coef > 0) {
                    sb.append("+");
                }
            }
            sb.append(((Term) node.data).coef).append("X^").append(((Term) node.data).expn);
            node = node.next;
        }
        return sb.toString();
    }

    @Override
    public int polynLength() {
        return linked.listLength();
    }

    @Override
    public void addPolyn(PolynomialImpl polynomial) {
        subtractOrAdd(polynomial, 0);
    }

    @Override
    public void subtractPolyn(PolynomialImpl polynomial) {
        subtractOrAdd(polynomial, 1);
    }

    @Override
    public void multiplyPolyn(PolynomialImpl polynomial) {
        List<Term> terms = new ArrayList<Term>();
        ListNode node1 = linked.head;
        for (int i = 1; i <= linked.listLength(); i++) {
            ListNode node2 = polynomial.linked.head;
            for (int j = 1; j < polynomial.linked.listLength(); j++) {
                float coef = ((Term) node1.data).coef * ((Term) node2.data).coef;
                int expn = ((Term) node1.data).expn + ((Term) node2.data).expn;
                Term term = new Term(coef, expn);
                terms.add(term);
                node2 = node2.next;
            }
            node1 = node1.next;
        }
        PolynomialImpl third = new PolynomialImpl(terms);
        linked = third.linked;
    }

    public int compare(int exp1, int exp2) {
        if (exp1 < exp2) {
            return -1;
        } else if (exp1 == exp2) {
            return 0;
        } else {
            return 1;
        }
    }

    public void subtractOrAdd(PolynomialImpl polynomial, int type) {
        ListNode node1 = linked.head;
        ListNode node2 = polynomial.linked.head;
        while (node1 != null && node2 != null) {
            BigDecimal data1 = new BigDecimal(String.valueOf(((Term) node2.data).coef));
            BigDecimal data2 = new BigDecimal(String.valueOf(type == 1 ? (-1)*((Term) node2.data).coef:((Term) node2.data).coef));
            switch (compare(((Term) node1.data).expn, ((Term) node2.data).expn)) {
                // 小
                case -1:
                    node1 = node1.next;
                    break;
                // 相等
                case 0:
                    BigDecimal data = data1.add(data2);
                    if (data.floatValue() == 0.0f) {
                        ListNode next = node1.next;
                        linked.delCur(node1);
                        node1 = next;
                    } else {
                        node1 = node1.next;
                    }
                    node2 = node2.next;
                    break;
                // 大
                case 1:
                    ListNode next = node2.next;
                    ListNode newNode = new ListNode(new Term(data2.floatValue(), ((Term) node2.data).expn));
                    linked.insBefore(node1, newNode);
                    node2 = next;
                default:
            }
        }
        if (node2 != null) {
            while (node2 != null) {
                float data2 = type == 1 ? (-1)*((Term) node2.data).coef:((Term) node2.data).coef;
                ListNode newNode = new ListNode(new Term(data2, ((Term) node2.data).expn));
                linked.append(newNode);
                node2 = node2.next;
            }
        }
    }
}
