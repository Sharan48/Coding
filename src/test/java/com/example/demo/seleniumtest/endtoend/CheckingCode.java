package com.example.demo.seleniumtest.endtoend;

public class CheckingCode {

    static char i;

    public void test() {
        int i = 9;
        System.out.println(i);
        System.out.println(this.i);

    }

    public static void main(String[] args) {
        CheckingCode check = new CheckingCode();
        check.test();
        String name = "sharan";
        int sm = name.compareTo("sharan");
        System.out.println(sm);

    }

}
