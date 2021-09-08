package com.TseniukhAnton.javacore.chapter15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

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

@FunctionalInterface
interface StringFunc2 {
    String func(String n);
}

class LambdasAsArgumentsDemo {
    static String stringOp(StringFunc2 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "Lambdas make Java effective";
        String outStr;

        System.out.println("This is the initial string: " + inStr);

        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("This is a string with uppercase: " + outStr);

        outStr = stringOp((str) -> {
            String result = "";
            int i;

            for (i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ')
                    result += str.charAt(i);
            }
            return result;
        }, inStr);
        System.out.println("This is a string with removed spaces: " + outStr);

        StringFunc2 reverse = (str) -> {
            String result = "";
            int i;

            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };

        System.out.println("This is a reverse string: " + stringOp(reverse, inStr));
    }
}

@FunctionalInterface
interface DoubleNumericArrayFunc {
    double func(double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception {
    EmptyArrayException() {
        super("Array is empty");
    }
}

class LambdaExceptionDemo {
    public static void main(String[] args) throws EmptyArrayException {
        double[] values = {1.0, 2.0, 3.0, 4.0};

        DoubleNumericArrayFunc average = n -> {
            double sum = 0;

            if (n.length == 0)
                throw new EmptyArrayException();

            for (int i = 0; i < n.length; i++) {
                sum += n[i];
            }

            return sum / n.length;
        };
        System.out.println("Average equals " + average.func(values));

        System.out.println("average equals " + average.func(new double[0]));
    }
}

@FunctionalInterface
interface MyFunc {
    int func(int n);
}

class VarCapture {
    public static void main(String[] args) {
        int num = 10;

        MyFunc myLambda = n -> {
            int v = num + n;

            //num++;
            return v;
        };
        //num = 9;
    }
}

@FunctionalInterface
interface StringFuncc {
    String func(String n);
}

class MyStringOps {
    static String strReverse(String str) {
        String result = "";
        int i;

        for (i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }

        return result;
    }
}

class MethodRefDemo {
    static String stringOp(StringFuncc sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "Lambdas make Java effective";
        String outStr;

        outStr = stringOp(MyStringOps::strReverse, inStr);

        System.out.println("Initial string: " + inStr);
        System.out.println("Reverted string: " + outStr);
    }
}

@FunctionalInterface
interface StringFunk {
    String func(String n);
}

class MyStringOps2 {
    String strReverse(String str) {
        String result = "";
        int i;

        for (i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}

class MethodRefDemo2 {
    static String stringOp(StringFunk sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "Lambdas make Java effective";
        String outStr;

        MyStringOps2 strOps = new MyStringOps2();
        outStr = stringOp(strOps::strReverse, inStr);

        System.out.println("Initial string: " + inStr);
        System.out.println("Reverted string: " + outStr);
    }
}

@FunctionalInterface
interface MyFunc2<T> {
    boolean func(T v1, T v2);
}

class HighTemp {
    private int hTemp;

    HighTemp(int ht) {
        hTemp = ht;
    }

    boolean sameTemp(HighTemp ht2) {
        return hTemp == ht2.hTemp;
    }

    boolean lessThanTemp(HighTemp ht2) {
        return hTemp < ht2.hTemp;
    }
}

class InstanceMethWithObjectRefDemo {
    static <T> int counter(T[] vals, MyFunc2<T> f, T v) {
        int count = 0;

        for (int i = 0; i < vals.length; i++) {
            if (f.func(vals[i], v)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int count;
        HighTemp[] weekDayHighs = {new HighTemp(89), new HighTemp(82),
                new HighTemp(90), new HighTemp(89),
                new HighTemp(89), new HighTemp(91),
                new HighTemp(84), new HighTemp(83)};

        count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));

        System.out.println("Number of days with max temp 89: " + count);

        HighTemp[] weekDaysHighs2 = {new HighTemp(32), new HighTemp(12),
                new HighTemp(24), new HighTemp(19),
                new HighTemp(18), new HighTemp(12),
                new HighTemp(-1), new HighTemp(13)};

        count = counter(weekDaysHighs2, HighTemp::sameTemp, new HighTemp(12));
        System.out.println("Number of days with max temp 12: " + count);

        count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
        System.out.println("Number of days when max temp was less than 89: " + count);

        count = counter(weekDaysHighs2, HighTemp::lessThanTemp, new HighTemp(19));
        System.out.println("Number of days when max temp was less than 19: " + count);

    }
}

@FunctionalInterface
interface MyFuncs<T> {
    int func(T[] vals, T v);
}

class MyArrayOps {
    static <T> int countMatching(T[] vals, T v) {
        int count = 0;

        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == v) count++;
        }
        return count;
    }
}

class GenericMethodRefDemo {
    static <T> int myOp(MyFuncs<T> f, T[] vals, T v) {
        return f.func(vals, v);
    }

    public static void main(String[] args) {
        Integer[] vals = {1, 2, 3, 4, 5};
        String[] strs = {"One", "Two", "Three", "Two"};
        int count;

        count = myOp(MyArrayOps::countMatching, vals, 4);
        System.out.println("Array vals includes " + count + " number 4");

        count = myOp(MyArrayOps::countMatching, strs, "Two");
        System.out.println("Array strs includes " + count + " number Two");
    }
}

class MyClass {
    private int val;

    MyClass(int v) {
        val = v;
    }

    int getVal() {
        return val;
    }
}

class UseMethodRef {
    static int compareMC(MyClass a, MyClass b) {
        return a.getVal() - b.getVal();
    }

    public static void main(String[] args) {
        ArrayList<MyClass> al = new ArrayList<>();

        al.add(new MyClass(1));
        al.add(new MyClass(4));
        al.add(new MyClass(2));
        al.add(new MyClass(9));
        al.add(new MyClass(3));
        al.add(new MyClass(7));

        MyClass maxValObj = Collections.max(al, UseMethodRef::compareMC);

        System.out.println("Max value equals: " + maxValObj.getVal());
    }
}


interface MyFunk {
    MyClasss func(int n);
}

class MyClasss{
    private int val;

    MyClasss(int v){
        val = v;
    }

    MyClasss(){
        val = 0;
    }

    int getVal(){
        return val;
    }
}

class ConstructorRefDemo {
    public static void main(String[] args) {
        MyFunk myClassCons = MyClasss::new;

        MyClasss mc = myClassCons.func(100);

        System.out.println("Value val in the Object mc equals " + mc.getVal());
    }
}







