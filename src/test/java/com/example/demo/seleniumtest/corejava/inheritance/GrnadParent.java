package com.example.demo.seleniumtest.corejava.inheritance;

public class GrnadParent {

    public final static int age = 10;
    String name = "grand-parent";
    static String last = "pujari";

    public static void methodOfGrnad() {
        System.out.println("grand parent static method");
    }

    public void methodOfGrandParent() {
        System.out.println("Grnad parent non static method");
    }

    void show() {
        System.out.println("Inside Parent show()");
    }

}
