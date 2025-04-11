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
    public void testTable() throws InterruptedException, IOException {
        String env = System.getProperty("env", "qa");
        ConfigLoad.load(env);
        String url = ConfigLoad.get("base.url");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(url);
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
        String qa = System.getProperty("env", "qa");
        ConfigLoad.load(qa);
        String url = ConfigLoad.get("base.url");
        WebDriverManager.chromedriver().setup();

        String passwd = ConfigLoad.get("password");
        String usernam = ConfigLoad.get("username");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu",
                "--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);

        System.out.println("run in jenkins test, im here jj");

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
