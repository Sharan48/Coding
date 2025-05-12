package com.example.demo.seleniumtest.corejava;

public class Logger {

    // Testing singleton pattern

    private static Logger instance;

    private Logger() {
        System.out.println("Logger ini....... ");
    }

    public static synchronized Logger getInstance() {

        if (instance == null) {
            synchronized (Logger.class) {
                instance = new Logger();
            }

        }
        return instance;
    }

    public void log(String name) {
        System.out.println("Log :" + name);
    }
}

class Main {

    public static void main(String[] args) {
        Logger log1 = Logger.getInstance();
        log1.log("first");

        Logger log2 = Logger.getInstance();
        log1.log("second");

        System.out.println(log1 == log2);
    }

}