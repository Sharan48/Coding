package com.example.demo.seleniumtest.corejava.Threads;

public class Counter {

    int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(count);
    }

}

class Test {
    public static void main(String[] args) throws InterruptedException {
        Counter count = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                count.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 10; j++)
                count.increment();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
