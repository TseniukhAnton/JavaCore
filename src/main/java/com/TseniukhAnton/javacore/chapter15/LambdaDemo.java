package com.TseniukhAnton.javacore.chapter15;

public class LambdaDemo {
    public static void main(String[] args) {
        MyNumber myNum;
        myNum = () -> 123.45;
        System.out.println("Fixed number: " + myNum.getValue());
        myNum = () -> Math.random() * 100;

        System.out.println("Random value: " + myNum.getValue());
        System.out.println("Another Random value: " + myNum.getValue());

        //  myNum = () -> "123.45";
    }
}

@FunctionalInterface
interface MyNumber {
    double getValue();
}

@FunctionalInterface
interface NumericTest {
    boolean test(int n);
}

class LambdaDemo2 {
    public static void main(String[] args) {
        NumericTest isEven = n -> (n % 2) == 0;

        if (isEven.test(10)) System.out.println("Number 10 is even");
        if (!isEven.test(9)) System.out.println("Number 9 is odd");

        NumericTest isNonNeg = n -> n >= 0;

        if (isNonNeg.test(1)) System.out.println("Number 1 is not negative");
        if (!isNonNeg.test(-1)) System.out.println("Number -1 is negative");
    }
}

@FunctionalInterface
interface NumericTest2 {
    boolean test(int n, int d);
}

class LambdaDemo3 {
    public static void main(String[] args) {
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;

        if (isFactor.test(10, 2)) System.out.println("Number 2 is factor of 10");
        if (!isFactor.test(10, 3)) System.out.println("Number 3 is not factor of 10");
    }
}

@FunctionalInterface
interface NumericFunc {
    int func(int n);
}

class BlockLambdaDemo {
    public static void main(String[] args) {
        NumericFunc factorial = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("Factorial of 3 equals: " + factorial.func(3));
        System.out.println("Factorial of 5 equals: " + factorial.func(5));
    }
}

@FunctionalInterface
interface StringFunc {
    String func(String n);
}

class BlockLambdaDemo2 {
    public static void main(String[] args) {
        StringFunc reverse = (str) -> {
            String result = "";
            int i;
            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };
        System.out.println("Lambda refers to " + reverse.func("Lambda"));
        System.out.println("Expression refers to " + reverse.func("Expression"));
    }
}

@FunctionalInterface
interface SomeFunc<T> {
    T func(T t);
}

class GenericFunctionalInterfaceDemo {
    public static void main(String[] args) {
        SomeFunc<String> reverse = (str) -> {
            String result = "";
            int i;

            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };

        System.out.println("Lambda refers to " + reverse.func("Lambda"));
        System.out.println("Expression refers to " + reverse.func("Expression"));


        SomeFunc<Integer> factorial = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };

        System.out.println("Factorial from 3 equals " + factorial.func(3));
        System.out.println("Factorial from 5 equals " + factorial.func(5));

    }
}





















