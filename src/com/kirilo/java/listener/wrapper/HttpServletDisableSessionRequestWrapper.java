package com.kirilo.java.listener.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

// https://memorynotfound.com/disable-http-session-web-application/
public class HttpServletDisableSessionRequestWrapper extends HttpServletRequestWrapper {
    public HttpServletDisableSessionRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession(boolean create) {
        throw new UnsupportedOperationException("HttpSession is not allowed");
    }

    @Override
    public HttpSession getSession() {
        throw new UnsupportedOperationException("HttpSession is not allowed");
    }
}
