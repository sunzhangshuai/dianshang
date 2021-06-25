package com.data.structure.string.impl;

import com.data.structure.string.interfaces.StringInterface;
import com.data.structure.string.model.ChunkNode;
import java.util.TreeMap;

/**
 * 链式块状存储
 * @author laosun
 */
public class LinkedChunkedStringImpl implements StringInterface<LinkedChunkedStringImpl> {

    public ChunkNode head;
    public ChunkNode tail;
    /**
     * 串长
     */
    int length;

    /**
     * 块大小
     */
    static int chunkSize = 4;

    public LinkedChunkedStringImpl() {}

    /**
     * 根据传入的字符数组，创建块状存储的字符串
     * 说明：尾插法创建链表，链表中的结点，从字符数组中取，不足补#
     * @param chars
     */
    public LinkedChunkedStringImpl(char[] chars) {
        if (chars.length == 0) {
            return;
        }
        length = chars.length;
        int i = 0;
        while (i < length) {
            ChunkNode newNode = new ChunkNode(new char[chunkSize]);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            for (int j = 0; j < chunkSize; j++,i++) {
                if (i > chars.length-1) {
                    tail.ch[j] = '#';
                } else {
                    tail.ch[j] = chars[i];
                }
            }
        }
    }

    /**
     * 字符串复制
     * 把s串复制给本串
     * @param s
     */
    @Override
    public void strCopy(LinkedChunkedStringImpl s){
        //1. 清空本串
        clearString();
        //2. 如果串s为空，则返回空串
        if (s == null || s.strLength() == 0) {
            return;
        }
        length = s.length;
        ChunkNode node = s.head;
        while (node != null) {
            //创建新结点
            ChunkNode newNode = new ChunkNode(new char[chunkSize]);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            if (chunkSize >= 0) {
                System.arraycopy(node.ch, 0, tail.ch, 0, chunkSize);
            }
            node = node.next;
        }
    }

    @Override
    public boolean strEmpty() {
        return length == 0;
    }

