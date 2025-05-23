package org.example.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String URL_HELLO_PAGE = readConfig().getString("urls.hellopage");
    String URL_SHOP_PAGE = readConfig().getString("urls.shoppage");
}
