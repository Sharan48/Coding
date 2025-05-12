package com.example.demo.seleniumtest.corejava.enumstest;

public class TestEnum {

    public static void main(String[] args) {
        Day day = Day.MONDAY;
        System.out.println(day);
        Day d = Day.valueOf("MONDAY");
        System.out.println(d);

        Day[] list = Day.values();
        for (Day dys : list) {
            System.out.println(dys.name());
        }

        HttpMehods get = HttpMehods.GET;
        System.out.println(get);

        int st = get.getStatus_Code();
        String met = get.getMethod();
        System.out.println(st);

        String str = String.format("checking %s status code %d in enum", get.getMethod(), get.getStatus_Code());
        System.out.println(str);

        HttpMehods[] ls = HttpMehods.values();
        for (HttpMehods hh : ls) {
            String str1 = String.format("checking %s status code %d in enum", hh.getMethod(), hh.getStatus_Code());
            System.out.println(str1);
        }
    }

}
