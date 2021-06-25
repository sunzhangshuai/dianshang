package com.data.basics.design.observer;

import com.data.config.RedisConfig;
import com.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UpdateObserver:
 *
 * @author sunchen
 * @date 2020/7/23 12:23 上午
 */
@Component
public class UpdateObserver implements Observer {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisConfig redisConfig;

    @Override
    public void actionEvent(ActionEvent event) {
        int id = event.model.id;
        redisUtil.incr(redisConfig.redisCountKey + "update" + id,1);
        System.out.println(id + "被更新了" + redisUtil.get(redisConfig.redisCountKey + "update" + id) + "次");
    }
}
