package lesson18;

import lesson18.task6.Boyscout;
import lesson18.task6.Camp;
import lesson18.task6.Team;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

//        getTop10UniqueSortedNumbers(); // 1
//
//        //2
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        Predicate<Integer> isEven = number -> number % 2 == 0;
//        Collection<Integer> evenNumbers = filterCollection(numbers, isEven);
//        evenNumbers.forEach(System.out::println);
//
//        //3
//        List<String> words = Arrays.asList("hello", ",", "world", "!", "good", "luck");
//        Predicate<String> contains = str -> str.contains("o");
//        String res = filterAndCombineStrings(words, contains);
//        System.out.println(res);
//
//        //4
//        Random random = new Random();
//        List<Integer> randomNumbers = random.ints(10, 1, 1000)
//                .boxed()
//                .toList();
//        System.out.println(randomNumbers);
//        System.out.println("ASC : " + sortUniqueNumbers(randomNumbers, Sort.ASC));
//        System.out.println("DESC : " + sortUniqueNumbers(randomNumbers, Sort.DESC));
//
//        //5
//        int number = 5;
//        System.out.println(calculateFactorial(number));

        //6
        Boyscout boy1 = new Boyscout("Ivan", 15, Team.RED);
        Boyscout boy2 = new Boyscout("Sasha", 12, Team.BLUE);
        Boyscout boy3 = new Boyscout("Maks", 13, Team.RED);
        Boyscout boy4 = new Boyscout("Misha", 14, Team.BLUE);
        Boyscout boy5 = new Boyscout("David", 16, Team.GREEN);
        Boyscout boy6 = new Boyscout("Ruslan", 17, Team.GREEN);

        Camp camp = new Camp(Arrays.asList(boy1, boy2, boy3, boy4, boy5, boy6));
        Map<Team, List<Boyscout>> grouped = camp.split();
        grouped.forEach((team, boyscouts) -> System.out.println(team + ": " + boyscouts));

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

    static String filterAndCombineStrings(Collection<String> collection, Predicate<String> predicate){
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.joining("|"));
    }

    enum Sort {
        ASC, DESC
    }

    static List<Integer> sortUniqueNumbers(Collection<Integer> collection, Sort sortOrder){
        return collection.stream()
                .distinct()
                .sorted((o1, o2) -> sortOrder == Sort.ASC ? Integer.compare(o1, o2) : Integer.compare(o2, o1))
                .collect(Collectors.toList());
    }

    static int calculateFactorial(int value){
        return IntStream.rangeClosed(1, value)
                .reduce(1, (a, b) -> a * b);
    }
}
