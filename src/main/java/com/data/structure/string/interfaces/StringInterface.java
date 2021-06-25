package com.data.structure.string.interfaces;

/**
 * 字符串接口
 * @author sunchen
 */
public interface StringInterface<T> {

    /**
     * 拷贝
     * @param s
     * @throws CloneNotSupportedException
     */
    void strCopy(T s) throws CloneNotSupportedException;

    /**
     * 判断字符串是否为空
     * @return
     */
    boolean strEmpty();

    /**
     * 比较字符串
     * @param s
     * @return
     */
    int strCompare(T s);

    /**
     * 获取字符串长度
     * @return
     */
    int strLength();

    /**
     * 清空字符串
     */
    void clearString();

    /**
     * 字符串拼接
     * @param v
     * @return
     * @throws CloneNotSupportedException
     */
    T concat(T v) throws CloneNotSupportedException;

    /**
     * 截取字符串
     * @param pos
     * @param len
     * @return
     */
    T subString(int pos, int len);

    /**
     * 从第pos个字符起，查找是否有串s
     * @param pos
     * @param s
     * @return
     */
    int index(int pos, T s);

    /**
     * 用v替换所有与s相等的
     * @param s
     * @param v
     * @throws CloneNotSupportedException
     */
    void replace(T s, T v) throws CloneNotSupportedException;

    /**
     * 在第pos个字符前插入s
     * @param pos
     * @param s
     * @throws CloneNotSupportedException
     */
    void strInsert(int pos, T s) throws CloneNotSupportedException;

    /**
     * 从第pos个字符起删除len长度个字符
     * @param pos
     * @param len
     * @throws CloneNotSupportedException
     */
    void strDelete(int pos, int len) throws CloneNotSupportedException;
}
