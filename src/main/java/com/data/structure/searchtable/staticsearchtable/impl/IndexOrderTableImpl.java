package com.data.structure.searchtable.staticsearchtable.impl;

import com.data.structure.list.impl.SequenceListImpl;
import com.data.structure.searchtable.model.Element;
import com.data.structure.searchtable.model.IndexNode;

/**
 * IndexOrderTableImpl:
 * 索引顺序表
 *
 * @author sunchen
 * @date 2020/6/23 2:50 下午
 */
public class IndexOrderTableImpl extends AbstractStaticSearchTable<Element> {
    /**
     * 索引表
     */
    IndexNode[] indexArray;

    int min;

    int max;

    public IndexOrderTableImpl(Element[] data) {
        //利用桶排序把数分割成块有序的
        elementData = new Element[data.length + 1];
        length = data.length;
        //1. 计算分多少块
        double chunkSize = Math.ceil(Math.sqrt(data.length));
        indexArray = new IndexNode[(int) chunkSize];
        for (int i = 0; i < indexArray.length; i++) {
            indexArray[i] = new IndexNode();
        }
        //2. 计算每个桶的范围
        getMinAndMax(data);
        double range = Math.ceil((max - min) / chunkSize);
        //3. 初始化桶
        SequenceListImpl[] bucketArray = new SequenceListImpl[(int) chunkSize];
        for (int i = 0; i < bucketArray.length; i++) {
            bucketArray[i] = new SequenceListImpl();
        }
        //4. 将数组里的数分到桶中，并求出桶中的最大值
        for (Element datum : data) {
            //计算在哪个桶里
            int count = (int) Math.floor((datum.intKey - min) / range);
            SequenceListImpl sequenceList = bucketArray[count];
            //插入一个结点
            sequenceList.listInsert(1, datum);
            indexArray[count].numNum++;
            if (indexArray[count].maxKey == null || indexArray[count].maxKey < datum.intKey) {
                indexArray[count].maxKey = datum.intKey;
            }
        }
        //5. 最后将桶中的数据串起来，重置data
        int index = 1;
        for (SequenceListImpl sequenceList : bucketArray) {
            if (sequenceList != null) {
                for (int j = 0; j < sequenceList.length; j++) {
                    elementData[index++] = new Element(((Element) sequenceList.elementData[j]).intKey, ((Element) sequenceList.elementData[j]).value);
                }
            }
        }
        //6. 更新indexArray表中各块的起始
        indexArray[0].startIndex = 1;
        for (int i = 1; i < indexArray.length; i++) {
            indexArray[i].startIndex = indexArray[i - 1].startIndex + indexArray[i - 1].numNum;
        }
    }

    /**
     * 获取最大最小值
     *
     * @param data
     */
    private void getMinAndMax(Element[] data) {
        max = data[0].intKey;
        min = data[0].intKey;
        for (Element datum : data) {
            if (datum.intKey > max) {
                max = datum.intKey;
            }
            if (datum.intKey < min) {
                min = datum.intKey;
            }
        }
    }

    public Element search(Integer key) {
        //先根据索引表，获取到在第几块
        int index;
        for (index = 0; index < indexArray.length; index++) {
            if (key <= indexArray[index].maxKey) {
                break;
            }
        }
        if (index == indexArray.length) {
            throw new RuntimeException("没有找到该元素" + key);
        }
        int startIndex = indexArray[index].startIndex;
        int endIndex = indexArray[index + 1].startIndex;
        for (int i = startIndex; i < endIndex; i++) {
            if (elementData[i].intKey.equals(key)) {
                return elementData[i];
            }
        }
        throw new RuntimeException("没有找到" + key);
    }

    @Override
    public Element search(Object key) {
        if (key instanceof Integer) {
            return search((Integer) key);
        }
        throw new RuntimeException("暂时只支持int类型");
    }
}
