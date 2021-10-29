package com.TseniukhAnton.javacore.chapter28;

public class Foo {
    public void first(Runnable r) {
        System.out.println("first");
    }

    public void second(Runnable r) {
        System.out.println("second");
    }

    public void third(Runnable r) {
        System.out.println("third");
    }
}

class GetCall implements Runnable {

    public GetCall() {
        new Thread(this).start();
    }

    public void run() {
        //System.out.printf("Thread number %s started ", Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Exception " + e);
        }

        //System.out.printf(" Thread number %s finished", Thread.currentThread().getName());
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("Main Thread Start");
        Foo foo = new Foo();
        GetCall threadA = new GetCall();
        GetCall threadB = new GetCall();
        GetCall threadC = new GetCall();
        foo.first(threadA);
        foo.second(threadB);
        foo.third(threadC);
        System.out.println("Main Thread Finish");
    }
}