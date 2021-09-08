package com.TseniukhAnton.javacore.chapter15;

import java.util.function.Function;

public class RealLambdaUsage {

}

@FunctionalInterface
interface MyFunc1<R, T> {
    R func(T n);
}

class MyClass1<T> {
    private T val;

    MyClass1(T v) {
        val = v;
    }

    MyClass1() {
        val = null;
    }

    T getVal() {
        return val;
    }
}

class MyClass2 {
    String str;

    MyClass2(String s) {
        str = s;
    }

    MyClass2() {
        str = "";
    }

    String getStr() {
        return str;
    }
}

class ConstructorRefDemo3 {
    static <R, T> R myClassFactory(MyFunc1<R, T> cons, T v) {
        return cons.func(v);
    }

    public static void main(String[] args) {
        MyFunc1<MyClass1<Double>, Double> myClassCons = MyClass1::new;

        MyClass1<Double> mc = myClassFactory(myClassCons, 100.1);

        System.out.println("val value in the mc object equals " + mc.getVal());

        MyFunc1<MyClass2, String> myClassCons2 = MyClass2::new;

        MyClass2 mc2 = myClassFactory(myClassCons2, "Lambda");
        System.out.println("str value in the mc2 object equals " + mc2.getStr());

    }
}

class UseFunctionInterfaceDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("Factorial of number 3 equals " + factorial.apply(3));
        System.out.println("Factorial of number 5 equals " + factorial.apply(5));
    }
}



