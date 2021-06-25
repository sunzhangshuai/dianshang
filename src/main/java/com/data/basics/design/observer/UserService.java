package com.data.basics.design.observer;

import com.alibaba.fastjson.JSONObject;
import com.data.config.RedisConfig;
import com.data.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * UserService:
 *
 * @author sunchen
 * @date 2020/7/22 11:58 下午
 */
@Service
public class UserService {

    @Autowired
    ActionEvent actionEvent;

    @Autowired
    SavedObserver savedObserver;

    @Autowired
    UpdateObserver updateObserver;

    @Autowired
    DeleteObserver deleteObserver;

    @Autowired
    SelectObserver selectObserver;

    @Autowired
    RedisConfig redisConfig;

    @Autowired
    RedisUtil redisUtil;

    List<Observer> observerList = new ArrayList<Observer>();

    @PostConstruct
    public void init() {
        addObserver();
    }

    public void insert(int id, String name) {
        Model queryModel = query(id);
        if (queryModel == null) {
            Model model = new Model();
            model.id = id;
            model.name = name;
            redisUtil.hset(redisConfig.redisKey, String.valueOf(id), JSONObject.toJSONString(model));
            ActionEvent event = new ActionEvent(model);
            savedObserver.actionEvent(event);
        }
    }

    public void delete(int id) {
        Model model = query(id);
        if (model != null) {
            redisUtil.hdel(redisConfig.redisKey, String.valueOf(id));
            deleteObserver.actionEvent(new ActionEvent(model));
        }
    }

    public void update(int id, String name) {
        Model model = query(id);
        if (model == null) {
            return;
        }
        model.name = name;
        redisUtil.hset(redisConfig.redisKey, String.valueOf(id), JSONObject.toJSONString(model));
        ActionEvent event = new ActionEvent(model);
        savedObserver.actionEvent(event);
        updateObserver.actionEvent(event);
    }

    public Model query(int id) {
        String hget = (String) redisUtil.hget(redisConfig.redisKey, String.valueOf(id));
        Model model = JSONObject.parseObject(hget, Model.class);
        if (model != null) {
            selectObserver.actionEvent(new ActionEvent(model));
        }
        return model;
    }

    private void addObserver() {
        observerList.add(new SavedObserver());
        observerList.add(new DeleteObserver());
        observerList.add(new UpdateObserver());
        observerList.add(new SelectObserver());
    }
}
