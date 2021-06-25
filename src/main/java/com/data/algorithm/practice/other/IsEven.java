package com.data.algorithm.practice.other;

/**
 * IsEven:
 * 把数组中的奇数全部都放到数组的最前面
 * @author sunchen
 * @date 2020/7/27 5:24 下午
 */
public class IsEven {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,5,6,8,9};
        handler(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void handler(int [] nums) {
        int length = nums.length-1;
        int i = 0;
        while (i <= length){
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[length];
                nums[length] = temp;
                length--;
            } else {
                i++;
            }
        }
    }
}
