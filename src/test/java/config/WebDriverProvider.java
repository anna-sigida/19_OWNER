package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;


public class WebDriverProvider {

    private final WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    public void configWebDriver() {

        if (config.remoteUrl() != null) {
            Configuration.remote = config.remoteUrl();
            Configuration.pageLoadStrategy = "eager";
        }

        switch (config.getBrowser()) {
            case CHROME: {
                Configuration.browser = "chrome";
                break;
            }
            case FIREFOX: {
                Configuration.browser = "firefox";
                break;
            }
            default:
                throw new RuntimeException("No such driver");
        }

        Configuration.browserVersion = config.browserVersion();
    }
}
