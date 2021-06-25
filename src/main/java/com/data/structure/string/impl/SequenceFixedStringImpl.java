package com.data.structure.string.impl;

import com.data.structure.string.interfaces.StringInterface;

/**
 * 顺序定长存储
 * @author sunchen
 */
public class SequenceFixedStringImpl implements StringInterface<SequenceFixedStringImpl> {

    public static int MAX_STRING = 255;

    char[] sString;

    public SequenceFixedStringImpl() {
        sString = new char[MAX_STRING + 1];
    }

    public SequenceFixedStringImpl(char[] chars) {
        sString = new char[MAX_STRING + 1];
        if (chars != null && chars.length <= MAX_STRING) {
            int length = Math.min(chars.length, MAX_STRING);
            System.arraycopy(chars, 0, sString, 1, length);
            sString[0] = (char) chars.length;
        }
    }

    @Override
    public void strCopy(SequenceFixedStringImpl s) {
        System.arraycopy(s.sString, 0, sString, 0, s.strLength());
    }

    @Override
    public boolean strEmpty() {
        return strLength() == 0;
    }

    @Override
    public int strCompare(SequenceFixedStringImpl s) {
        int i = 1;
        while (i <= strLength() && i <= s.strLength()) {
            if (sString[i] != s.sString[i]) {
                return sString[i] - s.sString[i];
            }
            i++;
        }
        return strLength() - s.strLength();
    }

    @Override
    public int strLength() {
        return sString[0];
    }

    @Override
    public void clearString() {
        // 清空
        for (int i = 0; i <= strLength(); i++) {
            sString[i] = 0;
        }
    }

    /**
     * 与t合并，存入s中
     * @param t
     */
    @Override
    public SequenceFixedStringImpl concat(SequenceFixedStringImpl t) {
        if (t.strLength() == 0) {
            return this;
        }
        SequenceFixedStringImpl s = new SequenceFixedStringImpl();
        s.strCopy(this);
        int length = Math.min(t.strLength(), MAX_STRING - strLength());
        if (length > 0) {
            System.arraycopy(t.sString, 1, s.sString, s.strLength()+1, length);
            s.sString[0] = (char) (s.strLength() + length);
        }
        return s;
    }

    @Override
    public SequenceFixedStringImpl subString(int pos, int len) {
        SequenceFixedStringImpl s = new SequenceFixedStringImpl();
        if (pos < 0 || pos > MAX_STRING || len < 0 || len > strLength() - pos + 1) {
            throw new RuntimeException("参数不合法");
        }
        System.arraycopy(sString, pos + 1 - 1, s.sString, 1, len);
        s.sString[0] = (char) len;
        return s;
    }

    @Override
    public int index(int pos, SequenceFixedStringImpl s) {
        for (int i = pos; i < strLength() - s.strLength() + 1; i++) {
            boolean equals = true;
            for (int j = 1; j <= s.strLength(); j++) {
                if (sString[i+j-1] != s.sString[j]) {
                    equals = false;
                    break;
                }
                j++;
            }
            if (equals) {
               return i;
            }
        }
        return 0;
    }

    /**
     * 用v替代主串中的子串s
     * @param s 被替换目标
     * @param v 替换目标
     */
    @Override
    public void replace(SequenceFixedStringImpl s, SequenceFixedStringImpl v) {
        //用v替代所有的s
        int index = index(1, s);
        while (index > 0) {
            strDelete(index, s.strLength());
            strInsert(index, v);
            index = index(index + v.strLength(), s);
        }
    }

    @Override
    public void strInsert(int pos, SequenceFixedStringImpl s) {
        if (pos <= 0 || pos > sString[0] + 1 || s.sString[0] + sString[0] > MAX_STRING) {
            throw new RuntimeException("参数不合法");
        }
        int start = strLength();
        if (strLength() + s.strLength() > MAX_STRING) {
            start = MAX_STRING - s.strLength() + 1;
        }
        for (int i = start; i >= pos; i--) {
            sString[i + s.strLength()] = sString[i];
        }

        int end = Math.min(pos + s.strLength() -1, MAX_STRING);
        if (end + 1 - pos >= 0) {
            System.arraycopy(s.sString,  1, sString, pos, end + 1 - pos);
        }
        sString[0] = (char) (strLength() + s.strLength());
    }

    /**
     * 截断
     * @param pos
     * @param len
     */
    @Override
    public void strDelete(int pos, int len) {
        if (pos <= 0 || pos > MAX_STRING || len < 0) {
            throw new RuntimeException("参数不合法");
        }
        if (pos + len - 1 > strLength()) {
            sString[0] = (char) (pos - 1);
        }
        if (strLength() + 1 - pos + len >= 0) {
            System.arraycopy(sString, pos + len, sString, pos + len - len, strLength() + 1 - pos + len);
        }
        sString[0] = (char) (sString[0] - len);
    }
}
