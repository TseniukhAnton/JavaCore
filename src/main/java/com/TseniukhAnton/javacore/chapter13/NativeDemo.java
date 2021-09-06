package com.TseniukhAnton.javacore.chapter13;

public class NativeDemo {
    int i;

    public static void main(String[] args) {
        NativeDemo ob = new NativeDemo();

        ob.i = 10;
        System.out.println("variable ob.i before platform oriented call " + ob.i);

        ob.test();// call platform oriented method
        System.out.println("variable ob.i after platform oriented call " + ob.i);

    }
    public native void test();
    static{
        System.loadLibrary("NativeDemo");
    }
}
