package com.data.algorithm.practice.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ThreeSum15:
 *
 * @author sunchen
 * @date 2020/7/15 1:28 下午
 */
public class ThreeSum15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        //排序
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int j = 0;
            for (j = i; j > 0; j--) {
                if (temp < nums[j - 1]) {
                    nums[j] = nums[j - 1];
                } else {
                    break;
                }
            }
            nums[j] = temp;
        }
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; ) {
            int low = i + 1;
            int high = nums.length - 1;
            int needKey = -nums[i];
            while (low < high) {
                int sum = nums[low] + nums[high];
                if (sum < needKey) {
                    low++;
                } else if (sum > needKey) {
                    high--;
                } else {
                    List<Integer> result = new ArrayList<Integer>();
                    result.add(nums[i]);
                    result.add(nums[low]);
                    result.add(nums[high]);
                    list.add(result);
                    low++;
                    high--;
                }
            }
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i = i + 2;
            } else {
                i = i + 1;
            }
        }
        return list;
    }


    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, 3, 1, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }
}
