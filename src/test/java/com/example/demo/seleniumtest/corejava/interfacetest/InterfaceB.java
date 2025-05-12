package com.example.demo.seleniumtest.corejava.interfacetest;

public interface InterfaceB {

    void test();

    default void sayHello() {
        System.out.println("interface b");
    }

}