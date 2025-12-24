package com.http_server.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            // waits to get a connection and accept it
            Socket socket = serverSocket.accept();

            // once we accept a connection we use socket (variable)
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO reading


            // writing
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
