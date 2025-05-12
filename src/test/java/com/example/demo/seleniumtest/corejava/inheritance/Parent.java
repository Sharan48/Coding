package com.example.demo.seleniumtest.corejava.inheritance;

public class Parent extends GrnadParent {

    static int age = 20;
    String name = "parent";

    Parent() {
        this.name = super.name;
    }

    public static void methodOfGrnad() {
        System.out.println("parent static method");
        System.out.println(age);
    }

    public void methodOfGrandParent() {
        System.out.println("parent non static method");
        System.out.println(super.name);
        System.out.println(super.age);
        System.out.println(this.name);
        System.out.println(age);
        System.out.println(this.age);
        // this.show();
        System.out.println(last);
        String na = last;
    }

    public static void main(String[] args) {
        // Parent par = new Parent();
        // par.methodOfGrandParent();
        int ss = age;
        System.out.println(ss);
        String jj = last;

        GrnadParent parent = new Parent();
        parent.methodOfGrandParent();
        // parent.methodOfGrnad();

        // String name = parent.name;
        // System.out.println(name);
        // int age = parent.age;
        // System.out.println(age);

    }
}
