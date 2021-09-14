package com.TseniukhAnton.javacore.chapter21;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

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
        }catch (InvalidPathException e){
            System.out.println("Wrong path Error " + e);
        }catch (IOException e){
            System.out.println("IO Error " + e);
        }
    }
}




















