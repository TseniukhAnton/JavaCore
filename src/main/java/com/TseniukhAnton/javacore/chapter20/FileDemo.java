package com.TseniukhAnton.javacore.chapter20;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

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

class FileOutputStreamDemo {
    public static void main(String[] args) {
        String source = "Now it's time for all good men\n" +
                " to come to the aid of their country\n" +
                " and pay their due taxes.";
        byte[] buf = source.getBytes();

        try (FileOutputStream f0 = new FileOutputStream("file1.txt");
             FileOutputStream f1 = new FileOutputStream("file2.txt");
             FileOutputStream f2 = new FileOutputStream("file3.txt")) {

            for (int i = 0; i < buf.length; i += 2) f0.write(buf[i]);

            f1.write(buf);
            f2.write(buf, buf.length - buf.length / 4, buf.length / 4);

        } catch (IOException e) {
            System.out.println("IO exception happened");
//        } finally {
//            try {
//                if (f0 != null) f0.close();
//            } catch (IOException e) {
//                System.out.println("Error while closing file1.txt");
//            }
//            try {
//                if (f1 != null) {
//                    f1.close();
//                }
//            } catch (IOException e) {
//                System.out.println("Error while closing file2.txt");
//            }
//            try {
//                if (f2 != null) f2.close();
//            }catch (IOException e){
//                System.out.println("Error while closing file3.txt");
        }
    }
}

class ByteArrayInputStreamReset {
    public static void main(String[] args) {
        String tmp = "abc";
        byte[] b = tmp.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        for (int i = 0; i < 2; i++) {
            int c;
            while ((c = in.read()) != -1) {
                if (i == 0) {
                    System.out.print((char) c);
                } else {
                    System.out.print(Character.toUpperCase((char) c));
                }
            }
            System.out.println();
            in.reset();
        }
    }
}

class ByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        ByteArrayOutputStream f = new ByteArrayOutputStream();
        String s = "This data should be added into array";
        byte[] buf = s.getBytes();

        try {
            f.write(buf);
        } catch (IOException e) {
            System.out.println("Error while recording into buffer");
            //return;
        }
        System.out.println("Buffer like a string of symbols");
        System.out.println(f.toString());
        System.out.println("Into array");

        byte[] b = f.toByteArray();
        for (int i = 0; i < b.length; i++) System.out.print((char) b[i]);

        System.out.println("\nInto stream type OutputStream");

        try (FileOutputStream f2 = new FileOutputStream("text.txt")) {
            f.writeTo(f2);
        } catch (IOException e) {
            System.out.println("IO exception: " + e);
        }

        System.out.println("Back to initial condition");
        f.reset();

        for (int i = 0; i < 3; i++) {
            f.write('X');
        }
        System.out.println(f.toString());
    }
}


class BufferedInputStreamDemo {
    public static void main(String[] args) {
        String s = "This is the sign of property &copy;" +
                ", and &copy - is not.\n";
        byte[] buf = s.getBytes();

        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int c;
        boolean marked = false;
        try (BufferedInputStream f = new BufferedInputStream(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '&':
                        if (!marked) {
                            f.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked) {
                            marked = false;
                            System.out.print("(c)");
                        }
                        break;
                    case ' ':
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else {
                            System.out.print((char) c);
                        }
                        break;
                    default:
                        if (!marked)
                            System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.print("IO error: " + e);
        }
    }
}

class PushbackIntoStreamDemo {
    public static void main(String[] args) {
        String s = "if (a == 4) a = 0;\n";
        byte[] buf = s.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int c;

        try (PushbackInputStream f = new PushbackInputStream(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '=':
                        if ((c = f.read()) == '=')
                            System.out.print(".eq.");
                        else {
                            System.out.print("<-");
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
}

class InputStreamEnumerator implements Enumeration<FileInputStream> {
    private Enumeration<String> files;

    public InputStreamEnumerator(Vector<String> files) {
        this.files = files.elements();
    }

    @Override
    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    @Override
    public FileInputStream nextElement() {
        try {
            return new FileInputStream(files.nextElement().toString());
        } catch (IOException e) {
            return null;
        }
    }
}

class SequenceInputStreamDemo {
    public static void main(String[] args) {
        int c;
        Vector<String> files = new Vector<String>();
        files.add("file1.txt");
        files.add("file2.txt");
        files.add("file3.txt");

        InputStreamEnumerator ise = new InputStreamEnumerator(files);
        InputStream input = new SequenceInputStream(ise);

        try {
            while ((c = input.read()) != -1)
                System.out.print((char) c);
        } catch (NullPointerException | IOException ex) {
            System.out.println("IO error: " + ex);
        }
    }
}

class PrintfDemo {
    public static void main(String[] args) {
        System.out.println("Below listed some " +
                "values  in different formats.\n");

        System.out.printf("Different numeric values: ");
        System.out.printf("%d %(d %+d %05d\n", 3, -3, 3, 3);

        System.out.println();
        System.out.printf("Float format " +
                " by default : %f\n", 1234567.123);
        System.out.printf("Float format" +
                " divided by commas : %,f\n", 1234567.123);
        System.out.printf("Float negative format " +
                " : %,f\n", -1234567.123);
        System.out.printf("Another negative format " +
                " : %,(f\n", -1234567.123);

        System.out.println();
        System.out.printf("Alignment negative and positive values:\n");
        System.out.printf("'% ,.2f\n% ,.2f\n',");
    }
}

class DataIODemo {
    public static void main(String[] args) {
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("Test.dat"))) {
            dout.writeDouble(98.6);
            dout.writeInt(1000);
            dout.writeBoolean(true);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open output file");
            return;
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }

        try (DataInputStream din = new DataInputStream(new FileInputStream("Test.dat"))) {
            double d = din.readDouble();
            int i = din.readInt();
            boolean b = din.readBoolean();

            System.out.println("Got values: " +
                    d + " " + i + " " + b);
        } catch (FileNotFoundException e) {
            System.out.println("Can't open input file ");
            return;
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
}

class FileReaderDemo {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("D:\\JavaLearning\\JavaCore\\src\\main\\java\\com\\TseniukhAnton\\javacore\\chapter20\\FileDemo.java")) {
            int c;
            while ((c = fr.read()) != -1) System.out.print((char) c);
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
}


class FileWriterDemo {
    public static void main(String[] args) {
        String source = "Now is the time for all good men\n" +
                " to come to the aid of their country\n" +
                " and pay their due taxes ";

        char[] buffer = new char[source.length()];
        source.getChars(0, source.length(), buffer, 0);

        try (FileWriter f0 = new FileWriter("file0.txt");
             FileWriter f1 = new FileWriter("file1.txt");
             FileWriter f2 = new FileWriter("file2.txt");) {
            for (int i = 0; i < buffer.length; i += 2) {
                f0.write(buffer[i]);
            }
            f1.write(buffer);
            f2.write(buffer, buffer.length - buffer.length/4, buffer.length/4);
        }catch (IOException e){
            System.out.println("IO error happened");
        }
    }
}

