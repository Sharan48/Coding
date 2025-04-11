package com.example.demo.seleniumtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoad {

    private static Properties properties;

    public static void load(String env) throws IOException {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config/" + env + ".properties");
            properties.load(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File failed to load :" + env);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);

    }

}
