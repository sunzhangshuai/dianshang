package com.data.basics.design.observer;

import com.data.config.RedisConfig;
import com.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sunchen
 */
@Component
public class SavedObserver implements Observer {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisConfig redisConfig;

    @Override
    public void actionEvent(ActionEvent event) {
        //id这条记录保存的次数
        int id = event.model.id;
        redisUtil.incr(redisConfig.redisCountKey + "insert" + id,1);
        System.out.println(id + "被保存了" + redisUtil.get(redisConfig.redisCountKey + "insert" + id) + "次");
    }
}
