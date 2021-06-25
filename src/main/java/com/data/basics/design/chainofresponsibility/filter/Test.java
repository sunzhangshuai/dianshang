package com.data.basics.design.chainofresponsibility.filter;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/15 7:48 下午
 */
public class Test {
    public static void main(String[] args) {
        Request request = new Request();
        request.param = "name";
        request.dns = "www.baidu.com";
        request.network = "127.0.0.1";
        Response response = new Response();
        FilterChain filterChain = new FilterChain();
        filterChain.add(new FilterDns()).add(new FilterNetWork());
        filterChain.doFilter(request, response);
        System.out.println(response.success);
        System.out.println(response.data);
        System.out.println(response.msg);
    }
}
