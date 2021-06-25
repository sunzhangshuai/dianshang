package com.data.structure.searchtable.model;

/**
 * Element:
 *
 * @author sunchen
 * @date 2020/6/19 2:16 下午
 */
public class Element implements Cloneable {
    /**
     * 关键字
     */
    public String strKey;

    public Integer intKey;

    public Double douKey;

    public Class<?> aClass;

    /**
     * 值
     */
    public String value;

    /**
     * 记录该条记录被访问的次数
     */
    public int searchTime;

    /**
     * 权值，用于生成静态生成树
     */
    public int weight;

    public boolean isDelete = false;

    public Element(Object key, String value) {
        setKey(key);
        this.value = value;
        this.searchTime = 0;
    }

    public Element(Object key, String value, int weight) {
        setKey(key);
        this.value = value;
        this.weight = weight;
    }

    public Element() {
    }

    @Override
    public Element clone() throws CloneNotSupportedException {
        return (Element) super.clone();
    }

    /**
     * 比较
     *
     * @param key
     * @return
     */
    public int equalKey(Object key) {
        if (key instanceof Integer) {
            return intKey.compareTo((Integer) key);
        } else if (key instanceof String) {
            return strKey.compareTo((String) key);
        } else if (key instanceof Double) {
            return douKey.compareTo((Double) key);
        } else {
            throw new RuntimeException("不支持其他类型比较");
        }
    }

    public Object getKey() {
        if (aClass == Integer.class) {
            return this.intKey;
        } else if (aClass == String.class) {
            return this.strKey;
        } else if (aClass == Double.class) {
            return this.douKey;
        } else {
            throw new RuntimeException("不支持其他数据类型");
        }
    }

    public void setKey(Object key) {
        aClass = key.getClass();
        if (aClass != Integer.class && aClass != String.class && aClass != Double.class) {
            throw new RuntimeException("不支持其他数据类型");
        }
        this.strKey = String.valueOf(key);
        try {
            this.intKey = Integer.parseInt(strKey);
            this.douKey = Double.parseDouble(strKey);
        } catch (Exception exception) {
            this.intKey = 0;
            this.douKey = 0.0;
        }
    }
}
