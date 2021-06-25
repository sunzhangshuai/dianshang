package com.data.structure.sort;

import com.data.structure.list.impl.SequenceListImpl;

/**
 * 桶排序
 * @author sunchen
 */
public class BucketSort extends Sort{

    @Override
    public void sort(int[] array) {
        //获取最大值和最小值
        int[] maxMin = getMaxMin(array);
        int max = maxMin[0], min = maxMin[1];

        //计算每个桶存储数据的范围
        int itemRange = (int) Math.ceil((double) (max - min)/array.length);

        // 初始化桶，每个桶里放一个空链表
        SequenceListImpl[] nodes = new SequenceListImpl[array.length];
        for (int i = 0; i < array.length; i++) {
            nodes[i]  = new SequenceListImpl();
        }

        for (int i = 0; i < array.length; i++) {
            int index = (array[i] - min)/itemRange;
            nodes[index].listInsert(1, array[i]);
        }
        int index = 0;
        for (int i = 0; i < nodes.length; i++) {
            Integer[] groupArray = (Integer[]) nodes[i].toArray();
            //对数组进行排序
            InsertSort sort = new InsertSort();
            sort.sort(groupArray);
            for (int k = 0; k < groupArray.length; k++,index++) {
                array[index] = groupArray[k];
            }
        }
    }
}
