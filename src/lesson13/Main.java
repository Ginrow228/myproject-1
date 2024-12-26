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
}