    @Override
    public int strCompare(LinkedChunkedStringImpl s) {
        ChunkNode node1 = head;
        ChunkNode node2 = s.head;
        while (node1 != null && node2 != null) {
            char[] ch1 = node1.ch;
            char[] ch2 = node2.ch;
            for (int i = 0; i < chunkSize; i++) {
                if (ch1[i] != ch2[i]) {
                    return ch1[i] - ch2[i];
                }
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return length - s.length;
    }

    @Override
    public int strLength() {
        return length;
    }

    @Override
    public void clearString() {
        ChunkNode node = head;
        ChunkNode next;
        while (node != null) {
            next = node.next;
            node.next = null;
            node.ch = null;
            node = next;
        }
        head = tail = null;
        length = 0;
    }

    @Override
    public LinkedChunkedStringImpl concat(LinkedChunkedStringImpl v){
        LinkedChunkedStringImpl linkedChunkedString = new LinkedChunkedStringImpl();
        //1. 判断原串是不是空的：
        if (length == 0) {
            linkedChunkedString.strCopy(v);
            return linkedChunkedString;
        }
        //2. 要合并的串为空
        if (v.length == 0) {
            return this;
        }
        //3. 原串不空，先复制原串
        linkedChunkedString.strCopy(this);
        linkedChunkedString.append(v.head, 0, v.length);
        return linkedChunkedString;
    }

    /**
     * 从原串的pos个位置起，获取长度为len的串
     * @param pos
     * @param len
     * @return
     */
    @Override
    public LinkedChunkedStringImpl subString(int pos, int len) {
        if (pos < 1 || pos > length || len < 0 || pos + len > length + 1) {
            throw new RuntimeException("pos不合法");
        }
        LinkedChunkedStringImpl link = new LinkedChunkedStringImpl();
        if (strEmpty()) {
            return link;
        }
        // 计算从哪个结点的哪个位置开始截取
        TreeMap<Integer,Object> nodeAndIndex = getChunkNodeIndex(head, pos);
        int nodeIndex = (Integer) nodeAndIndex.get(0);
        ChunkNode node = (ChunkNode) nodeAndIndex.get(1);
        link.append(node, nodeIndex, len);
        return link;
    }

    @Override
    public int index(int pos, LinkedChunkedStringImpl s) {
        //1.pos是否合法
        if (pos < 0 || pos > length) {
            throw new RuntimeException("pos不合法");
        }
        ChunkNode mainNode = head;
        // 记录当前遍历到的主串的位置
        int len = 0;
        // 遍历主串
        while (mainNode != null) {
            for (int i = 0; i < chunkSize; i++) {
                len++;
                // 当前位置小于pos，直接向下遍历
                if (len < pos) {
                    continue;
                }
                // 当前位置大于length - s.length + 1，没有找到
                if (len > length - s.length + 1) {
                    return 0;
                }
                // 与目标字符串s进行比较，如果找到返回true
                if (compareForIndex(s, mainNode, i)) {
                    return len;
                }
            }
            mainNode = mainNode.next;
        }
        return 0;
    }


    /**
     * 是否存在串s,如果有，则用v替代
     * @param s
     * @param v
     */
    @Override
    public void replace(LinkedChunkedStringImpl s, LinkedChunkedStringImpl v) {
        int index = index(1,s);
        while (index > 0) {
            strDelete(index, s.length);
            strInsert(index, v);
            index = index(index + v.length, s);
        }
    }

    /**
     * 在pos位置插入s
     * @param pos 插入位置
     * @param s 插入的串
     */
    @Override
    public void strInsert(int pos, LinkedChunkedStringImpl s) {
        //1. 校验参数合法性
        if (pos < 0 || pos > length + 1) {
            throw new RuntimeException("pos不合法");
        }
        //2. 判断串s是否为空
        if (s == null || s.length == 0) {
            return;
        }
        //3. 求插入位置对应的块和块中的下标
        ChunkNode newNode;
        int startIndex;
        //3.1 如果插入位置是第一块的第一个结点，则类似单链表的头插
        if (pos == 1) {
            newNode = head;
            startIndex = 0;
            //断开原串
            head = tail = null;
        } else {
            //3.2 求pos所在的块和在块中的下标
            TreeMap<Integer, Object> nodeAndIndex = getChunkNodeIndex(head, pos);
            startIndex = (Integer) nodeAndIndex.get(0);
            ChunkNode node = (ChunkNode) nodeAndIndex.get(1);
            //3.3 如果起始位置是块中的第一个位置，则需要找到上一个结点
            if (startIndex == 0) {
                TreeMap<Integer, Object> preNodeAndIndex = getChunkNodeIndex(head, pos-1);
                node = (ChunkNode) preNodeAndIndex.get(1);
            }
            //3.4. 拷贝一份node，避免插入新串，覆盖旧串
            newNode = new ChunkNode(node.ch, node.next);
            //3.5. 把原串的node结点和后面结点断开
            node.next = null;
            tail = node;
        }
        //4. 获取需要后移的串长
        int moveLen = length - pos + 1;
        //5. 修改原串长度
        length = pos - 1;
        //6. 拼接s串
        append(s.head, 0, s.length);
        //7. 拼接后移的串
        append(newNode, startIndex, moveLen);
    }

    @Override
    public void strDelete(int pos, int len){
        //1.校验参数合法性
        if (pos < 1 || pos > length || len < 0 || pos + len > length + 1) {
            throw new RuntimeException("pos不合法");
        }
        //2.获取要移动的块和下标
        TreeMap<Integer, Object> map = getChunkNodeIndex(head, pos+len);
        int startIndex = (Integer) map.get(0);
        ChunkNode nextNode = (ChunkNode) map.get(1);
        //从第一个块的第一个结点开始删除
        if (pos == 1) {
            head = tail = null;
        } else {
            //3.找到pos位置的node
            TreeMap<Integer, Object> nodeAndIndex = getChunkNodeIndex(head, pos);
            ChunkNode node = (ChunkNode) nodeAndIndex.get(1);
            int index = (Integer) nodeAndIndex.get(0);
            //如果修改的是同一个结点，拷贝一份
            if (node == nextNode) {
                nextNode = new ChunkNode(node.ch, node.next);
            }
            //找到pos-1的位置的node
            if (index == 0) {
                TreeMap<Integer, Object> preNodeAndIndex = getChunkNodeIndex(head, pos-1);
                node = (ChunkNode) preNodeAndIndex.get(1);
            }
            node.next = null;
            tail = node;
        }
        //4.获取最后追加的长度
        int moveLength = length - pos - len + 1;
        length = pos - 1;
        append(nextNode, startIndex, moveLength);
    }

    /**
     * 从块node的startIndex开始，为新串尾插len个长度的元素
     * @param node 起始结点
     * @param startIndex 起始结点的下标
     * @param len 插入的长度
     */
    public void append(ChunkNode node, int startIndex, int len){
        //计算往串的尾结点的哪个位置开始插入
        int index = this.length % chunkSize;
        int count = 0;
        while (node != null) {
            int j;
            for (j = startIndex; j < chunkSize; j++) {
                count++;
                if (count > len){
                    break;
                }
                if (index == chunkSize) {
                    index = 0;
                }
                if (index == 0) {
                    ChunkNode newNode = new ChunkNode(new char[chunkSize]);
                    if (this.head == null) {
                        this.head = this.tail = newNode;
                    } else {
                        this.tail.next = newNode;
                        this.tail = newNode;
                    }
                }
                this.tail.ch[index] = node.ch[j];
                index++;
            }
            if (j < chunkSize) {
                break;
            }
            startIndex = 0;
            node = node.next;
        }
        //判断是否需要补充#
        for (; index < chunkSize; index++) {
            this.tail.ch[index] = '#';
        }
        this.length = len + this.length;
    }

    /**
     * 从node结点的nodeIndex下标起，前缀是否等于s
     * @param s
     * @param node
     * @param nodeIndex
     * @return
     */
    private boolean compareForIndex(LinkedChunkedStringImpl s, ChunkNode node, int nodeIndex) {
        ChunkNode subNode = s.head;
        int subLen = 0;
        while (subNode != null) {
            int j;
            for (j = 0; j < chunkSize; j++, nodeIndex++) {
                subLen++;
                if (subLen > s.length) {
                    return true;
                }
                if (nodeIndex == chunkSize) {
                    nodeIndex = 0;
                    node = node.next;
                }
                if (node.ch[nodeIndex] != subNode.ch[j]) {
                    return false;
                }
            }
            subNode = subNode.next;
        }
        return false;
    }

    /**
     * 获取pos位置对应的块和在块中的下标
     * @param node
     * @param pos
     * @return
     */
    public TreeMap<Integer, Object> getChunkNodeIndex(ChunkNode node, int pos){
        TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
        int block = (int) Math.ceil((double) (pos) / chunkSize);
        //3.获取在块中的下标：
        int i = (pos - 1) % chunkSize;
        while (block > 1) {
            node = node.next;
            block--;
        }
        map.put(0, i);
        map.put(1, node);
        return map;
    }
}
