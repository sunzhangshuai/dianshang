package com.data.basics.design.chainofresponsibility.filter;

import org.springframework.util.StringUtils;

/**
 * FilterParam:
 *
 * @author sunchen
 * @date 2020/7/15 7:31 下午
 */
public class FilterParam implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.isParam = true;
        if (request == null || StringUtils.isEmpty(request.param)) {
            response.success = false;
            response.msg = "参数验证失败";
            return;
        }
        filterChain.doFilter(request, response);
        if (response.success) {
            response.msg += "参数验证通过\n";
        }
    }
}
