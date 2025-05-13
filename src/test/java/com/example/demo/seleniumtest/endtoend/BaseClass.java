package com.example.demo.seleniumtest.endtoend;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu",
                    "--window-size=1920,1080");
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless", "--disable-gpu",
                    "--window-size=1920,1080");
            driver.set(new EdgeDriver());

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        System.out.println(getDriver());
        getDriver().get("http://dev-opera.bscxpress.com.s3-website.ap-south-1.amazonaws.com/login");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

    }

    @AfterClass
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

}
