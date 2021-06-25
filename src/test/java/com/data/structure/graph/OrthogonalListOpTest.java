package com.data.structure.graph;

import com.data.algorithm.graph.OrthogonalListGraphOp;
import com.data.structure.graph.model.TripleExtend;
import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * AdjacentMatrixTest:
 * 十字链表测试类
 *
 * @author sunchen
 * @date 2020/5/29 4:57 下午
 */
public class OrthogonalListOpTest {
    OrthogonalListGraphOp matrixDirect;
    String[] verTexs;
    TripleExtend[] triples;

    @Test
    public void init() {
        verTexs = new String[]{
                "V1",
                "V2",
                "V3",
                "V4",
                "V5",
                "V6",
                "V7",
                "V8",
                "V9",
                "V10"
        };
        triples = new TripleExtend[]{
                new TripleExtend("V10", "V3"),
                new TripleExtend("V3", "V2"),
                new TripleExtend("V2", "V9"),
                new TripleExtend("V8", "V10"),
                new TripleExtend("V3", "V8"),
                new TripleExtend("V8", "V5"),
                new TripleExtend("V3", "V5"),
                new TripleExtend("V2", "V1"),
                new TripleExtend("V1", "V2"),
                new TripleExtend("V1", "V5"),
                new TripleExtend("V9", "V6"),
                new TripleExtend("V6", "V1"),
                new TripleExtend("V6", "V7"),
                new TripleExtend("V5", "V4"),
                new TripleExtend("V4", "V5")
        };
        // V10--->V3 V3---V2  V2---V9 V8 ---V10
        matrixDirect = new OrthogonalListGraphOp(verTexs, triples);
        matrixDirect.stronglyConnectedComponent();
        Map<Integer, List<String>> expectMap = new LinkedHashMap<Integer, List<String>>();
        LinkedList<String> list1 = new LinkedList<String>();
        list1.add("V3");
        list1.add("V10");
        list1.add("V8");
        LinkedList<String> list2 = new LinkedList<String>();
        list2.add("V1");
        list2.add("V6");
        list2.add("V9");
        list2.add("V2");
        LinkedList<String> list3 = new LinkedList<String>();
        list3.add("V7");
        LinkedList<String> list4 = new LinkedList<String>();
        list4.add("V5");
        list4.add("V4");
        Map<Integer, List<Object>> components = matrixDirect.components;
        expectMap.put(0, list1);
        expectMap.put(1, list2);
        expectMap.put(2, list3);
        expectMap.put(3, list4);
        Assert.assertEquals(expectMap, components);
    }
}
