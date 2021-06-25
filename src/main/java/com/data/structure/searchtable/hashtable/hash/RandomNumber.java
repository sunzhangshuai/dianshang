package com.data.structure.searchtable.hashtable.hash;

import com.data.structure.searchtable.hashtable.table.AbstractHashTable;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.DigestUtils;

/**
 * RandomNumber:
 * 随机数法
 *
 * @author sunchen
 * @date 2020/7/13 12:11 上午
 */
public class RandomNumber extends AbstractHashFun{
    private static final RandomNumber RANDOM_NUMBER = new RandomNumber();
    private RandomNumber(){}

    public static RandomNumber getInstance(){
        return RANDOM_NUMBER;
    }
    @Override
    public int handle(AbstractHashTable hashTable, Object key) {
        String strKey = DigestUtils.md5DigestAsHex(String.valueOf(key).getBytes());
        return getHashCode(strKey) % hashTable.tableLength();
    }

    public static void main(String[] args) {
        String encode = MD5Encoder.encode(("9").getBytes());
        System.out.println(encode);
    }
}
