package com.kirilo.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

// https://www.baeldung.com/convert-input-stream-to-string
// https://stackoverflow.com/a/1464366/9586230

public class ReadHtmlFileServlet extends HttpServlet {
    private int i;

    private static String modifyElements(String s, HttpServletRequest req) {
        if (req.getSession().getAttribute("count") != null) {
            s = s.replaceAll("<li id=\"c1\">.*</li>", "<li id=\"c1\">" + req.getSession().getAttribute("count") + "</li>");
        }
        if (req.getParameter("n1") != null) {
            s = s.replaceAll("<td id=\"n1\">.*</td>", "<td id=\"n1\">" + req.getParameter("n1") + "</td>");
        }
        if (req.getParameter("n2") != null) {
            s = s.replaceAll("<td id=\"n2\">.*</td>", "<td id=\"n2\">" + req.getParameter("n2") + "</td>");
        }
        if (req.getParameter("n3") != null) {
            s = s.replaceAll("<td id=\"n3\">.*</td>", "<td id=\"n3\">" + req.getParameter("n3") + "</td>");
        }

        if (req.getParameter("t1") != null) {
            s = s.replaceAll("<td id=\"t1\">.*</td>", "<td id=\"t1\">" + req.getParameter("t1") + "</td>");
        }
        if (req.getParameter("t2") != null) {
            s = s.replaceAll("<td id=\"t2\">.*</td>", "<td id=\"t2\">" + req.getParameter("t2") + "</td>");
        }
        if (req.getParameter("t3") != null) {
            s = s.replaceAll("<td id=\"t3\">.*</td>", "<td id=\"t3\">" + req.getParameter("t3") + "</td>");
        }

        if (req.getParameter("s1") != null) {
            s = s.replaceAll("<li id=\"s1\">.*</li>", "<li id=\"s1\">" + req.getParameter("s1") + "</li>");
        }
        if (req.getParameter("s2") != null) {
            s = s.replaceAll("<li id=\"s2\">.*</li>", "<li id=\"s2\">" + req.getParameter("s2") + "</li>");
        }
        if (req.getParameter("s3") != null) {
            s = s.replaceAll("<li id=\"s3\">.*</li>", "<li id=\"s3\">" + req.getParameter("s3") + "</li>");
        }
        return s;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("count") == null) {
            i = 0;
        }

        i++;
        req.getSession().setAttribute("count", i);
//        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter();
             InputStream inputStream = getClass().getResourceAsStream("/start.html")) {

// In Java 8
            String text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines().map((String s) -> modifyElements(s, req))
                    .collect(Collectors.joining(System.lineSeparator()));
            writer.println(text);

/*
        Using Java 7
        StringBuilder textBuilder = new StringBuilder();

            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            System.out.println(textBuilder.toString());
            writer.println(textBuilder.toString());*/
        }

        /*try (PrintWriter writer = resp.getWriter()) {
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en ru\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Homework 2</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"../css/hw.css\">\n" +
                    "</head>\n" +
                    "<body class=\"border-canvas\">\n" +
                    "<section>\n" +
                    "    <header>\n" +
                    "        <h1 class=\"main-header\">Secret document</h1>\n" +
                    "    </header>\n" +
                    "    <div class=\"flexbox-container\">\n" +
                    "        <div class=\"flexbox-item-1\">\n" +
                    "            <img src=\"../img/java-seeklogo.com.svg\">\n" +
                    "        </div>\n" +
                    "        <form class=\"flexbox-item-2 flexbox-container-v\">\n" +
                    "            <div class=\"flexbox-item-1-v\">\n" +
                    "                <table>\n" +
                    "                    <tr>\n" +
                    "                        <th>Name</th>\n" +
                    "                        <th>Telephone</th>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>Peter</td>\n" +
                    "                        <td>123456</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>Ivan</td>\n" +
                    "                        <td>234567</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>Serhio</td>\n" +
                    "                        <td>666777</td>\n" +
                    "                    </tr>\n" +
                    "                </table>\n" +
                    "            </div>\n" +
                    "            <div class=\"flexbox-item-2-v\">\n" +
                    "                <ul class=\"\">\n" +
                    "                    <li>Text document1</li>\n" +
                    "                    <li>Text document22</li>\n" +
                    "                    <li>Text document3</li>\n" +
                    "                </ul>\n" +
                    "            </div>\n" +
                    "        </form>\n" +
                    "    </div>\n" +
                    "</section>\n" +
                    "<footer>\n" +
                    "    <h3><span>Text Project 2020</span></h3>\n" +
                    "</footer>\n" +
                    "</body>\n" +
                    "</html>");
        }*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
