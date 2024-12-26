package lesson13;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //1
        List<String> words = List.of("Rock", "Jazz", "Rap", "Jazz", "Blues", "Rock", "Electronic", "Disco", "Rap", "Rock", "Jazz");
        System.out.println(countOccurance(words, "Jazz"));

        //2
        int[] array = {1, 2, 3, 4, 5};
        List<Integer> list = toList(array);
        System.out.println(list);

        //3
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 1, 2, 5, 6, 7, 7, 2, 8, 1, 9);
        List<Integer> uniqueNumbers = findUnique(numbers);
        System.out.println(uniqueNumbers);

        //4
        calcOccurance(words);

    }

    public static int countOccurance(List<String> words, String target){
        int counter = 0;
        for (String word : words){
            if(word.equals(target)){
                counter++;
            }
        }
        return counter;
    }

    public static List<Integer> toList(int[] array){

        List<Integer> list = new ArrayList<>();
        for (Integer arr : array){
            list.add(arr);
        }
        return list;
    }

    public static List<Integer> findUnique(List<Integer> numbers){
        List<Integer> list = new ArrayList<>();

        for (Integer number : numbers) {
            if(!list.contains(number)){
                list.add(number);
            }
        }
        return list;
    }

    public static void calcOccurance(List<String> words){

        List<String> processedWord = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);
            if(processedWord.contains(currentWord)){
                continue;
            }

            int count = 0;
            for (String word : words){
                if(word.equals(currentWord)){
                    count++;
                }
            }
            processedWord.add(currentWord);
            System.out.println(currentWord + ": " + count);
        }
    }

}
