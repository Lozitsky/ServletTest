package com.kirilo.java.servlets.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// https://docs.oracle.com/cd/B15904_01/web.1012/b14017/filters.htm#i1001462
public class SessionDestroyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        session.invalidate();
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<head><title> " + "Session Destroyed Successfully .." +
                    "Look at OC4J Console to see whether the HttpSessionEvent invoked "
                    + "</title></head><body>");
            out.println("<p>[<a href='" + req.getContextPath() + "/index'>Restart</a>]");
            out.println("<h2> Session Destroyed Successfully</h2>");
            out.println("Look at the OC4J Console to see whether the HttpSessionEvent was invoked");
            out.println("</p></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
