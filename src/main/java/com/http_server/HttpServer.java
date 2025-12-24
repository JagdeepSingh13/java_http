package com.http_server;

import com.http_server.config.ConfigManager;
import com.http_server.config.Configuration;
import com.http_server.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {

        LOGGER.info("server starting");

        ConfigManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigManager.getInstance().getCurrentConfiguration();

        LOGGER.info("using port: "+ conf.getPort() + ", webroot: "+conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
