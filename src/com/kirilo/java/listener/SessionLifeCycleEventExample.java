package com.kirilo.java.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionLifeCycleEventExample implements ServletContextListener, HttpSessionListener {

    public ServletContext getServletContext() {
        return servletContext;
    }

    private ServletContext servletContext;

    public SessionLifeCycleEventExample() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log("CREATE", httpSessionEvent);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        final HttpSession _session = httpSessionEvent.getSession();
        long _start = _session.getCreationTime();
        long _end = _session.getLastAccessedTime();
        String _counter = (String) _session.getAttribute("counter");
        log("DESTROY, Session Duration:"
                + (_end - _start) + "(ms) Counter:" + _counter, httpSessionEvent);
    }

    protected void log(String msg, HttpSessionEvent hse) {
        String _ID = hse.getSession().getId();
        log("SessionID:" + _ID + "    " + msg);
    }

    protected void log(String msg) {
        System.out.println("[" + getClass().getName() + "] " + msg);
    }
}
