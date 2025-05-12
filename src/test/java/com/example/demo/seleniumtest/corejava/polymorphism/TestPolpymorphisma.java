package com.example.demo.seleniumtest.corejava.polymorphism;

public class TestPolpymorphisma {

    public static void main(String[] args) {

        LoginRunner runner = new LoginRunner();
        runner.executeMethods(new AdminLoginPage());
        runner.executeMethods(new GuestLoginPage());
        runner.executeMethods(new UserLoginPage());
    }

}
