package com.kirilo.java.servlets;

import com.kirilo.java.util.HtmlFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class SessionExample extends HttpServlet {
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
            String title = rb.getString("sessions.title");
            out.println("<head><title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h3>" + title + "</h3>");

            // Return the existing session if there is one. Otherwise, create a new session
            HttpSession session = req.getSession();

            // Display session information
            out.println(rb.getString("sessions.id") + " " + session.getId() + "<br />");
            out.println(rb.getString("sessions.created") + " ");
            out.println(new Date(session.getCreationTime()) + "<br />");
            out.println(rb.getString("sessions.lastaccessed") + " ");
            out.println(new Date(session.getLastAccessedTime()) + "<br /><br />");

            // Set an attribute (name-value pair) if present in the request
            String attName = req.getParameter("attribute_name");
            if (attName != null) attName = attName.trim();
            String attValue = req.getParameter("attribute_value");
            if (attValue != null) attValue = attValue.trim();
            if (attName != null && !attName.equals("")
                    && attValue != null && !attValue.equals("")) {
                // synchronized session object to prevent concurrent update
                synchronized (session.getId()) {
                    session.setAttribute(attName, attValue);
                }
            }

            // Display the attributes (name-value pairs) stored in this session
            out.println(rb.getString("sessions.data") + "<br>");
            Enumeration<String> names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                String value = session.getAttribute(name).toString();
                out.println(HtmlFilter.filter(name) + " = "
                        + HtmlFilter.filter(value) + "<br>");
            }
            out.println("<br />");

            // Display a form to prompt user to create session attribute
            out.println("<form method='get'>");
            out.println(rb.getString("sessions.dataname"));
            out.println("<input type='text' name='attribute_name'><br />");
            out.println(rb.getString("sessions.datavalue"));
            out.println("<input type='text' name='attribute_value'><br />");
            out.println("<input type='submit' value='SEND'>");
            out.println("</form><br />");

            out.print("<a href='");
            // Encode URL by including the session ID (URL-rewriting)
            out.print(resp.encodeURL(req.getRequestURI() + "?attribute_name=foo&attribute_value=bar"));
            out.println("'>Encode URL with session ID (URL re-writing)</a>");
            out.println("</body></html>");
        }
        // Always close the output writer
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
