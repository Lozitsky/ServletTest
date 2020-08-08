package com.kirilo.java.servlets;

import com.kirilo.java.operations.OperationsFactory;
import com.kirilo.java.operations.OperationType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CalcServlet extends HttpServlet {

    public static final String SESSIONMAP = "sessionmap";
    private final OperationsFactory operationFactory = new OperationsFactory();
    private static final Map<String, OperationType> TYPE_MAP = new HashMap<>(OperationType.values().length);

    static {
        for (OperationType type : OperationType.values()) {
            TYPE_MAP.put(type.name(), type);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        Map<String, String> attribute = (Map<String, String>) req.getServletContext().getAttribute(SESSIONMAP);

        if (attribute == null) {
            attribute = new HashMap<>();
        }

        try (PrintWriter out = resp.getWriter()) {
            HttpSession session = req.getSession();

            // Display session information
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en ru\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Calculator</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"../css/hw.css\">" +
                    "</head>\n" +
                    "<body>\n" +
                    "<table cellpadding='20'>" +
                    "   <div>\n" +
                    "<tr>" +
                    "<td style='vertical-align:top;'>" +
                    "       ID: " + " " + session.getId() + "<br />" +
                    "       <ul class=\"\">\n" +
                    "           <li>" + req.getParameter("one") + "</li>\n" +
                    "           <li>" + req.getParameter("two") + "</li>\n" +
                    "           <li>" + req.getParameter("operation") + "</li>\n" +
                    "           ");

            final String collect = getExpressionList(req, resp).stream()
                    .map(s -> "<li>" + s + "</li>")
                    .collect(Collectors.joining(System.lineSeparator()));

            attribute.put(session.getId(), collect);
            getServletContext().setAttribute(SESSIONMAP, attribute);

            out.println(collect + "\n" +

                    "       </ul>\n" +
                    "</td>" +
                    "<td style='vertical-align:top;'>");

            for (Map.Entry<String, String> entry : attribute.entrySet()) {
                final String sessionId = entry.getKey();
                final String listOperation = entry.getValue();
                out.println("<div style='color:red'>" + sessionId + "</div>" +
                        "       <ul class=\"\">\n" +
                        listOperation +
                        "       </ul>\n"
                );
            }
            out.println(
                    "</td>" +
                            "</tr>" +
                            "   </div>\n" +
                            "</table>" +
                            "</body>\n" +
                            "</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getAllExpressionList(Map<String, String> attribute, PrintWriter out) {
        return null;
    }

    //    https://www.stubbornjava.com/posts/java-enum-lookup-by-name-or-field-without-throwing-exceptions
    private List<String> getExpressionList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final double one = Double.parseDouble(req.getParameter("one"));
        final double two = Double.parseDouble(req.getParameter("two"));
        final String operation = req.getParameter("operation");

//        final OperationType operationType = OperationType.valueOf(operation.toUpperCase());
        final OperationType operationType = TYPE_MAP.get(operation.toUpperCase());
        if (operationType == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        final HttpSession session = req.getSession();

        final List<String> expressionList;
        final Object expression = session.getAttribute("expression");
        if (expression != null) {
            expressionList = (ArrayList<String>) expression;
        } else {
            expressionList = new ArrayList<>();
        }

        expressionList.add(MessageFormat.format("{0} {1} {2} = {3}",
                one, operationType.getOperation(), two, operationFactory.calculate(operationType, one, two)));

        session.setAttribute("expression", expressionList);

        return expressionList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
