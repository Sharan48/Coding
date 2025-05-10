package com.example.demo.seleniumtest.endtoend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
    WebDriver driver;

    @FindBy(id = "outlined-basic")
    private WebElement usernameField;

    @FindBy(id = "outlined-adornment-password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public String loginToPage(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return "Login successful";
    }
}
