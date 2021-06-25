package com.data.structure.list.interfaces;

import com.data.structure.list.impl.PolynomialImpl;

/**
 * 一元多项式
 * @author sunchen
 */
public interface Polynomial {
    /**
     * 转为字符串
     * @return 字符串
     */
    @Override
    String toString();

    /**
     * 获取项数
     * @return 项数
     */
    int polynLength();

    /**
     * 相加
     * @param polynomial 要加的多项式
     * @throws CloneNotSupportedException 异常
     */
    void addPolyn(PolynomialImpl polynomial) throws CloneNotSupportedException;

    /**
     * 相减
     * @param polynomial 要减的多项式
     * @throws CloneNotSupportedException 异常
     */
    void subtractPolyn(PolynomialImpl polynomial) throws CloneNotSupportedException;

    /**
     * 相乘
     * @param polynomial 要乘的多项式
     */
    void multiplyPolyn(PolynomialImpl polynomial);
}
