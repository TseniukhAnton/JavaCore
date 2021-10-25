package com.TseniukhAnton.javacore.chapter28;

import java.util.concurrent.*;

public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        new IncThread(sem, "A");
        new DecThread(sem, "B");
    }
}

class Shared {
    static int count = 0;
}

class IncThread implements Runnable {
    String name;
    Semaphore sem;

    IncThread(Semaphore s, String n) {
        sem = s;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("Launch Thread " + name);
        try {
            System.out.println("Thread " + name + " waits permission");
            sem.acquire();
            System.out.println("Thread " + name + " gets permission");

            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println(name + ": " + Shared.count);

                Thread.sleep(10);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println("Thread " + name + " releases permission");
        sem.release();
    }
}

class DecThread implements Runnable {
    String name;
    Semaphore sem;

    public DecThread(Semaphore s, String n) {
        sem = s;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("Launch Thread " + name);

        try {
            System.out.println("Thread " + name + " waits permission");
            sem.acquire();
            System.out.println("Thread " + name + " gets permission");

            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println("Thread " + name + " releases permission");
        sem.release();
    }
}

class G {
    int n;

    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    void get() {
        try {
            semCon.acquire();
        } catch (InterruptedException e) {
            System.out.println("Exception caught");
        }
        System.out.println("Received: " + n);
        semProd.release();
    }

    void put(int n) {
        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            System.out.println("Exception caught");
        }
        this.n = n;
        System.out.println("Sent: " + n);
        semCon.release();
    }
}

class Producer implements Runnable {
    G g;

    Producer(G g) {
        this.g = g;
        new Thread(this, "Producer").start();
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            g.put(i);
        }
    }
}

class Consumer implements Runnable {
    G g;

    public Consumer(G g) {
        this.g = g;
        new Thread(this, "Consumer").start();
    }

    public void run(){
        for (int i = 0; i < 20; i++) {
            g.get();
        }
    }
}

class ProdCon{
    public static void main(String[] args) {
        G g = new G();
        new Consumer(g);
        new Producer(g);
    }
}















