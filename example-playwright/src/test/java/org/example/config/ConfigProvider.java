package org.example.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String URL = readConfig().getString("url");
    String API_URL = readConfig().getString("api_url");
    String API_KEY = readConfig().getString("api_key");
    String NOTE_URL = readConfig().getString("note_url");
}
