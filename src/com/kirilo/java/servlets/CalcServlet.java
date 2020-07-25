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
                    "ID: " + " " + session.getId() + "<br />" +
                    "   <div>\n" +
                    "       <ul class=\"\">\n" +
                    "           <li>" + req.getParameter("one") + "</li>\n" +
                    "           <li>" + req.getParameter("two") + "</li>\n" +
                    "           <li>" + req.getParameter("operation") + "</li>\n" +
                    "           " +
                    getExpressionList(req, resp).stream()
                            .map(s -> "<li>" + s + "</li>")
                            .collect(Collectors.joining(System.lineSeparator())) + "\n" +

                    "       </ul>\n" +
                    "   </div>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
