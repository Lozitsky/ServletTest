package com.kirilo.java.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        // The host and port to be connected.
        String host = "127.0.0.1";
        int port = 8080;
        try (
                // Create a TCP socket and connect to the host:port.
                Socket socket = new Socket(host, port);
                // Create the input and output streams for the network socket.
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            // Send request to the HTTP server.

        /*out.println("GET /calc?one=11&two=22&operation=divide HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Cookie: JSESSIONID=d2459265eac642d81bb5463bbd73;\n"+*/

            out.println("HEAD /start HTTP/1.1\n" +
                    "Host: localhost:8080\n" +
                    "Connection: close\n" +

                    "");
//        out.println("Connection: Keep-Alive");
            out.println();   // blank line separating header & body
            out.flush();
            // Read the response and display on console.
            String line;
            // readLine() returns null if server close the network socket.
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            // Close the I/O streams.
/*            in.close();
        out.close();*/
        }
    }
}
