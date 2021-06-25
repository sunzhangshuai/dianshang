package com.data.structure.graph.model;

import java.util.Objects;

/**
 * @author sunchen
 * 三元组
 */
public class TripleExtend {

    public Object arcHead;
    public Object arcTail;
    public Integer weight;

    public TripleExtend(Object arcTail, Object arcHead, Integer e) {
        this.arcHead = arcHead;
        this.arcTail = arcTail;
        this.weight = e;
    }

    public TripleExtend(Object arcTail, Object arcHead) {
        this.arcTail = arcTail;
        this.arcHead = arcHead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TripleExtend that = (TripleExtend) o;
        return (arcHead).equals(that.arcHead) &&
                arcTail.equals(that.arcTail) &&
                weight.equals(that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arcHead, arcTail, weight);
    }
}
