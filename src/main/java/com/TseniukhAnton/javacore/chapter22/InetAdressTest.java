package com.TseniukhAnton.javacore.chapter22;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;

public class InetAdressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress Address = InetAddress.getLocalHost();
        System.out.println(Address);

        Address = InetAddress.getByName("www.HerbSchildt.com");
        System.out.println(Address);

        InetAddress SW[] = InetAddress.getAllByName("www.nba.com");
        for (int i = 0; i < SW.length; i++) {
            System.out.println(SW[i]);
        }
    }
}

class Whois {
    public static void main(String[] args) throws IOException {
        int c;

        Socket s = new Socket("whois.internic.net", 43);

        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        String str = (args.length == 0 ? "MHProfessional.com" : args[0]) + "\n";
        byte buf[] = str.getBytes();

        out.write(buf);

        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
        s.close();
    }
}

class URLDemo {
    public static void main(String[] args) throws IOException {
        URL hp = new URL("http://www.HerbSchildt.com/Articles");
//        URLConnection urlc = new URLConnection() {
//            @Override
//            public void connect() throws IOException {
//
//            }
//        }
//        urlc = urlc.getURL().openConnection();

        System.out.println("Protocol: " + hp.getProtocol());
        System.out.println("Port: " + hp.getPort());

        System.out.println("Host: " + hp.getHost());
        System.out.println("File: " + hp.getFile());
        System.out.println("Complete Form: " + hp.toExternalForm());
    }
}

class UCDemo {
    public static void main(String[] args) throws IOException {
        int c;
        URL hp = new URL("http://www.internic.net");
        URLConnection hpCon = hp.openConnection();
        long d = hpCon.getDate();
        if (d == 0)
            System.out.println("No information about the date");
        else
            System.out.println("Date: " + new Date(d));

        System.out.println("Type of the content: " + hp.getContent());

        d = hpCon.getExpiration();
        if (d == 0)
            System.out.println("No ixpiration info");
        else
            System.out.println("Expiration date: " + new Date(d));

        d = hpCon.getLastModified();
        if (d == 0)
            System.out.println("Last modified no info");
        else
            System.out.println("Last modified: " + new Date(d));

        long len = hpCon.getContentLengthLong();
        if (len == -1)
            System.out.println("Content length in unavailable");
        else
            System.out.println("Content length: " + len);

        if (len != 0) {
            System.out.println("=== Content ===");
            InputStream input = hpCon.getInputStream();
            while (((c = input.read()) != -1)) {
                System.out.print((char) c);
            }
            input.close();
        } else {
            System.out.println("Content is unavailable");
        }

    }
}



















