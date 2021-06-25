package com.data.basics.design.chainofresponsibility.route;

import java.util.List;

/**
 * ValidatorNetWork:
 *
 * @author sunchen
 * @date 2020/7/22 4:30 下午
 */
public class ValidatorDns implements Validator{
    @Override
    public boolean valid(List<String> result) {
        result.add("域名测试通过");
        return true;
    }
}
