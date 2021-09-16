package com.TseniukhAnton.javacore.chapter29;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        System.out.println("Initial list: " + myList);

        Stream<Integer> myStream = myList.stream();
        Optional<Integer> minVal = myStream.min(Integer::compare);
        minVal.ifPresent(integer -> System.out.println("Min value is: " + integer));

        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        maxVal.ifPresent(integer -> System.out.println("Max value is: " + integer));

        Stream<Integer> sortedStream = myList.stream().sorted();
        System.out.print("Sorted data stream: ");
        sortedStream.forEach(n -> System.out.print(n + " "));
        System.out.println();

        Stream<Integer> oddVals = myList.stream().sorted().filter(n -> (n % 2) == 1);

        System.out.print("Odd values: ");
        oddVals.forEach(n -> System.out.print(n + " "));
        System.out.println();

        oddVals = myList.stream().filter(n -> (n % 2) == 1)
                .filter(n -> n > 5);
        System.out.print("Odd values more than 5: ");
        oddVals.forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}


class StreamDemo2 {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);

        Optional<Integer> productObj = myList.stream().reduce((a, b) -> a * b);
        productObj.ifPresent(integer -> System.out.println("Multiply object type Optional: " + integer));

        int product = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Multiply int values: " + product);
    }
}

class StreamDemo3 {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);

        double productOfSqrRoots = myList.parallelStream().reduce(1.0, (a, b) -> a * Math.sqrt(b),
                (a, b) -> a * b);

        System.out.println("Multiply of sqrt values: " + productOfSqrRoots);
    }
}

class StreamDemo4 {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);

        Stream<Double> sqrtRootStrm = myList.stream().map(Math::sqrt);

        double productOfSqrRoots = sqrtRootStrm.reduce(1.0, (a, b) -> a * b);
        System.out.println("Multiplication of sqrt equals: " + productOfSqrRoots);
    }
}

class NamePhoneEmail {
    String name;
    String phonenum;
    String email;

    public NamePhoneEmail(String n, String p, String e) {
        name = n;
        phonenum = p;
        email = e;
    }
}

class NamePhone {
    String name;
    String phonenum;

    NamePhone(String n, String p) {
        name = n;
        phonenum = p;
    }
}

class StreamDemo5 {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        myList.add(new NamePhoneEmail("Larry", "555-5555", "larry@i.ua"));
        myList.add(new NamePhoneEmail("James", "544-5555", "james@i.ua"));
        myList.add(new NamePhoneEmail("Mary", "775-5555", "mary@i.ua"));

        System.out.println("Initial elements from myList: ");
        myList.stream().forEach(a -> {
            System.out.println(a.name + " " + a.phonenum + " " + a.email);
        });
        System.out.println();

        Stream<NamePhone> nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));

        System.out.println("List of names and phone numbers: ");
        nameAndPhone.forEach(a -> {
            System.out.println(a.name + " " + a.phonenum);
        });
    }
}

class StreamDemo6 {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(1.1);
        myList.add(3.6);
        myList.add(9.2);
        myList.add(4.7);
        myList.add(12.1);
        myList.add(5.0);

        System.out.print("Initial values of myList: ");

        myList.stream().forEach(a -> {
            System.out.print(a + " ");
        });
        System.out.println();

        IntStream cStrm = myList.stream().mapToInt(a -> (int) Math.ceil(a));

        System.out.print("Max border values from myList: ");
        cStrm.forEach(a -> {
            System.out.print(a + " ");
        });

    }
}

class StreamDemo7 {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("Larry", "555-5555", "larry@i.ua"));
        myList.add(new NamePhoneEmail("James", "544-5555", "james@i.ua"));
        myList.add(new NamePhoneEmail("Mary", "775-5555", "mary@i.ua"));

        Stream<NamePhone> nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));

        List<NamePhone> npList = nameAndPhone.collect(Collectors.toList());
        System.out.println("Names and phones in type List: ");
        for (NamePhone e : npList)
            System.out.println(e.name + ": " + e.phonenum);

        nameAndPhone = myList.stream().map(a -> new NamePhone(a.name, a.phonenum));

        Set<NamePhone> npSet = nameAndPhone.collect(Collectors.toSet());
        System.out.println("\nNames and phones using Set:");

        for (NamePhone e : npSet)
            System.out.println(e.name + ": " + e.phonenum);
    }
}

class StreamDemo8 {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Betta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Xi");
        myList.add("Omega");

        Stream<String> myStream = myList.stream();
        Iterator<String> itr = myStream.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}

class StreamDemo9 {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Betta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Xi");
        myList.add("Omega");

        Stream<String> myStream = myList.stream();
        Spliterator<String> splitItr = myStream.spliterator();
        while (splitItr.tryAdvance((n) -> System.out.println(n))) ;
    }
}

class StreamDemo10 {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Alpha");
        myList.add("Betta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Xi");
        myList.add("Omega");

        Stream<String> myStream = myList.stream();
        Spliterator<String> splitItr = myStream.spliterator();
        Spliterator<String> splitItr2 = splitItr.trySplit();
        if (splitItr2 != null) {
            System.out.println("Result from spltItr2: ");
            splitItr2.forEachRemaining(n -> System.out.println(n));
        }
        System.out.println("\nResult from splitItr: ");
        splitItr.forEachRemaining(n -> System.out.println(n));
    }
}















