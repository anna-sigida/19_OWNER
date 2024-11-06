package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;
import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config = ConfigReader.Instance.read();

    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        String browser = String.valueOf(config.getBrowser());

        if (browser == null) {
            throw new RuntimeException("Browser is not specified in configuration.");
        }

        browser = browser.toUpperCase();

        if (Objects.isNull(config.remoteUrl())) {
            if (browser.equals(Browser.CHROME.toString())) {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            } else if (browser.equals(Browser.FIREFOX.toString())) {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }

        } else {
            if (browser.equals(Browser.CHROME.toString())) {
                return new RemoteWebDriver(config.remoteUrl(), new ChromeOptions());

            } else if (browser.equals(Browser.FIREFOX.toString())) {
                return new RemoteWebDriver(config.remoteUrl(), new FirefoxOptions());
            }
        }

        throw new RuntimeException("No such browser: " + config.getBrowser());
    }
}
