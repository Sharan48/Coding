package com.example.demo.seleniumtest.corejava.inheritance;

public class child extends Parent {

    static int age = 10;
    String name = "parent";

    public static void methodOfGrnad() {
        System.out.println("parent static method");
    }

    public void methodOfGrandParent() {
        System.out.println("parent non static method");
    }

}
