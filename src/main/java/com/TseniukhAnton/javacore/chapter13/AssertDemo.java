package com.TseniukhAnton.javacore.chapter13;
// проверка утверждений
class AssertDemo {
    static int val = 3;

    static int getnum() {
        return val--;
    }

    public static void main(String[] args) {
        int n;
        for (int i = 0; i < 10; i++) {
            n = getnum();
            assert n > 0 : "n is negative";
            System.out.println("n equals " + n);
        }
    }
}
