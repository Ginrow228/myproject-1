package lesson18;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        getTop10UniqueSortedNumbers(); // 1

    }

    static void getTop10UniqueSortedNumbers(){
    Random random = new Random();
    random.ints(100, 1, 1000)
            .distinct()
            .sorted()
            .limit(10)
            .boxed()
            .sorted((o1, o2) -> o2 - o1)
            .forEach(System.out::println);
    }
}
