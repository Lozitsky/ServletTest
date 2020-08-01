package com.kirilo.java.servlets.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

// https://docs.oracle.com/cd/B15904_01/web.1012/b14017/filters.htm#i1001462
public class SessionCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(true);
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            final String sCounter = (String) session.getAttribute("counter");
            int counter = 1;
            if (sCounter != null) {
                counter = Integer.parseInt(sCounter);
                counter++;
            }

            session.setAttribute("counter", String.valueOf(counter));

            out.println("<head><title> " + "Session Created Successfully .." +
                    "Look at OC4J Console to see whether the HttpSessionEvent invoked "
                    + "</title></head><body>");
            out.println("<p>[<a href='" + req.getRequestURI() + "'>Reload</a>]&nbsp;");
            out.println("[<a href='" + req.getContextPath() + "/destroy'>Destroy Session</a>]");
            out.println("<h2>Session created Successfully</h2>");
            out.println("Look at the OC4J Console to see whether the HttpSessionEvent was invoked");
            out.println("<h3>Session Data:</h3>");
            out.println("New Session: " + session.isNew());
            out.println("<br>Session ID: " + session.getId());
            out.println("<br>Creation Time: " + new Date(session.getCreationTime()));
            out.println("<br>Last Accessed Time: " +
                    new Date(session.getLastAccessedTime()));
            out.println("<br>Number of Accesses: " + session.getAttribute("counter"));
            out.println("</p></body></html>");
        }
    }
}
