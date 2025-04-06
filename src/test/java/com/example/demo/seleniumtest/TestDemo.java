package com.example.demo.seleniumtest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Flipkart homepage opened successfully");
        driver.findElement(By.xpath("//span[text()='Electronics']")).click();

        List<WebElement> listOfLink = driver.findElements(By.xpath("//div[@class ='_16rZTH']/object/a"));

        List<String> ascOrder = new ArrayList<>();

        for (WebElement link : listOfLink) {
            System.out.println("Link: " + link.getText());
            String linksText = link.getText();
            if (!linksText.isEmpty()) {
                ascOrder.add(linksText);
            }

        }

        Collections.reverse(ascOrder);
        System.out.println("Links in Ascending Order:");
        for (String text : ascOrder) {
            System.out.println(text);

        }

        for (WebElement link : listOfLink) {
            String linksText = link.getText();
            if ("Gaming".equals(linksText)) {
                link.click();

            }
        }

        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions
                .elementToBeClickable((By.xpath("//a[text()='STRIFF Mobile Phone Cooler-BLA Cooling Pad']"))));
        button.click();
        // driver.findElement(By.xpath("//a[text()='STRIFF Mobile Phone Cooler-BLA
        // Cooling Pad']"))
        // .click();

    }

}
