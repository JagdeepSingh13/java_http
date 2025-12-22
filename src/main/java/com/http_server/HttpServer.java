package com.http_server;

import com.http_server.config.ConfigManager;
import com.http_server.config.Configuration;

public class HttpServer {

    public static void main(String[] args) {

        System.out.println("main hello");

        ConfigManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration conf = ConfigManager.getInstance().getCurrentConfiguration();
        System.out.println("using port: "+ conf.getPort() + " webroot: "+conf.getWebroot());

    }
}
