package com.example.demo.seleniumtest.corejava.interfacetest;

public interface InterfaceA {

    void test();

    default void sayHello() {
        System.out.println("inteface a");
    }

}
