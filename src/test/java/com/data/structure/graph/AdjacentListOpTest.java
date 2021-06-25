package com.data.structure.graph;

import com.data.algorithm.graph.AdjacentListGraphOp;
import com.data.structure.graph.model.GraphType;
import com.data.structure.graph.model.TripleExtend;
import com.data.structure.tree.impl.binarytree.BinaryTreeImpl;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * AdjacentMatrixTest:
 * 邻接矩阵测试类
 * @author sunchen
 * @date 2020/5/29 4:57 下午
 */
public class AdjacentListOpTest {
    AdjacentListGraphOp matrixDirect;

    String[] verTexs;
    TripleExtend[] triples;
    @Before
    public void init() {
        verTexs = new String[]{
                "V1",
                "V2",
                "V3",
                "V4",
                "V5"
        };

        triples = new TripleExtend[]{
                new TripleExtend("V1", "V2", 1),
                new TripleExtend("V1", "V4", 1),
                new TripleExtend("V2", "V3", 1),
                new TripleExtend("V2", "V1", 1),
                new TripleExtend("V4", "V3", 1),
                new TripleExtend("V3", "V5", 1),
                new TripleExtend("V5", "V4", 1),
        };

        matrixDirect = new AdjacentListGraphOp(GraphType.Directed, verTexs, triples);
        Integer[][] array = new Integer[][]{
                new Integer[]{0, 1, 0, 1, 0},
                new Integer[]{1, 0, 1, 0, 0},
                new Integer[]{0, 0, 0, 0, 1},
                new Integer[]{0, 0, 1, 0, 0},
                new Integer[]{0, 0, 0, 1, 0},
        };
        Assert.assertArrayEquals(matrixDirect.toArray(), array);
    }

    @Test
    public void dfs(){
        matrixDirect.dfsTraverse();
        List<String> list = Lists.newArrayList();
        list.add("V1");
        list.add("V4");
        list.add("V3");
        list.add("V5");
        list.add("V2");
        Assert.assertEquals(matrixDirect.data, list);
    }

    @Test
    public void bfs(){
        matrixDirect.bfsTraverse();
        List<String> list = Lists.newArrayList();
        list.add("V1");
        list.add("V4");
        list.add("V2");
        list.add("V3");
        list.add("V5");
        Assert.assertEquals(matrixDirect.data, list);
    }
    @Test
    public void dfsForestTest(){
        BinaryTreeImpl tree = matrixDirect.dfsForest();
        tree.preOrderTraverse();
    }
    @Test
    public void bfsForestTest(){
        //V1 V4 V2 V3 V5
        BinaryTreeImpl tree = matrixDirect.bfsForest();
        tree.preOrderTraverse();
    }

    @Test
    public void testArticul(){
        verTexs = new String[]{
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
        };

        triples = new TripleExtend[]{
                new TripleExtend("A", "F"),
                new TripleExtend("A", "B"),
                new TripleExtend("A", "C"),
                new TripleExtend("A", "L"),
                new TripleExtend("B", "C"),
                new TripleExtend("B", "D"),
                new TripleExtend("B", "G"),
                new TripleExtend("B", "H"),
                new TripleExtend("B", "M"),
                new TripleExtend("D", "E"),
                new TripleExtend("G", "H"),
                new TripleExtend("G", "I"),
                new TripleExtend("G", "K"),
                new TripleExtend("H", "K"),
                new TripleExtend("J", "L"),
                new TripleExtend("J", "M"),
                new TripleExtend("L", "M"),
        };

        matrixDirect = new AdjacentListGraphOp(GraphType.UnDirected, verTexs, triples);
        matrixDirect.findArticul();
        List<String> list = Lists.newArrayList();
        list.add("G");
        list.add("B");
        list.add("D");
        list.add("A");
        Assert.assertEquals(matrixDirect.vexName,list);
    }

    @Test
    public void testTopologicalSort () {
        verTexs = new String[]{
                "V1", "V2", "V3", "V4", "V5", "V6"
        };

        triples = new TripleExtend[]{
                new TripleExtend("V1", "V2"),
                new TripleExtend("V1", "V3"),
                new TripleExtend("V1", "V4"),
                new TripleExtend("V3", "V2"),
                new TripleExtend("V4", "V5"),
                new TripleExtend("V6", "V4"),
                new TripleExtend("V6", "V5")
        };

        matrixDirect = new AdjacentListGraphOp(GraphType.Directed, verTexs, triples);
        matrixDirect.topologicalSort();
        List<String> list = Lists.newArrayList();
        list.add("V6");
        list.add("V1");
        list.add("V3");
        list.add("V2");
        list.add("V4");
        list.add("V5");
        Assert.assertEquals(matrixDirect.vexName,list);
    }

    @Test
    public void testCriticalPath () {
        verTexs = new String[]{
                "V1", "V2", "V3", "V4", "V5", "V6"
        };

        triples = new TripleExtend[]{
                new TripleExtend("V1", "V2", 3),
                new TripleExtend("V1", "V3", 2),
                new TripleExtend("V2", "V4", 2),
                new TripleExtend("V3", "V4", 4),
                new TripleExtend("V2", "V5", 3),
                new TripleExtend("V3", "V6", 3),
                new TripleExtend("V4", "V6", 2),
                new TripleExtend("V5", "V6", 1)

        };

        matrixDirect = new AdjacentListGraphOp(GraphType.Directed, verTexs, triples);
        matrixDirect.criticalPath();
        String[][] array = new String[][]{
                new String[]{"V1", "V3", "2"},
                new String[]{"V3", "V4", "4"},
                new String[]{"V4", "V6", "2"},
        };
        String[][] result_array = new String[3][3];
        List<TripleExtend> result = matrixDirect.edgeList;
        for (int i = 0; i < result.size(); i++) {
            result_array[i][0] = (String) result.get(i).arcTail;
            result_array[i][1] = (String) result.get(i).arcHead;
            result_array[i][2] = String.valueOf(result.get(i).weight);
        }
        Assert.assertArrayEquals(array, result_array);
    }
}
