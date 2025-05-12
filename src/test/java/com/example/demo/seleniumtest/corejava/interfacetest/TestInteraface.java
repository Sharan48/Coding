package com.example.demo.seleniumtest.corejava.interfacetest;

public interface TestInteraface {

    public static final int a = 100;

    public abstract void test();

    public default void defaultTest() {
        privateMethod();
        System.out.println("test");
    }

    public static void staticMethod() {
        System.out.println("static method");
    }

    private void privateMethod() {
        staticMethod();
        System.out.println("test private method");
    }

}
