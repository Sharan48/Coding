package com.example.demo.seleniumtest.inheritance.interfacetest;

public class ImplentInerfce implements TestInteraface, InterfaceA, InterfaceB {

    @Override
    public void test() {
        System.out.println("implementaed");
        defaultTest();
    }

    @Override
    public void defaultTest() {
        System.out.println("overrided");
    }

    @Override
    public void sayHello() {
        System.out.println("implemented in class");
        InterfaceA.super.sayHello();
        InterfaceB.super.sayHello();

    }

    public static void main(String[] args) {
        ImplentInerfce im = new ImplentInerfce();
        im.test();
        im.defaultTest();
        im.sayHello();

        int value = TestInteraface.a;
        value = 99;
        System.out.println(value);

        TestInteraface.staticMethod();

    }

}
