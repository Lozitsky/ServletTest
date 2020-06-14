package com.kirilo.java.servlets;

import com.kirilo.java.util.HtmlFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

public class RequestParamExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// Set the response message's MIME type
//        https://stackoverflow.com/a/4864956/9586230
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        // Allocate a output writer to write the response message into the network socket

        // Use ResourceBundle to keep localized string in "LocalStrings_xx.properties"

        // Write the response message, in an HTML page
        try (PrintWriter out = resp.getWriter()) {
            ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", req.getLocale());
            out.println("<!DOCTYPE html");  // HTML 5
            out.println("<html><head>");
//            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            String title = rb.getString("requestparams.title");
            out.println("<head><title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h3>" + title + "</h3>");

            // Display the request parameters. Trim and discard empty string.
            out.println(rb.getString("requestparams.params-in-req") + "<br />");
            boolean noParam = true;
            String firstName = req.getParameter("firstname");
            if (firstName != null && (firstName = firstName.trim()).length() != 0) {
                out.println(rb.getString("requestparams.firstname"));
                out.println(" = " + HtmlFilter.filter(firstName) + "<br />");
                noParam = false;
            }

            String lastName = req.getParameter("lastname");
            if (lastName != null && (lastName = lastName.trim()).length() != 0) {
                out.println(rb.getString("requestparams.lastname"));
                out.println(" = " + HtmlFilter.filter(lastName));
                noParam = false;
            }

            if (noParam) {
                out.println(rb.getString("requestparams.no-params"));
            }
            out.println("<br /><br />");
            final String reqEncoding = req.getCharacterEncoding();
            if (reqEncoding!=null) {
                out.println("<p> Request Encoding: "+ reqEncoding +"</p>");
            }
            final String respEncoding = resp.getCharacterEncoding();
            if (respEncoding!=null) {
                out.println("<p> Response Encoding: "+ respEncoding +"</p>");
            }

            // Display a form to prompt user for parameters.
            // Use default "action" to the current page
//            out.println("<form method='get'>");
//            out.println("<form method='post'>");
            out.println("<form action='request_param' method='post'>");
            out.println(rb.getString("requestparams.firstname"));
            out.println("<input type='text' name='firstname'><br />");
            out.println(rb.getString("requestparams.lastname"));
            out.println("<input type='text' name='lastname'><br />");
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
