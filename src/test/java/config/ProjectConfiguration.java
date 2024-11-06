package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProjectConfiguration {
    private final WebDriverConfig webDriverConfig;

    public ProjectConfiguration(WebDriverConfig webDriverConfig) {
        this.webDriverConfig = webDriverConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webDriverConfig.getBaseUrl();
        Configuration.browser = webDriverConfig.getBrowser().toString();
        Configuration.browserVersion = webDriverConfig.browserVersion();
        Configuration.timeout = 6000;
        Configuration.pageLoadStrategy = "eager";

        if (webDriverConfig.isRemote()) {
            Configuration.remote = String.valueOf(webDriverConfig.remoteUrl());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }
}
