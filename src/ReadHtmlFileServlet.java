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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter writer = resp.getWriter();
             InputStream inputStream = getClass().getResourceAsStream("htm/start.html")) {

// In Java 8
            String text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
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
