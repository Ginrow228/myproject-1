public class HomeWorkApp {

    public static void main(String[] args){
         //printThreeWords();
         //checkSumSign(-10,-5);
    }

    static void printThreeWords(){// метод который выводит 3 слова в табличном виде
        System.out.println(
                """
                Orange
                Banana
                Apple
                """
        );
    }

    static void checkSumSign(int a, int b){ // метод который считает положительна или отрицательна сумма
        int c = a + b;
        if (c >= 0){
            System.out.println("Сумма положительна!"); // если сумма чисел больше или равна 0 вывести Сумма положительна
        } else {
            System.out.println("Сумма отрицательна"); // в противном случае вывести Сумма отрицательна
        }
    }

}
