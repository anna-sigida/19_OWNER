package config;

import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    Instance;

    private static final WebDriverConfig WEB_DRIVER_CONFIG =
            ConfigFactory.create(
                    WebDriverConfig.class,
                    System.getProperties()
            );

    public WebDriverConfig read() {
        return WEB_DRIVER_CONFIG;
    }
}
