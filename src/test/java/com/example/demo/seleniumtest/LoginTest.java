package com.example.demo.seleniumtest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    @Test(dataProvider = "ExcelDataProvider", dataProviderClass = DataDriven.class)
    public void testLogin(String username, String password) {

        System.out.println("Testing login with" + username + "|" + password);

    }

    // @Test
    public void testTable() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        int row = driver.findElements(By.xpath("//tbody[@style=\"box-sizing:inherit\"]/tr")).size();
        int col = driver.findElements(By.xpath("//tbody[@style=\"box-sizing:inherit\"]/tr[1]/td")).size();
        System.out.println("Total rows are : " + row);
        System.out.println("Total columns are : " + col);

        java.util.List<WebElement> rowData = driver.findElements(By.xpath("//tbody[@style='box-sizing:inherit']/tr"));

        for (WebElement data : rowData) {
            List<WebElement> cellData = data.findElements(By.tagName("td"));
            for (WebElement cell : cellData) {
                String text = cell.getText();
                System.out.println("text: " + text);
            }

        }
    }

    @Test
    public void relativeLocator() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://dev-opera.bscxpress.com.s3-website.ap-south-1.amazonaws.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);

        System.out.println("run in jenkins test 45 uu  ggg gg ggg jj");

        WebElement username = driver.findElement(By.cssSelector("#outlined-basic"));
        username.sendKeys("sharantest");
        WebElement password = driver.findElement(RelativeLocator.with(By.tagName("input")).below(username));
        password.sendKeys("password");
        WebElement forgetPassword = driver.findElement(RelativeLocator.with(By.tagName("a")).below(password));
        WebElement click = driver.findElement(RelativeLocator.with(By.tagName("button")).below(forgetPassword));
        click.click();
        driver.quit();

    }

}
