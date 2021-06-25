package com.data.structure.string.model;

import java.io.Serializable;

/**
 * @author sunchen
 */
public class ChunkNode implements Cloneable, Serializable {
    /**
     * 块内字符串
     */
    public char[] ch;

    /**
     * 下一个块
     */
    public ChunkNode next;

    public ChunkNode(char[] ch) {
        this.ch = ch;
    }

    public ChunkNode(char[] ch, ChunkNode next) {
        this.ch = ch;
        this.next = next;
    }
}
