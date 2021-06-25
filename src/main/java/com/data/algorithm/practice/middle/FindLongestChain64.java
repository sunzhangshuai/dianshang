package com.data.algorithm.practice.middle;


/**
 * FindLongestChain64:
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * [[1,2], [2,3], [3,4]]
 *
 * @author sunchen
 * @date 2020/7/24 12:01 上午
 */
public class FindLongestChain64 {
    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2}, {2, 3}, {3, 4}};
        int[][] result = handler(array);
        for (int i = 0; i < result.length; i++) {
            for(int j = 0; j < 2; j++){
                System.out.print(result[i][j]+"-");
            }
            System.out.println("\n");
        }
    }

    public static int[][] handler(int[][] pairs) {
        for (int i = 0; i < pairs.length; i++) {
            int value = pairs[i][1];
            int j = 0;
            for (j = i; j > 0; j--) {
                if (pairs[j][1] > value) {
                    pairs[j + 1] = pairs[j];
                } else {
                    break;
                }
            }
            pairs[i] = pairs[j];
        }
        int[][] newArray = new int[pairs.length][2];
        newArray[0] = pairs[0];
        int index = 1;
        int slow = 0;
        int fast = 1;
        while (fast < pairs.length) {
            if (pairs[slow][1] < pairs[fast][0]) {
                slow = fast;
                newArray[index++] = pairs[fast];
            }
            fast++;
        }
        return newArray;
    }
}
