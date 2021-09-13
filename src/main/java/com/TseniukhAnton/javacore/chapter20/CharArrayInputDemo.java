package com.TseniukhAnton.javacore.chapter20;

import java.io.*;

public class CharArrayInputDemo {
    public static void main(String[] args) {
        String tmp = "abcdefghijklmnopqrstuvwxyz";
        int length = tmp.length();
        char[] c = new char[length];

        tmp.getChars(0, length, c, 0);
        int i;

        try (CharArrayReader input1 = new CharArrayReader(c)) {
            System.out.println("input1:");
            while ((i = input1.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
        try (CharArrayReader input2 = new CharArrayReader(c, 0, 5)) {
            System.out.println("input2:");
            while ((i = input2.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
}


class CharArrayWriterDemo {
    public static void main(String[] args) throws IOException {
        CharArrayWriter f = new CharArrayWriter();
        String s = " This line will be shown as array";
        char[] buf = new char[s.length()];

        s.getChars(0, s.length(), buf, 0);
        try {
            f.write(buf);
        } catch (IOException e) {
            System.out.println("Error while recording to buffer");
            return;
        }

        System.out.println("Buffer as a line of symbols");
        System.out.println(f.toString());
        System.out.println("Into the array");

        char[] c = f.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }


        System.out.println("\nInto the outputstream type FileWriter()");

        try (FileWriter f2 = new FileWriter("text.txt")) {
            f.writeTo(f2);
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }

        System.out.println("Back to the initial condition");
        f.reset();

        for (int i = 0; i < 3; i++) f.write('X');

        System.out.println(f.toString());

    }
}

class BufferedReaderDemo {
    public static void main(String[] args) {
        String s = "This is a sign og private property &copy; " +
                ", and &copy - is not.\n";
        char buf[] = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);

        CharArrayReader in = new CharArrayReader(buf);
        int c;
        boolean marked = false;

        try (BufferedReader f = new BufferedReader(in)) {
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
                            System.out.print("c");
                        } else {
                            System.out.print((char) c);
                            break;
                        }
                    case ' ':
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else
                            System.out.print((char) c);
                        break;
                    default:
                        if (!marked) {
                            System.out.print((char) c);
                            break;
                        }
                }
            }
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }
    }
}

class PushbackReaderDemo {
    public static void main(String[] args) {
        String s = "if (a == 4) a = 0;\n";
        char buf[] = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        CharArrayReader in = new CharArrayReader(buf);

        int c;
        try (PushbackReader f = new PushbackReader(in)) {
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

class ConsoleDemo {
    public static void main(String[] args) {
        String str;
        Console con;
        con = System.console();

        if (con == null ) return;

        str = con.readLine("Enter a string: ");
        con.printf("string entered: %s\n", str);
    }
}












