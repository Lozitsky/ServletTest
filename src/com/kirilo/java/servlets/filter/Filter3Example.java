package com.kirilo.java.servlets.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Filter3Example extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");  // HTML 5
            out.println("<html><head>");
            final String title = "Filter Example 3";
            out.println("<title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + title + "</h1>");
            out.println("This is a testpage. You should see<br>\n" +
                    "this text when you invoke /filter3, <br>\n" +
                    "as well as the additional material added<br>\n" +
                    "by the PrePostFilter.");
            out.println("<br></body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
