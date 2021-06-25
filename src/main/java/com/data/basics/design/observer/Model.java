package com.data.basics.design.observer;

import org.springframework.stereotype.Component;

/**
 * Model:
 *
 * @author sunchen
 * @date 2020/7/23 1:37 上午
 */
@Component
public class Model {
    public int id;
    public String name;

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
