package com.data.algorithm.practice.primary;

import com.data.structure.searchtable.hashtable.confictmanage.LinearProbing;
import com.data.structure.searchtable.hashtable.hash.ModHash;
import com.data.structure.searchtable.hashtable.table.AbstractHashTable;
import com.data.structure.searchtable.hashtable.table.OpenAddressingImpl;
import com.data.structure.searchtable.model.Element;

/**
 * TowSum1:
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * @author sunchen
 * @date 2020/7/7 11:20 下午
 */
public class TowSum1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 7, 15};
        int[] array = twoSum(nums, 9);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        AbstractHashTable hashTable = new OpenAddressingImpl(new LinearProbing(), ModHash.getInstance());
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            int needValue = target - key;
            if (hashTable.search(needValue) != null) {
                Element search = hashTable.search(needValue);
                return new int[]{i, Integer.parseInt(search.value)};
            } else {
                Element element = new Element(key, String.valueOf(i));
                hashTable.insert(element);
            }
        }
        return result;
    }


}
