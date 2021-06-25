package com.data.basics.design.observer;

import org.springframework.stereotype.Component;

/**
 * DeleteObserver:
 *
 * @author sunchen
 * @date 2020/7/23 12:24 上午
 */
@Component
public class DeleteObserver implements Observer {

    @Override
    public void actionEvent(ActionEvent event) {
        //被删除了
        System.out.println(event.model + "被删除了");
    }
}
