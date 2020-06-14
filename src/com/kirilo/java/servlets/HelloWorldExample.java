package com.kirilo.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloWorldExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the response message's MIME type.
        resp.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket.
        PrintWriter out = resp.getWriter();

        // Use a ResourceBundle for localized string in "LocalStrings_xx.properties" for i18n.
        // The request.getLocale() sets the locale based on the "Accept-Language" request header.
//        ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", req.getLocale());
        // To test other locales.
        ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", new Locale("uk"));

        // Write the response message, in an HTML document.
        try {
            out.println("<!DOCTYPE html>");  // HTML 5
            out.println("<html><head>");
            out.println("<meta charset=\"UTF-8\">");
            String title = rb.getString("helloworld.title");
            out.println("<title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + title + "</h1>");  // Prints "Hello, world!"
            // Set a hyperlink image to refresh this page
            out.println("<a href='" + req.getRequestURI() + "'><img src='/img/v.jpg' style='width: 100px;'></a>");
            out.println("</body></html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}
