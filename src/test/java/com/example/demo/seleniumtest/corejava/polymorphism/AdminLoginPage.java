package com.example.demo.seleniumtest.corejava.polymorphism;

public class AdminLoginPage implements LoginPage {

    private volatile boolean loggedIn = false;

    @Override
    public void logIn() {
        System.out.println("Admin logged in");
        loggedIn = true;
    }

    @Override
    public void logOut() {
        System.out.println("Admin logged ou");
        loggedIn = false;
    }

    @Override
    public boolean isLoggin() {
        return loggedIn;
    }

}
