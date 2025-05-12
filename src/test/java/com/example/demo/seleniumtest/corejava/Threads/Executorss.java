package com.example.demo.seleniumtest.corejava.Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executorss implements Runnable {

    int count = 0;

    @Override
    public synchronized void run() {
        count++;
        System.out.println(count);
    }

}

class Test1 {
    public static void main(String[] args) {
        Executorss exe = new Executorss();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executor.execute(exe);
            // executor.execute(exe);
        }

        executor.shutdown();

    }
}