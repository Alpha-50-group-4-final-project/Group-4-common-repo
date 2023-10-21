package com.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesManager {



    public enum PropertiesManagerEnum {

        INSTANCE;

        private static final String CONFIG_PROPERTIES = "src/test/resources/configuration.properties";

        private static Properties getConfigProperties() {
            return loadProperties(CONFIG_PROPERTIES);
        }


        private static Properties loadProperties(String url) {
            Properties properties = new Properties();

            try {
                properties.load(Files.newInputStream(Paths.get(url)));
            } catch (IOException ex) {
              System.out.println("Loading properties failed!");
            }

            return properties;
        }
        public static String getConfigPropertyByKey(String key) {
            String value = getConfigProperties().getProperty(key);
            return value != null ? value : key;
        }

    }
}
