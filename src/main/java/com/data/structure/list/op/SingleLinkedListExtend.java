package com.data.structure.list.op;

import com.data.structure.list.impl.SingleLinkedListImpl;
import com.data.structure.list.model.ListNode;

/**
 * 链表扩展
 */
public class SingleLinkedListExtend extends SingleLinkedListImpl {
    /**
     * 记录起点到环口的距离
     */
    public int x = 0;
    /**
     * 记录环长
     */
    public int y = 1;

    /**
     * 翻转单链表  a <---b <---c
     */
    public void reverse() {
        if (head == null) {
            return;
        }
        //一个指针指向第二个结点
        ListNode current = head.next;
        //一个指针指向链表第一个结点
        ListNode pre = head;
        pre.next = null;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        head = pre;
    }

    /**
     * 求链表的中间结点
     */
    public ListNode getMiddleNode() {
        ListNode pre = head;
        ListNode current = head;
        while (current != null && current.next != null) {
            current = current.next.next;
            pre = pre.next;
        }
        return pre;
    }

    /**
     * 删除倒数第n个结点
     * a----》b-----》c-----》h-----》g------》f------》null
     */
    public void deleteByCount(int n) {
        ListNode pre = head;
        ListNode current = head;
        if (length < n) {
            throw new RuntimeException("该链表长度小于" + n + "，删除失败");
        }
        if (length == n) {
            head.next = head.next.next;
            head = head.next;
            return;
        }
        while (n != 0) {
            current = current.next;
            n--;
        }
        while (current.next != null) {
            current = current.next;
            pre = pre.next;
        }
        // pre就是要删除结点的前一个结点
        pre.next = pre.next.next;
        length--;
    }

    /**
     * 判断一个链表是否是回文 1 2 2 2 1
     */
    public boolean palindrome() {
        //1. 求中间结点
        ListNode node = getMiddleNode();
        if (node == null) {
            return false;
        }
        //2. 把中间结点到最后的结点放入一个新的list中
        SingleLinkedListImpl singleLinkedList = new SingleLinkedListImpl();
        while (node != null) {
            singleLinkedList.insFirst(node);
            node = node.next;
        }
        //3. 遍历
        ListNode node1 = head;
        ListNode node2 = singleLinkedList.head;
        while (node1 != null && node2 != null) {
            if (!node1.data.equals(node.data)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断链表是否有环，若有，求环长和头结点距离环口有多远
     * 为什么说一个走一步，一个走两步，如果有环，最终一定会相遇？
     * 证明：a和b一起出发，b的速度是a的2倍，起点距离环口为x米，环长为y,当a走到了环口,b距离环口为z,
     * 可以得到等式：ny+x+z = 2x ===》x = ny+z
     * 此时引出另一个问题：证明 a没有走完一圈，就和b相遇了
     * 我们暂且先用这个结论，得到 x = y+z
     * 当a到达环口，b在z处，故b比a快z, 又因为是一个环，同时可以看做，b比a 落后 y-z，接下来，b和a一起跑，当a和b相遇时，b比a多跑了y-z,
     * 那么假设a跑了w,则b跑了2w,w=b-z,所以a再跑y-z时，a和b相遇，此时a的位置和a在环口时，b的位置对称（对称点为环口）
     * 一个走一步，一个走三步，最终能相遇吗？2w=b-z 如果b-z是奇数，w将不是整数，而步数的单位是1步，所以不一定能相遇
     * a b
     */
    public boolean isRing() {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null && slow == fast) {
            //此时相遇，一个不动，一个跑，等再次相等的时候，说明跑了一圈
            slow = slow.next;
            while (slow != fast) {
                slow = slow.next;
                y++;
            }
            //一个从起点开始跑，一个从相遇点开始跑，等他们相遇时，刚好就是环口的位置
            ListNode node = head;
            while (node != fast) {
                node = node.next;
                fast = fast.next;
                x++;
            }
            return true;
        }
        return false;
    }

    /**
     * 有序链表，去除重复的结点
     */
    public void deleteRepeated() {
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            //删除重复的结点
            if (pre != null && pre.data.equals(node.data)) {
                pre.next = node.next;
                length--;
            } else {
                pre = node;
            }
            node = node.next;
        }
    }

    /**
     * 把有序链表list合并到当前的list中
     *
     * @param list
     */
    public void merge(SingleLinkedListImpl list) {
        ListNode mergeNode = list.head;
        ListNode node = this.head;
        ListNode pre = null;
        while (mergeNode != null && node != null) {
            //如果待合并结点比当前结点的data小
            if (mergeNode.val < node.val) {
                //插入新结点
                ListNode next = mergeNode.next;
                mergeNode.next = node;
                if (pre == null) {
                    head = mergeNode;
                } else {
                    pre.next = mergeNode;

                }
                pre = mergeNode;
                mergeNode = next;
            } else {
                pre = node;
                node = node.next;
            }
        }

        if (mergeNode != null) {
            this.append(mergeNode);
        } else {
            this.length += list.length;
        }
    }
}
