package com.kirilo.java.servlets.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// https://memorynotfound.com/disable-http-session-web-application/
// https://docs.oracle.com/cd/B15904_01/web.1012/b14017/filters.htm#i1001462
public class Welcome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");  // HTML 5
            out.println("<html><head>");
            out.println("<title>" + "OC4J - HttpSession Event Listeners" + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + "OC4J - HttpSession Event Listeners" + "</h1>");  // Prints "Hello, world!"
            out.println("<p>\n" +
                    "This example demonstrates the use of the HttpSession Event and Listener that is\n" +
                    "new with the Java Servlet 2.3 API.  \n" +
                    "</p>\n" +
                    "<p>");
            out.println("<a href='" + req.getContextPath() + "/create'>" + "Create New Session" + "</a>");
            out.println("<a href='" + req.getContextPath() + "/destroy'>" + "Destroy Current Session" + "</a>");
/*            out.println("<a href='" + "servlets/listener/SessionCreateServlet" + "'>" + "Create New Session" + "</a>");
            out.println("<a href='" + "servlets/listener/SessionDestroyServlet" + "'>" + "Destroy Current Session" + "</a>");*/
            out.println("</p>\n" +
                    "<p>\n" +
                    "Click the Create link above to start a new HttpSession. An HttpSession\n" +
                    "listener has been configured for this application. The servler container\n" +
                    "will send an event to this listener when a new session is created or \n" +
                    "destroyed. The output from the event listener will be visible in the \n" +
                    "console window from where OC4J was started.\n" +
                    "</p>");
            out.println("</body></html>");
        }
    }
}
