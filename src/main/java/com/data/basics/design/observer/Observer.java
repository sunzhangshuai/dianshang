package com.data.basics.design.observer;

/**
 * Observer:
 *
 * @author sunchen
 * @date 2020/7/16 11:18 下午
 */
public interface Observer {
    /**
     * 观察者
     * @param event
     * @return
     */
    void actionEvent(ActionEvent event);
}
