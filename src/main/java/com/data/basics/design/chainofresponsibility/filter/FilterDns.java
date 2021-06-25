package com.data.basics.design.chainofresponsibility.filter;

import org.springframework.util.StringUtils;

/**
 * FilterDns:
 *
 * @author sunchen
 * @date 2020/7/15 7:32 下午
 */
public class FilterDns implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (request == null || StringUtils.isEmpty(request.dns)) {
            response.success = false;
            response.msg = "域名验证失败";
            return;
        }
        filterChain.doFilter(request,response);
        if (response.success) {
            response.msg += "域名验证通过\n";
        }
    }
}
