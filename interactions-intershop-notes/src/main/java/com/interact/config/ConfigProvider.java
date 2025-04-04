package com.interact.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String URL_SHOP = readConfig().getString("urls.shop");
    String URL_NOTE = readConfig().getString("urls.note");
    String URL_FEEDBACK = readConfig().getString("urls.feedback");
    String URL_WEBINAR = readConfig().getString("urls.webinar");
}
