package com.example.demo.seleniumtest;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Asserts;
import org.checkerframework.checker.units.qual.t;
import org.mozilla.javascript.JavaScriptException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

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

        WebDriver driver = new ChromeDriver(options);
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

    @Test(enabled = false)
    public void elementTest() {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();
        // driver.get("https://www.tutorialspoint.com/selenium/practice/webtables.php");
        // driver.navigate().to("https://demoqa.com/dynamic-properties");
        driver.get("https://www.tutorialspoint.com/selenium/practice/date-picker.php");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // List<WebElement> table = driver.findElements(By.cssSelector("table > thead
        // >tr"));

        // for (WebElement ele : table) {
        // String text = ele.getText();
        // System.out.println(text);
        // }

        // List<WebElement> tbody = driver.findElements(By.cssSelector("tbody>tr"));
        // for (WebElement xc : tbody) {
        // List<WebElement> child = xc.findElements(By.xpath("td[not(a)]"));
        // for (WebElement lk : child) {
        // System.out.println(lk.getText());
        // }
        // }

        // Incluede link
        // List<WebElement> tabledata = driver.findElements(By.cssSelector("tbody
        // tr:nth-of-type(1) td"));

        // for (WebElement kk : tabledata) {
        // System.out.println(kk.getText());
        // }

        // WebElement all = driver.findElement(By.cssSelector("tbody
        // tr:nth-of-type(1)"));
        // without link
        // List<WebElement> jk = all.findElements(By.xpath("td[not(a)]"));
        // for (WebElement lk : jk) {
        // System.out.println(lk.getText());
        // }

        // List<WebElement> jk = all.findElements(By.cssSelector("td>a"));
        // for (WebElement lk : jk) {
        // System.out.println(lk.getText());
        // System.out.println(lk.getAttribute("href"));
        // System.out.println(lk.getTagName());
        // }

        // handling dynamic element
        // we have to travell from static element to dynamic element
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // WebElement ele = wait.until(ExpectedConditions
        // .visibilityOfElementLocated(By.xpath("//button[@id='enableAfter']/following-sibling::button[2]")));
        // System.out.println(ele.getText());

        // DatePicker Readonly
        WebElement date = driver.findElement(By.id("datetimepicker1"));

        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("arguments[0].value='2025-03-21'", date);

        // driver.quit();

    }

    @Test(priority = 0, enabled = false)
    public void shadowRoot() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://practice.expandtesting.com/shadowdom");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        WebElement shadowHost = driver.findElement(By.id("shadow-host"));

        // Get the shadow root using Selenium 4's getShadowRoot()
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        // Find the element inside the shadow DOM
        WebElement button = shadowRoot.findElement(By.cssSelector("#my-btn"));

        // Print button text
        System.out.println(button.getText());

        driver.quit();

    }

    @Test(priority = 1, enabled = false)
    public void nestedShadowRoot() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("http://watir.com/examples/shadow_dom.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Access first Shadow DOM
        WebElement first = driver.findElement(By.id("shadow_host"));
        SearchContext sdw1 = first.getShadowRoot();
        WebElement text = sdw1.findElement(By.cssSelector("#shadow_content"));
        Assert.assertEquals(text.getText(), "some text");
        System.out.println(text.getText());

        // Access nested Shadow DOM
        WebElement second = sdw1.findElement(By.cssSelector("#nested_shadow_host"));
        SearchContext sdw2 = second.getShadowRoot();
        WebElement sontent = sdw2.findElement(By.cssSelector("#nested_shadow_content"));
        Assert.assertEquals(sontent.getText(), "nested text");
        System.out.println(sontent.getText());

        driver.quit();

    }

    @Test
    public void testScrollPage() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://infinite-scroll.com/demo/full-page");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        int scroll = 6;

        for (int i = 1; i <= scroll; i++) {
            JavascriptExecutor scpt = (JavascriptExecutor) driver;
            scpt.executeScript("window.scrollBy(0,document.body.scrollHeight);");
            Thread.sleep(2000);
        }

        Thread.sleep(2000);
        WebElement located = driver.findElement(By.xpath("//p[text()='End of content']"));
        System.out.println(located.getText());

    }

    @Test
    public void unKnownPage() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://infinite-scroll.com/demo/full-page");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        int scroll = 6;
        boolean found = false;

        for (int i = 1; i <= scroll; i++) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
            Thread.sleep(1500);

            try {
                WebElement target = driver.findElement(By.xpath("//p[text()='End of content']"));
                String text = target.getText();
                if (!text.isEmpty()) {
                    System.out.println("ELement found at page " + scroll + " " + text);
                    found = true;
                    break;
                }

            } catch (NoSuchElementException e) {
                System.out.println("scroll " + i + " :Element is not found");
            }

        }
        if (!found) {
            System.out.println("Elemet not found after maximum scroll: " + scroll);
        }

        driver.quit();

    }

    @Test
    public void testPagination() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://practice.expandtesting.com/dynamic-pagination-table");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        boolean nextPage = true;

        while (nextPage) {

            Thread.sleep(2000);

            // Here fetching data from table
            List<WebElement> list = driver.findElements(By.cssSelector("tbody#demo tr td"));
            for (WebElement ele : list) {
                System.out.println(ele.getText());
            }
            // parent element
            // if you try to using child element at the end you will get stale element
            // reference.
            // In this pagination im clicking on linkText
            // <li class="paginate_button page-item next disabled" id="example_next">
            // <a class="page-link" aria-disabled="true">Next</a>
            // </li>
            WebElement pageNext = driver.findElement(By.id("example_next"));
            Thread.sleep(2000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.id("example_next")));

            // check class attribute have disabled value then break the loop bcz the button
            // become a disabled
            String check = pageNext.getAttribute("class");
            if (check.contains("disabled")) {
                nextPage = false;
            } else {
                // child element
                WebElement lnk = pageNext.findElement(By.tagName("a"));
                lnk.click();
            }

        }

        driver.quit();

    }

}
