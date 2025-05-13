package com.example.demo.seleniumtest.endtoend;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeClass
    public static void setUp(String browser) {

        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu",
                    "--window-size=1920,1080");
            driver.set(new ChromeDriver(options));
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless", "--disable-gpu",
                    "--window-size=1920,1080");
            driver.set(new FirefoxDriver(options));

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
        getDriver().get("http://dev-opera.bscxpress.com.s3-website.ap-south-1.amazonaws.com/login");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

    }

    @AfterClass
    public static void tearDown() {
        getDriver().quit();
        driver.remove();
    }

}
