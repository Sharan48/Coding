package com.example.demo.seleniumtest.corejava.polymorphism;

public class GuestLoginPage implements LoginPage {

    public volatile boolean loggedIn = false;

    @Override
    public void logIn() {
        System.out.println("guest logged in");
        loggedIn = true;
    }

    @Override
    public void logOut() {
        System.out.println("guest logged out");
        loggedIn = false;
    }

    @Override
    public boolean isLoggin() {
        return loggedIn;
    }

}
