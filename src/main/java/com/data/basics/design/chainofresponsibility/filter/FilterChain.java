package com.data.basics.design.chainofresponsibility.filter;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * FilterChain:
 *
 * @author sunchen
 * @date 2020/7/15 7:31 下午
 */
public class FilterChain{
    int index = 0;
    List<Filter> filterList = new ArrayList<Filter>();
    public FilterChain add(Filter filter){
        filterList.add(filter);
        return this;
    }
    public void doFilter(Request request, Response response) {
        if (index == filterList.size()) {
            if (!request.isParam) {
                response.msg += "没有验证param，请注意\n";
            }
            response.data = "老公，我爱你";
            return ;
        }
        Filter filter = filterList.get(index);
        index++;
        filter.doFilter(request, response, this);
    }
}
