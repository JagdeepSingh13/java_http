package com.http_server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// this handles after we accept a connection
public class HttpConnectionWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);


    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // once we accept a connection we use socket (variable)
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO reading

            // writing
            String html = "<html><head>JAVA Http server Serving...</head></html>";

            final String CRLF = "\n\r";   // 10, 13
            String response =
                    "HTTP/1.1 200 OK" + CRLF +   // status: http_version code msg
                            "Content-Length: " + html.getBytes().length + CRLF +    // header
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("connection processing finished");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}