package com.data.structure.string.impl;

import com.data.structure.string.interfaces.StringInterface;
import java.util.Arrays;

/**
 * 顺序动态存储
 * @author laosun
 */
public class SequenceDynamicStringImpl implements StringInterface<SequenceDynamicStringImpl> {

    public char[] hString;

    public int length;

    public SequenceDynamicStringImpl() {
    }

    public SequenceDynamicStringImpl(char[] hString) {
        this.hString = hString;
        this.length = hString.length;
    }

    @Override
    public void strCopy(SequenceDynamicStringImpl s) {
        if (s == null) {
            clearString();
            return;
        }
        if (length < s.length) {
            hString = new char[s.length];
        }
        if (s.length >= 0) {
            System.arraycopy(s.hString, 0, hString, 0, s.length);
        }
        length = s.length;
    }

    @Override
    public boolean strEmpty() {
        return length == 0;
    }

    @Override
    public int strCompare(SequenceDynamicStringImpl s) {
        if (s == null) {
            throw new RuntimeException("参与比较的字符串为null");
        }
        int i = 0;
        while (i < strLength() && i < s.strLength()) {
            if (hString[i] != s.hString[i]) {
                return hString[i] - s.hString[i];
            }
            i++;
        }
        return length - s.length;
    }

    @Override
    public int strLength() {
        return length;
    }

    @Override
    public void clearString() {
        hString = null;
        length = 0;
    }

    /**
     * 与v合并，存入s中
     * @param v
     */
    @Override
    public SequenceDynamicStringImpl concat(SequenceDynamicStringImpl v) {
        int otherLen = v.length;
        if (otherLen == 0) {
            return this;
        }
        char[] buf = Arrays.copyOf(hString, length + otherLen);
        System.arraycopy(v.hString, 0, buf, length, v.length);
        return new SequenceDynamicStringImpl(buf);
    }

    @Override
    public SequenceDynamicStringImpl subString(int pos, int len) {
        if (pos < 1 || pos > length || len < 0 || pos + len > length + 1) {
            throw new RuntimeException("pos或者len不合法");
        }
        SequenceDynamicStringImpl s = new SequenceDynamicStringImpl();
        s.hString = new char[len];
        System.arraycopy(hString, pos - 1, s.hString, 0, len);
        s.length = len;
        return s;
    }

    @Override
    public int index(int pos, SequenceDynamicStringImpl s) {
        for (int i = pos - 1; i <= length - s.length; i++) {
            boolean equals = true;
            for (int j = 0; j < s.length; j++) {
                if (hString[i] != s.hString[i]) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    public void replace(SequenceDynamicStringImpl s, SequenceDynamicStringImpl v) {
        int index = index(1, s);
        while (index > 0) {
            //替换
            strDelete(index, s.length);
            strInsert(index, v);
            index = index(index + v.length , s);
        }
    }

    @Override
    public void strInsert(int pos, SequenceDynamicStringImpl s) {
        if (pos < 1 || pos > length + 1) {
            throw new RuntimeException("pos不合法");
        }
        //1. 先分配足够的空间
        int oldEnd = length;
        length = length + s.length;
        if (length < hString.length) {
            hString = Arrays.copyOf(hString, length);
        }
        //2. 把数据搬移到插入的位置
        if (oldEnd + 1 - pos - 1 >= 0) {
            System.arraycopy(hString, pos - 1, hString, pos - 1 + s.length, oldEnd + 1 - pos - 1);
        }
        for (int i = pos - 1; i < pos + s.length; i++) {
            hString[i] = s.hString[i - pos - 1];
        }
    }

    @Override
    public void strDelete(int pos, int len) {
        if (pos < 1 || pos > length || pos + len > length + 1) {
            throw new RuntimeException("pos或者len不合法");
        }
        if (pos + len - pos >= 0) {
            System.arraycopy(hString, pos + len, hString, pos, pos + len - pos);
        }
        length = length - len;
    }

    public char[] toArray() {
        char[] array = new char[length];
        System.arraycopy(hString, 0, array, 0, length);
        return array;
    }
}
