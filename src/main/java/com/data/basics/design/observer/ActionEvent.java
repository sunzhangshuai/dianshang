package com.data.basics.design.observer;


import org.springframework.stereotype.Component;

/**
 * ActionEvent:
 *
 * @author sunchen
 * @date 2020/7/16 11:21 下午
 */
@Component
public class ActionEvent {

    public Model model;

    public ActionEvent(Model model) {
        this.model = model;
    }
}
