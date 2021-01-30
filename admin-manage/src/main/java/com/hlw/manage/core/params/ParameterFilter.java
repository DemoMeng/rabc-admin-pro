package com.hlw.manage.core.params;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author mqz
 * @description
 * @since 2020/6/9
 */
@WebFilter(filterName = "parameterFilter",urlPatterns = "/*")
public class ParameterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("12312312");
        filterChain.doFilter(new ParameterRequest((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
    }
}
