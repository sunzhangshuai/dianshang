package com.data.basics.design.observer;

import com.data.config.RedisConfig;
import com.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SelectObserver:
 *
 * @author sunchen
 * @date 2020/7/23 12:24 上午
 */
@Component
public class SelectObserver implements Observer {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    RedisConfig redisConfig;

    @Override
    public void actionEvent(ActionEvent event) {
        Model model = event.model;
        if (model != null) {
            redisUtil.incr(redisConfig.redisCountKey + "query" + model.id,1);
        }
        System.out.println(model + "被查询了" + redisUtil.get(redisConfig.redisCountKey + "query" + model.id) + "次");
    }
}
