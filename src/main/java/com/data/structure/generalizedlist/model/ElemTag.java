package com.data.structure.generalizedlist.model;

/**
 * 结点类型
 * @author sunchen
 */
public enum ElemTag {
    /**
     * 原子类型
     */
    ATOM(0,"原子"),
    /**
     * 子表类型
     */
    LIST(1,"子表");
    public int code;
    public String desc;
    ElemTag(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
