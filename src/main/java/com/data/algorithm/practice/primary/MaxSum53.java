package com.data.algorithm.practice.primary;

/**
 * MaxSum53:
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 *
 * @author sunchen
 * @date 2020/7/6 11:07 下午
 */
public class MaxSum53 {
    public static void main(String[] args) {
        int a[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int max = maxSumDynamicProgram(a);
        System.out.println(max);
    }

    public static int[] sum(int[] numbers) {
        int start = 0;
        int maxStart = 0;
        int maxEnd = 0;
        int maxSum = numbers[0];
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            if (sum <= 0) {
                start = i + 1;
                sum = 0;
            } else if (sum > maxSum) {
                maxSum = sum;
                maxEnd = i;
                maxStart = start;
            }
        }
        int[] newArray = new int[maxEnd - maxStart + 1];
        if (newArray.length >= 0) {
            System.arraycopy(numbers, maxStart, newArray, 0, newArray.length);
        }
        return newArray;
    }

    public static int maxSumDynamicProgram(int[] numbers) {
        int[] result = new int[numbers.length];
        result[0] = numbers[0];
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result[i] = Math.max(result[i - 1] + numbers[i], numbers[i]);
            max = Math.max(max, result[i]);
        }
        return max;
    }

    public static int maxSumDivide(int[] numbers, int start, int end) {
        if (start == end) {
            return numbers[start];
        }
        //求左边最大子序和
        int mid = (start + end) / 2;
        int leftMax = maxSumDivide(numbers, start, mid);
        //求右边最大子序和
        int rightMax = maxSumDivide(numbers, mid + 1, end);
        //求中间最大值
        int leftMiddleSum = numbers[mid];
        int rightMiddleSum = numbers[mid + 1];
        int leftSum = 0;
        int rightSum = 0;
        for (int i = mid; i >= start; i--) {
            leftSum += numbers[i];
            if (leftSum > leftMiddleSum) {
                leftMiddleSum = leftSum;
            }
        }

        for (int i = mid + 1; i <= end; i++) {
            rightSum += numbers[i];
            if (rightSum > rightMiddleSum) {
                rightMiddleSum = leftSum;
            }
        }
        int middleMax = leftMiddleSum + rightMiddleSum;
        return getMax(leftMax, middleMax, rightMax);
    }

    public static int getMax(int a1, int a2, int a3) {
        return Math.max(a1, Math.max(a2, a3));
    }


}
