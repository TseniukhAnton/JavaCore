package com.TseniukhAnton.javacore.chapter11;

public class DeadLockDemo {

}
class A {
    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();

        System.out.println(name + " entered into A.foo()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Class A has been interrupted");
        }
        System.out.println(name + " tries to call B.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("In the meth A.last()");
    }
}

class B {
    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();

        System.out.println(name + " entered into B.bar()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Class B has been interrupted");
        }
        System.out.println(name + " tries to call A.last()");
        a.last();
    }

    synchronized void last() {
        System.out.println("In the meth B.last()");
    }
}

class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    void Deadlock () {
        Thread.currentThread().setName("Main Thread");
        Thread t = new Thread(this, "Conquer Thread");
        t.start();

        a.foo(b);
        System.out.println("Back to the main Thread");
    }

    public void run() {
        b.bar(a);
        System.out.println("Back to another Thread");
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}



