package lesson17;

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
