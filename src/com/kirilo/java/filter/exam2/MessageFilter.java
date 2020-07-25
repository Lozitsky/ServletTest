package com.kirilo.java.filter.exam2;

import com.kirilo.java.filter.GenericFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MessageFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Entering MessageFilter");
        final String message = filterConfig.getInitParameter("message");
        servletRequest.setAttribute("message", message);
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Exiting MessageFilter");
    }
}
