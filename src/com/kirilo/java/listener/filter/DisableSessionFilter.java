package com.kirilo.java.listener.filter;

import com.kirilo.java.listener.wrapper.HttpServletDisableSessionRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// https://memorynotfound.com/disable-http-session-web-application/
public class DisableSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletDisableSessionRequestWrapper wrapper = new HttpServletDisableSessionRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(wrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
