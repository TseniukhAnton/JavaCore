package com.TseniukhAnton.javacore.chapter29;

import java.util.ArrayList;
import java.util.Optional;
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
        if (minVal.isPresent()) System.out.println("Min value is: " + minVal.get());

        myStream = myList.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        if (maxVal.isPresent()) System.out.println("Max value is: " + maxVal.get());

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
        if (productObj.isPresent())
            System.out.println("Multiply object type Optional: " + productObj.get());

        int product = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Multiply int values: " + product);
    }
}













