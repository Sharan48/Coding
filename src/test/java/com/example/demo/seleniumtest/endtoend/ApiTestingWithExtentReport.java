package com.example.demo.seleniumtest.endtoend;

import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTestingWithExtentReport {

    private ExtentTest test;

    @Test(priority = 0)
    public void getAPi() {
        test = ExtentReport.extentTest("GET /get - validate status code");

        Response repsnse = RestAssured.given().headers("Content-Type ", "application/json; charset=utf-8")
                .baseUri("https://reqres.in/").when().get("/api/users/2");
        Headers heade = repsnse.getHeaders();
        for (Header hesd : heade) {
            System.out.println(hesd.getName() + " " + hesd.getValue());
            test.pass("GET request returned status code 200  " + hesd.getName() + "  " + hesd.getValue());
        }

        test.pass("GET request returned status code 200");

        // Cookies
        Map<String, String> cokies = repsnse.getCookies();
        for (Map.Entry<String, String> entry : cokies.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());

        }
        Cookies det = repsnse.getDetailedCookies();
        List<Cookie> lis = det.asList();
        for (Cookie kk : lis) {
            System.out.println(kk.getName());
        }

        // Headers

        Headers heder = repsnse.getHeaders();
        List<Header> lst = heder.asList();
        for (Header hd : lst) {
            System.out.println(hd.getName());
            System.out.println(hd.getValue());
        }

    }

    @Test(priority = 1)
    public void getAll() {

        Response repons = RestAssured.given()
                .baseUri("https://reqres.in/").when()
                .get("/api/users?page=2");
        test.info("Getted all recordds");
        test.info("log all " + repons.prettyPrint());
        JsonPath path = repons.body().jsonPath();

    }

    @AfterMethod
    public void flushReport() {
        ExtentReport.extentFlush();
    }

}
