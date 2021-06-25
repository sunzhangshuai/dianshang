package com.data.structure.graph.model.vertex;

/**
 * VerTex:
 * 顶点-抽象类
 *
 * @author sunchen
 * @date 2020/5/29 11:17 上午
 */
public abstract class AbstractVerTex {
    /**
     * 顶点名称
     */
    public Object data;

    /**
     * 顶点值域
     *
     * @param data
     */
    public AbstractVerTex(Object data) {
        this.data = data;
    }
}
