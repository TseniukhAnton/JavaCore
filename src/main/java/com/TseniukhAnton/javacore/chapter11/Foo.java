package com.TseniukhAnton.javacore.chapter11;

import java.util.concurrent.Semaphore;

public class Foo {
    public void first(Runnable r) {
        System.out.print("first");
    }

    public void second(Runnable r) {
        System.out.print("second");
    }

    public void third(Runnable r) {
        System.out.print("third");
    }
}

class Launcher implements Runnable {
    Foo target;
    Thread t;

    public Launcher(Foo targ) {
        target = targ;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        //synchronized (target) {
        target.first(this);
        target.second(this);
        target.third(this);
        //}
    }
}

class Launch {
    public static void main(String[] args) {
        Foo target = new Foo();
        Launcher obj1 = new Launcher(target);
        Launcher obj2 = new Launcher(target);
        Launcher obj3 = new Launcher(target);

        try {
            obj1.t.join();
            obj2.t.join();
            obj3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}


















