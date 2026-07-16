package com.youkeda.practice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        properties = new Properties();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("配置文件加载成功");
            } else {
                logger.error("配置文件 {} 未找到", CONFIG_FILE);
                throw new RuntimeException("配置文件加载失败");
            }
        } catch (IOException e) {
            logger.error("配置文件加载异常", e);
            throw new RuntimeException("配置文件加载失败", e);
        }
    }

    public static String getString(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            logger.warn("配置项 {} 未配置或为空", key);
        }
        return value;
    }

    public static int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            logger.warn("配置项 {} 不是有效的整数，使用默认值 {}", key, defaultValue);
            return defaultValue;
        }
    }

    public static String getApiKey() {
        return getString("weather.api.key");
    }

    public static String getBaseUrl() {
        return getString("weather.api.base-url");
    }

    public static int getConnectTimeout() {
        return getInt("weather.http.connect-timeout", 10000);
    }

    public static int getReadTimeout() {
        return getInt("weather.http.read-timeout", 15000);
    }

    public static int getWriteTimeout() {
        return getInt("weather.http.write-timeout", 10000);
    }

    public static String getMailFrom() {
        return getString("mail.from");
    }

    public static String getMailKey() {
        return getString("mail.key");
    }

    public static String getMailTo() {
        return getString("mail.to");
    }
}