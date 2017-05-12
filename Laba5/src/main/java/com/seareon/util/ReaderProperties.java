package com.seareon.util;

import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Misha Ro on 17.03.2017.
 */
public class ReaderProperties {
    private static Properties properties = new Properties();

    public static void load(String path) throws IOException {
        properties.load(new InputStreamReader(ConfigurationManager.class.getResourceAsStream(path)));
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
