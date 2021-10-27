package com.TseniukhAnton.javacore.chapter28;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new LockThread(lock, "A");
        new LockThread(lock, "B");
    }
}


class Shared2 {
    static int count = 0;
}

class LockThread implements Runnable {
    String name;
    ReentrantLock lock;

    LockThread(ReentrantLock lk, String n) {
        lock = lk;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("Thread launch " + name);

        try {
            System.out.println("Thread " + name + " awaits counter blocking");
            lock.lock();
            System.out.println("Thread " + name + " blocks counter");
            Shared2.count++;
            System.out.println("Thread " + name + ": " + Shared2.count);
            System.out.println("Thread " + name + " waits");
            Thread.sleep(1000);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        } finally {
            System.out.println("Thread " + name + " unlocks counter");
            lock.unlock();
        }
    }
}





















