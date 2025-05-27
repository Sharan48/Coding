package com.example.demo.seleniumtest;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DockerChromeTest {

    @Test(dataProvider = "ExcelDataProvider", dataProviderClass = DataDriven.class)
    public void testLogin(String username, String password) {

        System.out.println("Testing login with" + username + "|" + password);

    }

    @Test(enabled = true)
    public void relativeLocator() throws InterruptedException, IOException {
        String qa = System.getProperty("env", "qa");
        ConfigLoad.load(qa);
        String url = ConfigLoad.get("base.url");
        WebDriverManager.chromedriver().setup();

        String passwd = ConfigLoad.get("password");
        String usernam = ConfigLoad.get("username");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu",
                "--window-size=1920,1080");

        WebDriver driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Thread.sleep(2000);

        System.out.println("run in jenkins test, im here jj");
        Assert.assertTrue(false);

        WebElement username = driver.findElement(By.cssSelector("#outlined-basic"));
        username.sendKeys(usernam);
        WebElement password = driver.findElement(RelativeLocator.with(By.tagName("input")).below(username));
        password.sendKeys(passwd);
        WebElement forgetPassword = driver.findElement(RelativeLocator.with(By.tagName("a")).below(password));
        WebElement click = driver.findElement(RelativeLocator.with(By.tagName("button")).below(forgetPassword));
        click.click();
        driver.quit();

    }

}
