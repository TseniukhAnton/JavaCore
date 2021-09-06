package com.TseniukhAnton.javacore.chapter13;

import java.applet.Applet;
import java.awt.*;
import java.io.*;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

class BRRead {
    public static void main(String[] args) throws IOException {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter, 'q' - to exit");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');
    }
}

class BRReadLines {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter text lines");
        System.out.println("Enter stop to quit");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("stop"));
    }
}

class TinyEdit {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = new String[100];
        System.out.println("Enter text");
        System.out.println("Enter 'stop' to exit");
        for (int i = 0; i < 100; i++) {
            str[i] = br.readLine();
            if (str[i].equals("stop")) break;
        }
        System.out.println("\nYour file includes: ");
        for (int i = 0; i < 100; i++) {
            if (str[i].equals("stop")) break;
            System.out.println(str[i]);
        }
    }
}

class WriteDemo {
    public static void main(String[] args) {
        int b;
        b = 'A';
        System.out.write(b);
        System.out.write('\n');
    }
}

class PrintWriterDemo {
    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println("This is a String");
        int i = -7;
        pw.println(i);
        double d = 4.5e-7;
        pw.println(d);
    }
}

class ShowFile {
    public static void main(String[] args) {
        int i;
        FileInputStream fin;

        if (args.length != 1) {
            System.out.println("Use: ShowFile Test.txt");
            return;
        }
        try {
            fin = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File can't be opened");
            return;
        }
        try {
            do {
                i = fin.read();
                if (i != 1) System.out.println((char) i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("Error while reading from file");
        }
        try {
            fin.close();
        } catch (IOException e) {
            System.out.println("Error while file closing");
        }
    }
}

class ShowFile2 {
    public static void main(String[] args) {
        int i;
        FileInputStream fin;

        if (args.length != 1) {
            System.out.println("Use: ShowFile Test.txt");
            return;
        }

        try {
            fin = new FileInputStream(args[0]);
            do {
                i = fin.read();
                if (i != -1) System.out.println((char) i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } finally {
            try {
                fin = new FileInputStream(args[0]);
                if (fin != null) fin.close();
            } catch (IOException e) {
                System.out.println("Error while file closing");
            }
        }
    }
}

class CopyFile {
    public static void main(String[] args) {
        int i;
        FileInputStream fin = null;
        FileOutputStream fout = null;
        if (args.length != 2) {
            System.out.println("Use: CopyFile from..to...");
            return;
        }
        try {
            fin = new FileInputStream(args[0]);
            fout = new FileOutputStream(args[0]);
            do {
                i = fin.read();
                if (i != -1) fout.write(i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException e2) {
                System.out.println("Error while file close");
            }
            try {
                if (fout != null) fout.close();
            } catch (IOException e2) {
                System.out.println("Error while file close");
            }
        }
    }
}

class ShowFileTryWithResourses {
    public static void main(String[] args) {
        int i;
        if (args.length != 1) {
            System.out.println("Use: ShowFile file_name");
            return;
        }
        try (FileInputStream fin = new FileInputStream(args[0])) {
            do {
                i = fin.read();
                if (i != -1) System.out.println((char) i);
            } while (i != -1);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error happened " + e);
        }
    }
}

class CopyFileTryWithTwoResourses {
    public static void main(String[] args) {
        int i;
        if (args.length != 2) {
            System.out.println("Use: CopyFile from...to...");
            return;
        }
        try (FileInputStream fin = new FileInputStream(args[0]); FileOutputStream fout = new FileOutputStream(args[0])) {
            // advantages autoclosability of the files in try with resourses
            //also while simple try during finally execution initial exception can be supressed by another one and saved getSupressed();
            //in try without resourses it will not be saved
            do {
                i = fin.read();
                if (i != -1) fout.write(i);
            } while (i != -1);
        } catch (IOException e) {
            System.out.println("IO error happened " + e);
        }
    }
}

class A {
    int i, j;
}

class B {
    int i, j;
}

class C extends A {
    int k;
}

class D extends A {
    int k;
}

class InstanceOf {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();
        if (a instanceof A)
            System.out.println("a instance of A");
        if (b instanceof B)
            System.out.println("b instance of B");
        if (c instanceof C)
            System.out.println("c instance of C");
        if (d instanceof D)
            System.out.println("d instance of D");

        if (a instanceof C)
            System.out.println("a can be casted to C");

        System.out.println();

        A ob;

        ob = d;
        System.out.println("ob now pointed to d");
        if (ob instanceof D)
            System.out.println("ob is now instance of D");
        System.out.println();

        ob = c;
        if (ob instanceof D) System.out.println("ob can be casted to D");
        else
            System.out.println("ob can't be casted to D");

        if (ob instanceof A) System.out.println("ob can be casted to A");
        System.out.println();

        if (a instanceof Object) System.out.println("a can be casted to Object");
        if (b instanceof Object) System.out.println("b can be casted to Object");
        if (c instanceof Object) System.out.println("c can be casted to Object");
        if (d instanceof Object) System.out.println("d can be casted to Object");

    }
}

//оператор i n s t an ce o f - это средство, с помощью которого программа может получить сведения об объекте во время выполнения

class Hypot {
    public static void main(String[] args) {
        double side1, side2;
        double hypot;

        side1 = 3.0;
        side2 = 4.0;

        hypot = sqrt(pow(side1, 2) + pow(side2, 2));
        System.out.println(hypot);

        System.out.println("Having stated side's length " + side1 + " and " + side2 + " hypot equals " + hypot);
    }
}

class MyClass {
    int a;
    int b;

    MyClass(int i, int j) {
        a = i;
        b = j;
    }

    MyClass(int i) {
        this(i, i);
    }

    MyClass(){
        this(0);
    }
}











