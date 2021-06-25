package com.data.algorithm.practice.middle;

/**
 * Search33:
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * @author sunchen
 * @date 2020/7/26 4:31 下午
 */
public class Search33 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int result = handler(nums, 2);
        System.out.println(result);
    }

    public static int handler(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        int rotate = rotationPoint(nums, low, high);
        if (target == nums[low]) {
            return low;
        } else if (target > nums[low]) {
            return getIndex2(low, rotate, nums, target);
        } else {
            int start = rotate + 1;
            return getIndex2(start, high, nums, target);
        }
    }

    public static int rotationPoint(int[] nums, int low, int high) {
        if (nums[low] <= nums[high]) {
            return high;
        }
        int mid = (low + high) / 2;
        if (nums[low] > nums[mid]) {
            return rotationPoint(nums, low, mid);
        } else if (nums[mid + 1] > nums[high]) {
            return rotationPoint(nums, mid + 1, high);
        } else {
            return mid;
        }
    }

    public static int getIndex(int start, int end, int[] nums, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int getIndex2(int start, int end, int[] nums, int target) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] > target) {
            return getIndex2(start, mid - 1, nums, target);
        } else if (nums[mid] == target) {
            return mid;
        } else {
            return getIndex2(mid + 1, end, nums, target);
        }
    }
}
