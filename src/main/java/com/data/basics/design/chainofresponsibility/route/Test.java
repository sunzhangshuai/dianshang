package com.data.basics.design.chainofresponsibility.route;

import java.util.ArrayList;
import java.util.List;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/22 4:31 下午
 */
public class Test {
    public static void main(String[] args) {
        ValidatorDns validatorDns = new ValidatorDns();
        ValidatorNetWork validatorNetWork = new ValidatorNetWork();
        ValidatorParams validatorParams = new ValidatorParams();
        ValidChain validChain = new ValidChain();
        validChain.add(validatorDns);
        validChain.add(validatorNetWork);
        validChain.add(validatorParams);


    }
}
