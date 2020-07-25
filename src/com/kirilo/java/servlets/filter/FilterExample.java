package com.kirilo.java.servlets.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FilterExample extends HttpServlet {
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
//            out.println("<meta charset=\"UTF-8\">");
            String title = (String) req.getAttribute("hello");
            out.println("<title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + title + "</h1>");  // Prints "Hello, world!"
            // Set a hyperlink image to refresh this page
            out.println("<a href='" + req.getRequestURI() + "'><img src='/img/v.jpg' style='width: 100px;'></a>");
            out.println("</body></html>");
        }
    }
}
