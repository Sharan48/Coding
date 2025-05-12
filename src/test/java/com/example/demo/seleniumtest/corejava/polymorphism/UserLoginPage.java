package com.example.demo.seleniumtest.corejava.polymorphism;

public class UserLoginPage implements LoginPage {
    private volatile boolean loggedIn = false;

    @Override
    public void logIn() {
        System.out.println("User logged in");
        loggedIn = true;
    }

    @Override
    public void logOut() {
        System.out.println("User logged out");
        loggedIn = false;
    }

    @Override
    public boolean isLoggin() {
        return loggedIn;
    }

}
