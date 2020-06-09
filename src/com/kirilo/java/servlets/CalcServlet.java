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
//    private List<String> expressionList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en ru\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Calculator</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"../css/hw.css\">" +
                    "</head>\n" +
                    "<body>\n" +
                    "   <div>\n" +
                    "       <ul class=\"\">\n" +
                    "           <li>" + req.getParameter("one") + "</li>\n" +
                    "           <li>" + req.getParameter("two") + "</li>\n" +
                    "           <li>" + req.getParameter("operation") + "</li>\n" +
                    "           " +
                    getExpressionList(req).stream()
                            .map(s -> "<li>" + s + "</li>")
                            .collect(Collectors.joining(System.lineSeparator())) + "\n" +

                    "       </ul>\n" +
                    "   </div>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    private List<String> getExpressionList(HttpServletRequest req) {
        final double one = Double.parseDouble(req.getParameter("one"));
        final double two = Double.parseDouble(req.getParameter("two"));
        final String operation = req.getParameter("operation");
        final OperationType operationType = OperationType.valueOf(operation.toUpperCase());


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
