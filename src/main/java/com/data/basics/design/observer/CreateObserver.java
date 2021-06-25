package com.data.basics.design.observer;

import org.springframework.stereotype.Component;

/**
 * CreateObserver:
 *
 * @author sunchen
 * @date 2020/7/23 1:41 上午
 */
@Component
public class CreateObserver implements Observer {

    @Override
    public void actionEvent(ActionEvent event) {
        //id创建了
        Model model = event.model;
        System.out.println(model + "创建了");
    }
}
