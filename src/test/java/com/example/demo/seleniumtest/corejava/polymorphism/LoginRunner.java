package com.example.demo.seleniumtest.corejava.polymorphism;

public class LoginRunner {

    public void executeMethods(LoginPage loginPage) {
        loginPage.logIn();
        System.out.println("is Logged in ? : " + loginPage.isLoggin());
        loginPage.logOut();
    }
}
