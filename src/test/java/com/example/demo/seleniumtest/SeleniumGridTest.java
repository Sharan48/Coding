package com.example.demo.seleniumtest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGridTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Test
    @Parameters({ "browser" })
    public void tearUp(String browser) throws MalformedURLException, URISyntaxException {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions option = new ChromeOptions();
            driver.set(new RemoteWebDriver(new URI("http://localhost:4444/").toURL(), option));

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions opt = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URI("http://localhost:4444/").toURL(), opt));
        } else {
            EdgeOptions opt = new EdgeOptions();
            driver.set(new RemoteWebDriver(new URI("http://localhost:4444/").toURL(), opt));

        }
        getDriver().get("http://dev-opera.bscxpress.com.s3-website.ap-south-1.amazonaws.com/login");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

    }

    public void tearDown() {
        getDriver().close();
        driver.remove();
    }

}
