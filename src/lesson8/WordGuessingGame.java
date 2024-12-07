package lesson8;

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
        String playerWord; // переменная для слово игрока
        String hint = ""; // переменная для хранения подсказки

        while(true){
            System.out.print("Enter word: ");
            playerWord = scanner.nextLine(); // считывание строки введенного слова

            if(playerWord.equals(randomWord)){ // проверка на сравнимость слова, если слово введенное игроком совпадает с загаданным словом то остановить игру
                break;
            } else { // если слово не совпало, то идёт сравнение на совпадение символов, если n символы совпали то добавляем подсказку
                for (int i = 0; i < randomWord.length(); i++) {
                    if(randomWord.charAt(i) == playerWord.charAt(i)){
                        hint += randomWord.charAt(i); // добавляем в подсказку символы которые есть в загаданном слове
                    } else {
                        while(hint.length() < 15){ // чтобы игрок не узнал сколько символов в слове необходимо добавить 15 знаков #
                            hint += "#"; // если загаданное слово будет состоять из 5 букв то в переменной hint длина будет 5 для этого добавляем # пока hint меньше 15
                        }
                    }
                }
                System.out.println("Hint to the hidden word: " + hint);
            }
        }
        scanner.close();
    }
}
