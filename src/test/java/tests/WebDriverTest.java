package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import config.WebDriverProvider;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverTest {
    private final WebDriverProvider webDriverProvider = new config.WebDriverProvider();

    @BeforeEach
    public void startDriver() {
        webDriverProvider.configWebDriver();
    }

    @Test
    public void webDriverTest() {
        open("https://github.com");
        String title = title();
        assertEquals("GitHub · Build and ship software on a single, collaborative platform · GitHub", title);
    }
}
