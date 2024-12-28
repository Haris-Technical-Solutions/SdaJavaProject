package org.example.bookstore.util;

import java.util.Properties;
import java.io.FileInputStream;

class Config {
    private static Config instance;
    private Properties config;

    private Config() {
        this.config = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            this.config.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static synchronized Properties getConfig() {
        if (instance == null) {
            instance = new Config();
        }
        return instance.getConfigAttr();
    }
    public Properties getConfigAttr() {
        return this.config;
    }
}