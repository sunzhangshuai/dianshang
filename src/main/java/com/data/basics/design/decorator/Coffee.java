package com.data.basics.design.decorator;

/**
 * Coffee:
 *
 * @author sunchen
 * @date 2020/7/15 4:42 下午
 */
public interface Coffee {
    /**
     * 获取咖啡价格
     * @return
     */
    double getCost();

    /**
     * 获取咖啡材料
     * @return
     */
    String getIngredients();
}
