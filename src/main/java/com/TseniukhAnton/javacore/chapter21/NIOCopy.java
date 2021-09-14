package com.TseniukhAnton.javacore.chapter21;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NIOCopy {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Apply: from and to copy");
            return;
        }
        try {
            Path source = Paths.get(args[0]);
            Path target = Paths.get(args[1]);

            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (InvalidPathException e) {
            System.out.println("Wrong path Error " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }
}

class ShowFile {
    public static void main(String[] args) {
        int i;
        if (args.length != 1) {
            System.out.println("Application: ShowFile file_name");
            return;
        }

        try (InputStream fin = Files.newInputStream(Paths.get(args[0]))) {
            do {
                i = fin.read();
                if (i != -1) System.out.println((char) i);
            } while (i != -1);
        } catch (InvalidPathException e) {
            System.out.println("Wrong path Error " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }
}

class NIOStreamWrite {
    public static void main(String[] args) {
        try (OutputStream fout = new BufferedOutputStream(Files.newOutputStream(Paths.get("test.txt")))) {
            for (int i = 0; i < 26; i++) {
                fout.write((byte) ('A' + i));
            }
        } catch (InvalidPathException e) {
            System.out.println("Wrong path Error " + e);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }
}


class PathDemo {
    public static void main(String[] args) {
        Path filepath = Paths.get("examples\\test.txt");

        System.out.println("File name: " + filepath.getName(1));
        System.out.println("File path: " + filepath);
        System.out.println("Absolute File path: " + filepath.toAbsolutePath());
        System.out.println("Parent directory: " + filepath.getParent());

        if (Files.exists(filepath))
            System.out.println("File exists");
        else
            System.out.println("File doesn't exist");

        try {
            if (Files.isHidden(filepath))
                System.out.println("File is hidden");
            else
                System.out.println("File is not hidden");
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }

        Files.isWritable(filepath);
        System.out.println("File is writable");

        Files.isReadable(filepath);
        System.out.println("File is readable");

        try {
            BasicFileAttributes attribs = Files.readAttributes(filepath, BasicFileAttributes.class);

            if (attribs.isDirectory())
                System.out.println("This is a directory");
            else
                System.out.println("This is not a directory");

            if (attribs.isRegularFile())
                System.out.println("This is a regular file");
            else
                System.out.println("This is not a regular file");

            if (attribs.isSymbolicLink())
                System.out.println("This is a symbolic link");
            else
                System.out.println("This is nota symbolic link");

            System.out.println("Last modified at: " + attribs.lastModifiedTime());
            System.out.println("File size: " + attribs.size() + " bytes");
        }catch (IOException e){
            System.out.println("Attributes reading Error: " + e);
        }
    }
}















