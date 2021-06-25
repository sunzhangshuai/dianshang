package com.data.algorithm.practice.primary;

/**
 * RemoveDuplicateElements:
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author sunchen
 * @date 2020/7/22 3:04 下午
 */
public class RemoveDuplicate26 {
    /**
     * 1，1，2，2，3，4，4，5
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int length = handler(array);
        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }
    }

    public static int handler(int[] nums) {
        int slow = 0;
        int fast = 1;
        int length = nums.length;
        while (fast<length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
