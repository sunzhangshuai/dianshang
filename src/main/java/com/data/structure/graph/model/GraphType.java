package com.data.structure.graph.model;


/**
 * 图的类型
 * @author sunchen
 */

public enum GraphType {
    /**
     * 有向图
     */
    Directed(0,"有向图"),

    /**
     * 无向图
     */
    UnDirected(1,"无向图"),

    /**
     * 有向网
     */
    DirectedNetWork(2,"有向网"),

    /**
     * 无向网
     */
    UnDirectedNetWork(3,"无向网");
    public int code;
    public String desc;
    GraphType(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static GraphType getKindByCode(int code){
        GraphType[] values = values();
        for(GraphType graphType: values){
            if (graphType.code == code) {
                return graphType;
            }
        }
        return Directed;
    }
}
