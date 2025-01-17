package lesson17;

/*
Необходимо реализовать метод, который принимает в качества параметра массив (массив может быть любого ссылочного типа) и меняет каждую пару элементов местами между собойd.
Метод работает с массивом по ссылке, а значит ничего не возвращает.
Необходимо реализовать метод, который принимает в качества параметра массив (массив может быть любого ссылочного типа)
и преобразовывает его в List (должен обладать таким же типом данных как и переданный в качестве параметра массив).
Результатом выполнения операция является экземпляр List возвращаемый после вызова метода.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "d"};
        System.out.println(Arrays.toString(array));

        swapElements(array);
        System.out.println(Arrays.toString(array));
        System.out.println("=====");
        System.out.println(toList(array));
    }

    public static <T> void swapElements(T[] array){
     for (int i = 0; i < array.length; i += 2){
         T temp = array[i];
         array[i] = array[i + 1];
         array[i + 1] = temp;
     }
    }

    public static <T> List<T> toList(T[] array){
        List<T> list = new ArrayList<>(Arrays.asList(array));
        return list;
    }
}
