package com.kirilo.java.filter.exam1;

import com.kirilo.java.filter.GenericFilter;

import javax.servlet.*;
import java.io.IOException;

public class HelloWorldFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Entering Filter");
        servletRequest.setAttribute("hello", "HelloWorld!");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Exiting HelloWorldFilter!");
    }
}
