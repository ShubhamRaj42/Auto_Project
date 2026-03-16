package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static void loadConfig() throws IOException {

        prop = new Properties();

        FileInputStream input = new FileInputStream(
        System.getProperty("user.dir") +
        "/src/test/resources/config/config.properties");

        prop.load(input);
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
