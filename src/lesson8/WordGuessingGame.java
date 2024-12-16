package lesson8;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordGuessingGame {

    String[] words = {
            "apple", "orange", "lemon", "banana", "apricot", "avocado",
            "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
            "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"
    };

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        String randomWord = words[rand.nextInt(words.length)];
        String playerWord;
        char[] hintArr = new char[15];
        Arrays.fill(hintArr, '#');

        while(true){
            System.out.print("Enter word: ");
            playerWord = scanner.nextLine();
            if(playerWord.equals(randomWord)){
                break;
            } else {
                int minLen;
                if(randomWord.length() < playerWord.length()){
                    minLen = randomWord.length();
                } else {
                    minLen = playerWord.length();
                }
                for (int i = 0; i < minLen; i++) {
                    if(randomWord.charAt(i) == playerWord.charAt(i)){
                        hintArr[i] = randomWord.charAt(i);
                    }
                }
                String hint = new String(hintArr);
                System.out.println("Hint to the hidden word: " + hint);
            }
        }
        scanner.close();
    }
}

