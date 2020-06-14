package com.kirilo.java.servlets;

import com.kirilo.java.util.HtmlFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class RequestHeaderExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the response message's MIME type
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket

        // Use ResourceBundle to keep localized string in "LocalStrings_xx.properties"

        // Write the response message, in an HTML page
        try (PrintWriter out = resp.getWriter()) {
            ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", req.getLocale());
            out.println("<!DOCTYPE html");  // HTML 5
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            String title = rb.getString("requestheader.title");
            out.println("<head><title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h3>" + title + "</h3>");

            // Display all the request headers from the request message
            out.println("<table>");
            Enumeration<String> e = req.getHeaderNames();
            while (e.hasMoreElements()) {
                String headerName = e.nextElement();
                String headerValue = req.getHeader(headerName);
                out.println("<tr><td>" + HtmlFilter.filter(headerName) + "</td>");
                out.println("<td>" + HtmlFilter.filter(headerValue) + "</td></tr>");
            }
            out.println("</table></body></html>");
        }
        // Always close the output writer
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
