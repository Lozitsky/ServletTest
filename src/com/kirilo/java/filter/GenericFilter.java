package com.kirilo.java.filter;

import javax.servlet.*;
import java.io.IOException;

public abstract class GenericFilter implements Filter {
    protected FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public abstract void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }
}
