package com.TseniukhAnton.javacore.chapter10;

import java.util.Random;

public class MainChapter10 {
    public static void main(String[] args) {
        int d, a;
        try {
            d = 0;
            a = 42 / d;
            System.out.println("This will not be printed");
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        }
        System.out.println("After catch operator");
    }
}

class HandleError {
    public static void main(String[] args) {
        int a = 0, b = 0, c = 0;
        Random r = new Random();
        for (int i = 0; i < 32; i++) {
            try {
                b = r.nextInt();
                c = r.nextInt();
                a = 12345 / (b / c);
            } catch (ArithmeticException e) {
                System.out.println("Division by zero");
                a = 0;
            }
            System.out.println("a: " + a);
        }
    }
}

class MultipleCatches {
    public static void main(String[] args) {
        try {
            //int a = args.length;
            int a = 55;
            System.out.println("a = " + a);
            int b = 42 / a;
            int c[] = {1};
            c[42] = 99;
        } catch (ArithmeticException e) {
            System.out.println("Divided by zero: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index error is out of arrays bounds: " + e);
        }
        System.out.println("After try/catch block");
    }
}

class SuperSubCatch {
    public static void main(String[] args) {
        try {
            int a = 0;
            int b = 42 / a;
        } catch (ArithmeticException e) {
            System.out.println("Common class Exception catch");
        } catch (Exception e) {
            System.out.println("Code is unreachable");
        }
    }
}

class NestTry {
    public static void main(String[] args) {
        try {
            //int a = args.length;
            int a = 2;
            int b = 42 / a;
            System.out.println("a = " + a);
            try {
                if (a == 1) {
                    a = a / (a - a);
                }
                if (a == 2) {
                    int c[] = {1};
                    c[42] = 99;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Index is out of bounds " + e);
            }
        } catch (ArithmeticException e) {
            System.out.println("Division by zero: " + e);
        }
    }
}

class MathNestTry {
    static void nestTry(int a) {
        try {
            if (a == 1) a = a / (a - a);
            if (a == 2) {
                int c[] = {1};
                c[42] = 99;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index is out of bounds " + e);
        }
    }

    public static void main(String[] args) {
        try {
            int a = args.length;
            int b = 42 / a;
            System.out.println("a = " + a);
            nestTry(a);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero : " + e);
        }
    }
}

class ThrowDemo {
    static void demoProc() {
        try {
            throw new NullPointerException("Demo");
        } catch (NullPointerException e) {
            System.out.println("Exception caught in the method demoproc().");
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            demoProc();
        } catch (NullPointerException e) {
            System.out.println("Caught again: " + e);
        }
    }
}

class ThrowsDemo {
    static void throwsOne() throws IllegalAccessException {
        System.out.println("In method throwsOne");
        throw new IllegalAccessException("Demo");
    }

    public static void main(String[] args) {
        try {
            throwsOne();
        } catch (IllegalAccessException e) {
            System.out.println("Exception caught " + e);
        }
    }
}

class FinallyDemo {
    static void procA() {
        try {
            System.out.println("In the procA");
            throw new RuntimeException("Demo");
        } finally {
            System.out.println("block Finally from procA()");
        }
    }

    static void procB() {
        try {
            System.out.println("In the procB");
            return;
        } finally {
            System.out.println("block Finally from procB()");
        }
    }

    static void procC() {
        try {
            System.out.println("In the procC");
        } finally {
            System.out.println("block Finally from procC()");
        }
    }

    public static void main(String[] args) {
        try {
            procA();
        } catch (Exception e) {
            System.out.println("Exception has been caught");
        }
        procB();
        procC();
    }
}

class MyException extends Exception {
    private int detail;

    MyException(int a) {
        detail = a;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "detail= " + detail +
                '}';
    }
}

class ExceptionDemo {
    static void compute(int a) throws MyException {
        System.out.println("Method compute is running (" + a + ")");
        if (a > 10)
            throw new MyException(a);
        System.out.println("Normal finishing");
    }

    public static void main(String[] args) {
        try {
            compute(1);
            compute(20);
        } catch (MyException e) {
            System.out.println("Exception was caught: " + e);
        }
    }
}

class ChainExcDemo {
    static void demoproc() {
        NullPointerException e = new NullPointerException("Upper level");
        e.initCause(new ArithmeticException("cause"));
        throw e;
    }

    public static void main(String[] args) {
        try {
            demoproc();
        } catch (NullPointerException e) {
            System.out.println("Exception caught: " + e);
            System.out.println("Initial cause: " + e.getCause());
        }
    }
}

class MultiCatch {
    public static void main(String[] args) {
        int a = 10, b = 0;
        int vals[] = {1, 2, 3};
        try {
            int result = a / b;
            vals[10] = 19;
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e);
        }
        System.out.println("After multiple catch.");
    }
}














