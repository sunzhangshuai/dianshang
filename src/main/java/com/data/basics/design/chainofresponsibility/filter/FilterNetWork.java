package com.data.basics.design.chainofresponsibility.filter;

import org.springframework.util.StringUtils;

/**
 * FilterNetWork:
 *
 * @author sunchen
 * @date 2020/7/15 7:32 下午
 */
public class FilterNetWork implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (request == null || StringUtils.isEmpty(request.network)) {
            response.success = false;
            response.msg = "网络验证失败";
            return;
        }
        filterChain.doFilter(request,response);
        if (response.success) {
            response.msg += "网络验证通过\n";
        }
    }
}
