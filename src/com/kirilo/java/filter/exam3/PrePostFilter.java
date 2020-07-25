package com.kirilo.java.filter.exam3;

import com.kirilo.java.filter.GenericFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class PrePostFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (OutputStream out = servletResponse.getOutputStream()) {
            out.write("<hr>PRE<hr>".getBytes());
            final GenericResponseWrapper wrapper = new GenericResponseWrapper((HttpServletResponse) servletResponse);
            filterChain.doFilter(servletRequest, wrapper);
            out.write(wrapper.getData());
            out.write("<hr>POST<hr>".getBytes());
        }
    }
}
