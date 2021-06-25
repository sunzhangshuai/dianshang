package com.data.basics.design.chainofresponsibility.route;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidChain:
 *
 * @author sunchen
 * @date 2020/7/22 4:27 下午
 */
public class ValidChain {
    List<Validator> list = new ArrayList<Validator>();
    public void add(Validator validator){
        list.add(validator);
    }
    public List<String> handler(List<String> request, List<String> response){
        for (Validator validator : list) {
            boolean valid = validator.valid(response);
            if (!valid) {
                break;
            }
        }
        return response;
    }
}
