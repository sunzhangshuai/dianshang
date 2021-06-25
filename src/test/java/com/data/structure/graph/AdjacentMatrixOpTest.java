package com.data.structure.graph;

import com.data.algorithm.graph.AdjacentMatrixGraphOp;
import com.data.structure.graph.model.GraphType;
import com.data.structure.graph.model.TripleExtend;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

/**
 * AdjacentMatrixTest:
 * 邻接矩阵测试类
 *
 * @author sunchen
 * @date 2020/5/29 4:57 下午
 */
public class AdjacentMatrixOpTest {
    AdjacentMatrixGraphOp matrixDirect;

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
                "V6"
        };

        triples = new TripleExtend[]{
                new TripleExtend("V1", "V2", 6),
                new TripleExtend("V1", "V3", 1),
                new TripleExtend("V1", "V4", 5),
                new TripleExtend("V3", "V2", 5),
                new TripleExtend("V3", "V4", 5),
                new TripleExtend("V3", "V5", 6),
                new TripleExtend("V3", "V6", 4),
                new TripleExtend("V2", "V5", 3),
                new TripleExtend("V4", "V6", 2),
                new TripleExtend("V5", "V6", 6)
        };


        matrixDirect = new AdjacentMatrixGraphOp(GraphType.UnDirectedNetWork, verTexs, triples);
        String[][] array = new String[][]{
                new String[]{"V1", "V3", "1"},
                new String[]{"V3", "V6", "4"},
                new String[]{"V6", "V4", "2"},
                new String[]{"V3", "V2", "5"},
                new String[]{"V2", "V5", "3"},
        };
        List<TripleExtend> result = matrixDirect.miniSpanTreePrim("V1");
        String[][] result_array = new String[5][3];
        for (int i = 0; i < result.size(); i++) {
            result_array[i][0] = (String) result.get(i).arcTail;
            result_array[i][1] = (String) result.get(i).arcHead;
            result_array[i][2] = String.valueOf(result.get(i).weight);
        }
        Assert.assertArrayEquals(array, result_array);
    }

    /**
     * 测试求最短路径
     */
    @Test
    public void test(){
        verTexs = new String[]{
                "V0",
                "V1",
                "V2",
                "V3",
                "V4",
                "V5"
        };

        triples = new TripleExtend[]{
                new TripleExtend("V0", "V2", 10),
                new TripleExtend("V0", "V4", 30),
                new TripleExtend("V0", "V5", 100),
                new TripleExtend("V1", "V2", 5),
                new TripleExtend("V2", "V3", 50),
                new TripleExtend("V3", "V5", 10),
                new TripleExtend("V4", "V3", 20),
                new TripleExtend("V4", "V5", 60),

        };
        matrixDirect = new AdjacentMatrixGraphOp(GraphType.DirectedNetWork, verTexs, triples);
        matrixDirect.shortestPathDlj(0);
        int[] dArray = new int[]{
                0, 1000000, 10, 50, 30, 60
        };
        Assert.assertArrayEquals(dArray, matrixDirect.d);
        boolean[][] array = new boolean[][]{
                new boolean[]{true, false, false, false, false, false},
                new boolean[]{false, false, false, false, false, false},
                new boolean[]{true, false, true, false, false, false},
                new boolean[]{true, false, false, true, true, false},
                new boolean[]{true, false, false, false, true, false},
                new boolean[]{true, false, false, true, true, true},};
        boolean[][] result = matrixDirect.p;
        Assert.assertArrayEquals(array, result);
    }

    /**
     * 测试求最短路径
     */
    @Test
    public void test2(){
        verTexs = new String[]{
                "V0",
                "V1",
                "V2"
        };

        triples = new TripleExtend[]{
                new TripleExtend("V0", "V2", 10),
                new TripleExtend("V1", "V2", 5),
                new TripleExtend("V2", "V0", 50)

        };
        matrixDirect = new AdjacentMatrixGraphOp(GraphType.DirectedNetWork, verTexs, triples);
        matrixDirect.shortestFloyd();
        Integer[][] dArray = new Integer[][]{
                new Integer[]{0, 1000000, 10},
                new Integer[]{55, 0, 5},
                new Integer[]{50, 1000000, 0},
        };
        Assert.assertArrayEquals(dArray, matrixDirect.dFloyd);
        boolean[] array = new boolean[27];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    array[index] = matrixDirect.pFloyd[i][j][k];
                    index++;
                }
            }
        }
        boolean[] expectArray = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                false,
                true,

                true,
                true,
                true,

                false,
                false,
                false,

                false,
                true,
                true,

                true,
                false,
                true,

                false,
                false,
                false,

                false,
                false,
                false,
        };
        Assert.assertArrayEquals(array, expectArray);
    }
}
