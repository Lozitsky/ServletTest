package com.kirilo.java.servlets;

import com.kirilo.java.util.HtmlFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

public class RequestInfoExample extends HttpServlet {
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
            String title = rb.getString("requestinfo.title");
            out.println("<head><title>" + title + "</title></head>");
            out.println("<body>");
            out.println("<h3>" + title + "</h3>");

            // Tabulate the request information
            out.println("<table>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.protocol") + "</td>");
            out.println("<td>" + req.getProtocol() + "</td></tr>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.method") + "</td>");
            out.println("<td>" + req.getMethod() + "</td></tr>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.servername") + "</td>");
            out.println("<td>" + req.getServerName() + "</td></tr>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.serverport") + "</td>");
            out.println("<td>" + req.getServerPort() + "</td></tr>");
            out.println("</td></tr><tr><td>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.requesturi") + "</td>");
            out.println("<td>" + HtmlFilter.filter(req.getRequestURI()) + "</td></tr>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.pathinfo") + "</td>");
            out.println("<td>" + HtmlFilter.filter(req.getPathInfo()) + "</td></tr>");
            out.println("<tr><td>"+rb.getString("requestinfo.label.pathtranslated")+"</td>");
            out.println("<td>" + req.getPathTranslated() + "</td></tr>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.remoteaddr") + "</td>");
            out.println("<td>" + req.getRemoteAddr() + "</td></tr>");
            out.println("<tr><td>" + rb.getString("requestinfo.label.remotehost") + "</td>");
            out.println("<td>" + req.getRemoteHost() + "</td></tr>");

            // SSL (HTTPS) Cipher suites
            String cipherSuite = (String) req.getAttribute("javax.servlet.request.cipher_suite");
            if (cipherSuite != null) {
                out.println("<tr><td>SSLCipherSuite:</td>");
                out.println("<td>" + cipherSuite + "</td></tr>");
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
