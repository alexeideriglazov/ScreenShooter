package com.screenshooter.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config connectionConfig = null;
    private DbxClientV2 client;

    private Config() {
        String accessToken;
        Properties prop = new Properties();
        DbxRequestConfig requestConfig;
        try {
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            accessToken = prop.getProperty("accessToken");
            requestConfig = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
            client = new DbxClientV2(requestConfig, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Uploading configuration was failed. Check out application.properties file");
        }
    }

    public static Config getConfig() {
        if (connectionConfig == null) {
            connectionConfig = new Config();
        }
        return connectionConfig;
    }

    public DbxClientV2 client() {
        return this.client;
    }
}