package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static constants.Constants.BASE_URL;
import static constants.Constants.SERVER;
import static org.assertj.core.api.Assertions.assertThat;

public class AppiumBrowserTests {
    //Запуск: appium --allow-insecure chromedriver_autodownload
    private AndroidDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setPlatformVersion("16")
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .noReset()
                .withBrowserName("Chrome");

        driver = new AndroidDriver(new URL(SERVER), options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void webFormTest() throws InterruptedException {
        String item = "Web form";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.linkText(item))).click();
        WebElement title =  wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//h1[@class = 'display-6']")));

        assertThat(title.getText()).isEqualTo(item);

        Thread.sleep(5000);
    }
}
