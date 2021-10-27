package com.TseniukhAnton.javacore.chapter28;

import java.util.concurrent.atomic.*;

public class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}

class Shared3 {
    static AtomicInteger ai = new AtomicInteger(0);
}

class AtomThread implements Runnable {
    String name;

    AtomThread(String n) {
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("Thread launch " + name);
        for (int i = 1; i <= 3; i++) {
            System.out.println("Thread " + name + " received: " + Shared3.ai.getAndSet(i));
        }
    }
}