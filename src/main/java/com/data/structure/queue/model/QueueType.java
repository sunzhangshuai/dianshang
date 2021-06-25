package com.data.structure.queue.model;

/**
 * 队列类型
 *
 * @author sunchen
 */
public enum QueueType {
    /**
     * 头进尾出
     */
    FrontEnRearDe(1, "头进尾出"),
    /**
     * 尾进头出
     */
    FrontDeRearEn(0, "尾进头出");

    public int code;
    public String desc;

    QueueType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
