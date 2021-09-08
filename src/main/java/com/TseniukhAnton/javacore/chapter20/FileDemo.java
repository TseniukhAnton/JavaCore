package com.TseniukhAnton.javacore.chapter20;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileDemo {
    static void p(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        File f1 = new File("D:\\JavaLearning\\JavaCore\\src\\main\\java\\com\\TseniukhAnton\\javacore\\chapter20\\COPYRIGHT.txt");
        p("File name: " + f1.getName());
        p("File path: " + f1.getPath());
        p("File absolute path: " + f1.getAbsolutePath());
        p("File parent directory: " + f1.getParent());
        p(f1.exists() ? "exists" : "don't exists");
        p(f1.canWrite() ? "available for recording" : "not available for recording");
        p(f1.canRead() ? "available for reading" : "not available for reading");
        p(f1.isDirectory() ? "is directory" : "not a directory");
        p(f1.isFile() ? "is file" : "not a file");
        p(f1.isAbsolute() ? "is absolute" : "not absolute");
        p("last modified " + f1.lastModified());
        p("size " + f1.length() + " byte");
    }
}

class DirList {
    public static void main(String[] args) {
        String dirname = "D:\\JavaLearning\\JavaCore";
        File f1 = new File(dirname);
        if (f1.isDirectory()) {
            System.out.println("Directory " + dirname);
            String s[] = f1.list();

            for (int i = 0; i < s.length; i++) {
                File f = new File(dirname + "/" + s[i]);
                if (f.isDirectory()) {
                    System.out.println(s[i] + " is directory");
                } else {
                    System.out.println(s[i] + " is file");
                }
            }
        } else {
            System.out.println(dirname + " is not a directory");
        }
    }
}

class FileInputStreamDemo {
    public static void main(String[] args) {
        int size;
        try (FileInputStream f = new FileInputStream("D:\\JavaLearning\\JavaCore\\src\\main\\java\\com\\TseniukhAnton\\javacore\\chapter20\\FileDemo.java")) {
            System.out.println("Bytes available: " + (size = f.available()));

            int n = size / 40;
            System.out.println("First " + n + " bytes" + " read from file in a row using method read()");
            for (int i = 0; i < n; i++) {
                System.out.println((char) f.read());
            }
            System.out.println("\nStill available: " + f.available());
            System.out.println("Reading next " + n + " bytes using read(b[])");

            byte b[] = new byte[n];
            if (f.read(b) != n) {
                System.out.println("Impossible to read " + n + " bytes");
            }

            System.out.println(new String(b, 0, n));
            System.out.println("\nStill available: " + (size = f.available()));

            System.out.println("Skip all remained bytes usin skip()");
            f.skip(size);
            System.out.println("Still available: " + f.available());

            System.out.println("Reading " + n / 2 + " bytes from array's end");

            if (f.read(b, n / 2, n / 2) != n / 2) {
                System.err.println("Impossible to read " + n / 2 + " bytes");
            }
            System.out.println(new String(b, 0, b.length));
            System.out.println("\nStill available: " + f.available());

        } catch (IOException e) {
            System.out.println("IOExceptio: " + e);
        }
    }
}