package com.data.structure.list.model;

/**
 * 项
 * @author sunchen
 */
public class Term {
    /**
     * 系数
     */
    public float coef;

    /**
     * 指数
     */
    public int expn;

    /**
     * 构造器
     * @param coef
     * @param expn
     */
    public Term(float coef, int expn) {
        this.coef = coef;
        this.expn = expn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Term term = (Term) o;
        return expn == term.expn;
    }
}
