package com.http_server;

import com.http_server.config.ConfigManager;
import com.http_server.config.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) {

        System.out.println("main hello");

        ConfigManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration conf = ConfigManager.getInstance().getCurrentConfiguration();
        System.out.println("using port: "+ conf.getPort() + " webroot: "+conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            // waits to get a connection and accept it
            Socket socket = serverSocket.accept();

            // once we accept a connection we use socket (variable)
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO reading


            // TODO writing
            String html = "<html><head>JAVA Http server Serving...</head></html>";

            final String CRLF = "\n\r";   // 10, 13
            String response =
                    "HTTP/1.1 200 OK" + CRLF +   // status: http_version code msg
                   "Content-Length: " + html.getBytes().length + CRLF+    // header
                    CRLF+
                    html+
                    CRLF+ CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
