package com.TseniukhAnton.javacore.chapter15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleLambda {
    public static void main(String[] args) {
        Operation add = (x, y) -> x + y;
        Operation sub = (x, y) -> x - y;
        Operation div = (x, y) -> x / y;
        Operation mul = (x, y) -> x * y;

        int zadd = add.calculate(20, 10);
        int zsub = sub.calculate(20, 10);
        int zdiv = div.calculate(20, 10);
        int zmul = mul.calculate(20, 10);
        System.out.println(zadd);
        System.out.println(zsub);
        System.out.println(zdiv);
        System.out.println(zmul);
    }
}

@FunctionalInterface
interface Operation {
    int calculate(int x, int y);

    default int simple(int x, int y) {
        return x;
    }
}

class StreamDemo {
    public static void main(String[] args) {
        List<Integer> collect = Arrays.asList(10, 20, 30, 30, 40, 20, 50)
                .stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        Stream<Integer> stream = Stream.of(10, 20, 30);

        String[] str = {"r","t","g"};

        // conveernie
        // map, filter, distinct, parallel, sequential, flatMap, skip, limit

        // terminalnie
        // forEach, collect, min/max, sum, count, matcher, findFirst, findAny, iterator,

    }
}








