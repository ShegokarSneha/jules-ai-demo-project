package com.julesai.configprovider;

import java.io.IOException;
import java.io.InputStream;
import java.security.spec.ECField;
import java.util.Properties;

public class ConfigProvider {
    private static final String PROPERTIES_PATH = "/properties";
    private static Properties properties;

    private ConfigProvider() {
    }

    private static Properties getInstance() {
        if (properties == null) {
            properties = loadProperties();
            return properties;
        } else {
            return properties;
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            InputStream inputStream = loader.getClass().getResourceAsStream("/properties/config.properties");
            properties.load(inputStream);
        } catch (NullPointerException e) {
            System.out.println("config.properties File Not Found.....");
            try {
                InputStream inputStream = ConfigProvider.class.getResourceAsStream("/properties/config.properties");
                properties.load(inputStream);
                System.out.println("config.properties File Found.....");
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getAsString(String key){
        return getInstance().getProperty(key);
    }
}
