package com.data.basics.design.chainofresponsibility.route;

import java.util.List;

/**
 * Validtor:
 *
 * @author sunchen
 * @date 2020/7/22 4:28 下午
 */
public interface Validator {
    /**
     * 验证
     * @return
     * @param result
     */
    boolean valid(List<String> result);
}
