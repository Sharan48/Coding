package com.example.demo.seleniumtest.endtoend;

import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {

    @Test
    public void loginToPage() {
        System.out.println("Driver is: " + getDriver());
        LoginPage loginPage = new LoginPage();
        String message = loginPage.loginToPage("sharantest", "password");
        System.out.println(message);
    }

}
