package lesson13;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //1
        List<String> words = List.of("Rock", "Jazz", "Rap", "Jazz", "Blues", "Rock", "Electronic", "Disco", "Rap", "Rock", "Jazz");
        System.out.println(countOccurance(words, "Jazz"));



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
}
