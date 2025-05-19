package com.example.demo.seleniumtest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraTest {

    @Test
    public void setUpChrome() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.myntra.com/");
        System.out.println("Myntra homepage opened successfully");

        // List of links
        List<WebElement> links = driver.findElements(By.tagName("a"));
        // for (WebElement link : links) {
        // System.out.println("Link: " + link.getText());
        // String linksText = link.getText();
        // if (!linksText.isEmpty()) {
        // System.out.println("Link: " + link.getAttribute("href"));
        // }
        // }

        WebElement kids = driver.findElement(By.linkText("Kids"));

        Actions action = new Actions(driver);
        action.moveToElement(kids).perform();

        List<WebElement> boys = driver.findElements(By.xpath("(//ul[@class='desktop-navBlock'])[1]/li/a"));

        for (WebElement boy : boys) {
            System.out.println(boy.getText());
        }
    }

    public static void urlConnection(String url) throws MalformedURLException, IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();

        int code = connection.getResponseCode();

        if (code >= 400) {
            System.out.println("Broken link" + url + " response code " + code);
        } else {
            System.out.println("valid link " + url);
        }
        connection.disconnect();
    }

}
