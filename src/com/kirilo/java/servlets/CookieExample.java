package com.kirilo.java.servlets;

import com.kirilo.java.util.HtmlFilter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

public class CookieExample extends HttpServlet {
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
            String title = rb.getString("cookies.title");
            out.println("<head><title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h3>" + title + "</h3>");

            // Display the cookies returned by the client
            Cookie[] cookies = req.getCookies();
            if ((cookies != null) && (cookies.length > 0)) {
                out.println(rb.getString("cookies.cookies") + "<br />");
                for (Cookie cookie : cookies) {
/*                    out.println("Path: " + cookie.getPath()+ "<br />");
                    out.println("Domain: " + cookie.getDomain()+ "<br />");*/
                    out.println("Version: " + cookie.getVersion()+ "<br />");
                    out.println("Max age: " + cookie.getMaxAge()+ "<br />");
                    out.println("Secure: " + cookie.getSecure()+ "<br />");
                    out.println("Class: " + cookie.getClass()+ "<br />");
                    out.println("Hash code: " + cookie.hashCode()+ "<br />");
                    out.println("Is Http only: " + cookie.isHttpOnly()+ "<br />");
                    out.println("Cookie Name: " + HtmlFilter.filter(cookie.getName()) + "<br />");
                    out.println("Cookie Value: " + HtmlFilter.filter(cookie.getValue()) + "<br /><br />");
                }
            } else {
                out.println(rb.getString("cookies.no-cookies") + "<br />");
            }
            out.println("<br />");

            // Create a new cookie if cookiename and cookievalue present in the request
            String cookieName = req.getParameter("cookiename");
            if (cookieName != null) cookieName = cookieName.trim();
            String cookieValue = req.getParameter("cookievalue");
            if (cookieValue != null) cookieValue = cookieValue.trim();
            if (cookieName != null && !cookieName.equals("")
                    && cookieValue != null && !cookieValue.equals("")) {
                Cookie cookie = new Cookie(cookieName, cookieValue);
                resp.addCookie(cookie);
                out.println(rb.getString("cookies.set") + "<br />");
                out.print(rb.getString("cookies.name") + "  "
                        + HtmlFilter.filter(cookieName) + "<br />");
                out.print(rb.getString("cookies.value") + "  "
                        + HtmlFilter.filter(cookieValue));
            }
            out.println("<br /><br />");

            // Display a form to prompt the user to create a new cookie
            out.println(rb.getString("cookies.make-cookie") + "<br />");
            out.print("<form method='get'>");
            out.print(rb.getString("cookies.name"));
            out.println("<input type='text' name='cookiename'><br />");
            out.print(rb.getString("cookies.value"));
            out.println("<input type='text' name='cookievalue'><br />");
            out.println("<input type='submit' value='SEND'>");
            out.println("</form></body></html>");
        }
        // Always close the output writer
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
