package com.TseniukhAnton.javacore.chapter11;

public class SimilarToHomeWork {

}

class Callme1 {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.print("Interrupted");
        }
        System.out.print("]");
    }
}

class Caller1 implements Runnable {
    String msg;
    Callme1 target;
    Thread t;

    public Caller1(Callme1 targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        synchronized (target) {
            target.call(msg);
        }
    }
}

class Synch1 {
    public static void main(String[] args) {
        Callme1 target = new Callme1();
        Caller1 obj1 = new Caller1(target, "Welcome");
        Caller1 obj2 = new Caller1(target, "to synchronized");
        Caller1 obj3 = new Caller1(target, "world!!!");

        try {
            obj1.t.join();
            obj2.t.join();
            obj3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
