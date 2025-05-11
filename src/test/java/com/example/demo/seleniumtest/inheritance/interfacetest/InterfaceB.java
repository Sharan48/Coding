package com.example.demo.seleniumtest.inheritance.interfacetest;

public interface InterfaceB {
    default void sayHello() {
        System.out.println("interface b");
    }

}