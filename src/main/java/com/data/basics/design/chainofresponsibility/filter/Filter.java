package com.data.basics.design.chainofresponsibility.filter;

/**
 * Filter:
 *
 * @author sunchen
 * @date 2020/7/15 7:30 下午
 */
public interface Filter {
    /**
     * 过滤
     * @param request
     * @param response
     * @param filterChain
     */
    void doFilter(Request request, Response response, FilterChain filterChain);
}
