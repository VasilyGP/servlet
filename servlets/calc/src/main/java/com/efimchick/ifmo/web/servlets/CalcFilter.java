package com.efimchick.ifmo.web.servlets;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "CalcFilter", urlPatterns = "/")
public class CalcFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter:");
        System.err.println(servletRequest.getParameterMap());
        System.err.println("==============================");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
