public class HomeWorkApp {

    public static void main(String[] args){
//         printThreeWords();
//         checkSumSign(-10,-5);
//         printColor(-1);
//         compareNumbers(5,6);
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

    static void printColor(int value){ // метод который выводит цвет, в зависимости от переданного значения
        if(value <= 0){
            System.out.println("Красный"); // если value меньше или ровно 0 вывести слово Красный
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый"); // если value в пределах от 0 (исключительно) 100 (включительно) вывести слово Жёлтый
        } else {
            System.out.println("Зелёный"); // если value больше 100 вывести слово Зелёный
        }
    }

    static void compareNumbers(int a, int b){ // метод который определяет больше || равно или меньше значение
        if (a >= b){
            System.out.println("a >= b"); // если значение a больше или равно b, то вывести на консоль результат
        } else {
            System.out.println("a < b"); // если значение a меньше значения b, то вывести на консоль результат
        }
    }

}
