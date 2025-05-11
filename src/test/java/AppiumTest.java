import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.UUID;


public class AppiumTest {
    private static AndroidDriver driver;

    @BeforeAll
    static void setUp() throws URISyntaxException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName("UiAutomator2")
                .setPlatformName(MobilePlatform.ANDROID)
                .setPlatformVersion("12")
                .setUdid("05BAYV4M0W")
                .setAppPackage("com.android.settings")
                .setAppActivity(".Settings");
        driver = new AndroidDriver(
                // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                new URI("http://127.0.0.1:4723").toURL(), options
        );
    }

    @AfterEach
    void afterEach() throws IOException {
        screenShot();
    }

    private void screenShot() throws IOException {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String fileName = UUID.randomUUID().toString();
        Files.copy(srcFile.toPath(), new File("/Users/ryosuke/" + fileName + ".jpg").toPath());
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testExistApp() {
        WebElement el = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"アプリ\")"
        ));
        Assertions.assertNotNull(el);
    }

    @Test
    void testExistNotification() {
        WebElement el = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"通知\")"
        ));
        Assertions.assertNotNull(el);
    }

    @Test
    void testExistBattery() {
        WebElement el = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"バッテリー\")"
        ));
        Assertions.assertNotNull(el);
    }

//    @Test
//    void testClickAccount() {
//        WebElement el = driver.findElement(AppiumBy.id("com.android.settings:id/account_avatar"));
//        el.click();
//        // 待機
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
////        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(
////                "new UiSelector().text(\"個人情報\")"
////        )));
//
//        // Assert
//        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiSelector().text(\"個人情報\")"
//        ));
//        Assertions.assertNotNull(el2);
//    }
}
