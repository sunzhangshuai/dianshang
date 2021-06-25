package com.data.structure.searchtable.hashtable.confictmanage;

import com.data.structure.list.impl.SingleLinkedListImpl;
import com.data.structure.list.model.ListNode;
import com.data.structure.searchtable.hashtable.table.AbstractHashTable;
import com.data.structure.searchtable.model.Element;

/**
 * LinkedHashTable:
 *
 * @author sunchen
 * @date 2020/7/12 4:34 下午
 */
public class Linked implements ConflictManage {
    @Override
    public Element search(AbstractHashTable hashTable, int index, Object key) {
        if (hashTable.data[index] == null) {
            return null;
        }
        SingleLinkedListImpl list = (SingleLinkedListImpl) hashTable.data[index];
        ListNode node = list.head;
        while (node != null && ((Element) (node.data)).equalKey(key) < 0) {
            node = node.next;
        }
        if (node == null || ((Element) (node.data)).equalKey(key) > 0) {
            return null;
        }
        return (Element) (node.data);
    }

    @Override
    public boolean insert(AbstractHashTable hashTable, int index, Element element) {
        Object data = hashTable.data[index];
        if (data == null) {
            hashTable.data[index] = new SingleLinkedListImpl();
        }
        SingleLinkedListImpl list = (SingleLinkedListImpl) hashTable.data[index];
        ListNode node = list.head;
        ListNode newNode = new ListNode(element);
        if (node == null || ((Element) node.data).equalKey(element.getKey()) < 0) {
            list.insFirst(newNode);
            return true;
        }
        if (((Element) node.data).equalKey(element.getKey()) == 0) {
            return false;
        }
        while (node.next != null && ((Element) node.next.data).equalKey(element.getKey()) < 0) {
            node = node.next;
        }
        if (((Element) node.next.data).equalKey(element.getKey()) == 0) {
            return false;
        }
        newNode.next = node.next;
        node.next = newNode;
        return true;
    }

    @Override
    public boolean delete(AbstractHashTable hashTable, int index, Object key) {
        SingleLinkedListImpl list = (SingleLinkedListImpl) hashTable.data[index];
        ListNode node = list.head;
        if (((Element) node.data).equalKey(key) == 0) {
            list.head = node.next;
            return true;
        }
        while (node.next != null && ((Element) node.next.data).equalKey(key) < 0) {
            node = node.next;
        }
        if (((Element) node.next.data).equalKey(key) != 0) {
            return false;
        }
        node.next = node.next.next;
        return true;
    }
}
