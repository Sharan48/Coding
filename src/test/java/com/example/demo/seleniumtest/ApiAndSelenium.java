package com.example.demo.seleniumtest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v117.network.model.Response;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
// import org.hamcrest.Matchers.*;
import org.hamcrest.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ApiAndSelenium {

        public WebDriver driver;

        public ApiAndSelenium() {
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
        }

        @Test
        public void login() throws IOException {
                // Selenium test using RestAssured
                // API test using RestAssured
                // Both tests can be written in Java or using frameworks like JUnit, TestNG,
                // etc.

                byte[] file = Files.readAllBytes(Paths.get("src/main/resources/login.json"));
                String body = new String(file);

                RequestSpecification url = new RequestSpecBuilder().setBaseUri("https://reqres.in")
                                .build();

                // RestAssured.given().spec(url).body(body).when()
                // .post("/api/users")
                // .then().assertThat()
                // .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user_schema.json")).log();
                RestAssured.given().spec(url).when().get("/api/users/4").then().assertThat()
                                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user_get.json"));

                // io.restassured.response.Response respnse =
                // RestAssured.given().spec(url).when().get("/api/users/4").then()
                // .extract().response();

                // String name = respnse.jsonPath().get("data.first_name");
                // System.out.println(name);
                io.restassured.response.Response repsons = RestAssured.given().baseUri("https://reqres.in").when()
                                .get("/api/users");
                repsons.then().body("data", Matchers.matchesPattern("^.+@(gmail|bscxpress).com"));

        }

        @Test
        public void operaDevLogin() throws IOException {
                byte[] file = Files.readAllBytes(Paths.get("src/main/resources/operaDevLogin.json"));

                String body = new String(file);

                HashMap<String, String> token = new HashMap<>();
                token.put("x-app-token", "username");

                RequestSpecification url = new RequestSpecBuilder()
                                .setBaseUri("http://52.66.141.164:8001/opera-services")
                                .build();

                ResponseSpecification rp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

                RestAssured.given().body(body).when().get("http://52.66.141.164:8001/opera-services/v1/opera/branch")
                                .then()
                                .log().all();

        }

        @Test
        public void testNaukariWeb() throws IOException {

                byte[] file = Files.readAllBytes(Paths.get("src/main/resources/login.json"));
                String body = new String(file);

                RequestSpecification url = new RequestSpecBuilder().setBaseUri("https://reqres.in")
                                .build();
                RestAssured.given().spec(url).when().get("/api/users/4").then().log().all();

                WebDriverManager.chromedriver().setup();

                ChromeOptions option = new ChromeOptions();
                option.addArguments("--disable-notifications");
                WebDriver driver = new ChromeDriver(option);

                driver.get("http://dev-opera.bscxpress.com.s3-website.ap-south-1.amazonaws.com/login");
                driver.manage().window().maximize();

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                // WebElement username = driver.findElement(By.id("outlined-basic"));
                // username.sendKeys("sharantest");

                FluentWait<WebDriver> wat = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

                WebElement usrname = wat.until(new Function<WebDriver, WebElement>() {

                        @Override
                        public WebElement apply(WebDriver arg0) {
                                return arg0.findElement(By.id("outlined-basic"));
                        }

                });
                usrname.sendKeys("sharantest");

                WebElement password = driver.findElement(RelativeLocator.with(By.tagName("input")).below(usrname));
                password.sendKeys("password");

                WebElement loginButton = driver.findElement(RelativeLocator.with(By.tagName("button")).below(password));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions
                                .elementToBeClickable(driver.findElement(
                                                RelativeLocator.with(By.tagName("button")).below(password))));
                loginButton.click();

        }

        @Test()
        public void testDropDown() {

                driver.get("https://demoqa.com/select-menu");
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                // WebElement dropdiv = driver.findElement(By.id("selectOne"));
                // dropdiv.click();
                // java.util.List<WebElement> option = driver
                // .findElements(By.xpath("//div[@class=' css-26l3qy-menu']"));

                // for (WebElement optionElement : option) {
                // System.out.println(optionElement.getText());
                // if (optionElement.getText().equals("Ms.")) {

                // break;
                // }

                // }
                // WebElement ele = driver.findElement(By.id("oldSelectMenu"));
                // Select select = new Select(ele);
                // java.util.List<WebElement> list = select.getOptions();
                // for (WebElement option : list) {
                // System.out.println(option.getText());
                // }
                // select.selectByValue("2");

                WebElement ele = driver.findElement(By.id("cars"));

                Select sele = new Select(ele);

                if (sele.isMultiple()) {
                        sele.selectByVisibleText("Volvo");
                        sele.selectByValue("saab");
                        sele.selectByIndex(3);
                }

                Set<String> wnd = driver.getWindowHandles();
                for (String child : wnd) {
                        if (child.equals("switch to present url")) {
                                driver.switchTo().window(child);
                        }

                }

        }
}
