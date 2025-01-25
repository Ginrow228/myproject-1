package lesson18;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        getTop10UniqueSortedNumbers(); // 1

        //2
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Collection<Integer> evenNumbers = filterCollection(numbers, isEven);
        evenNumbers.forEach(System.out::println);

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

    static <T>Collection<T> filterCollection(Collection<T> collection, Predicate<T> predicate){
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
