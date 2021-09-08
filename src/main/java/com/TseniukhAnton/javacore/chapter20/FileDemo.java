package com.TseniukhAnton.javacore.chapter20;

import java.io.File;

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
