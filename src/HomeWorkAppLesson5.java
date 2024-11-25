import java.util.Arrays;

public class HomeWorkAppLesson5 {
    public static void main(String[] args) {
        int[] arr;
        /** Блок односвязного списка
         arr = initializationList(5);
         arr = addLast(arr, 4);
         System.out.println(Arrays.toString(arr));
         System.out.println(searchIndex(arr,4));
         System.out.println(lengthList(arr)); */
        arr = queue(7);
        addInQueue(arr, 2);
        addInQueue(arr, 4);
        addInQueue(arr, 5);
        addInQueue(arr, 1);
        addInQueue(arr, 6);
        addInQueue(arr, 9);
        addInQueue(arr, 10);
        addInQueue(arr, 20);
        System.out.println(Arrays.toString(arr));
        getInQueue(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    static int[] initializationList(int sizeList){
        return new int[sizeList];
    } */

    /*
    static int[] addLast(int[] arr, int value){
        arr[arr.length - 1] = value;
        return arr;
    } */

    /*
    static int searchIndex(int[] arr, int index){
        if(index < 0 || index > arr.length){
            System.out.println("Переданное значение меньше нуля или вышло за пределы массива");
            return -1;
        }
        return arr[index];
    } */

    /*
    static int lengthList(int[] arr){
        return arr.length;
    } */


    static int[] queue(int sizeQueue) {
        if (sizeQueue <= 0) {
            return new int[0];
        }
        return new int[sizeQueue + 1];
    }

    static void addInQueue(int[] arr, int val) {
        if (arr.length <= 0 || arr.length - 1 == arr[0]) { // проверка на выход за пределы массива
            return;
        }
        ++arr[0];
        arr[arr[0]] = val;
    }

    static int getInQueue(int[] arr) {
        if (arr.length == 0 || arr[0] == 0) {
            return -1;
        }
        int firstValue = arr[1];
        for (int i = 2; i < arr.length; i++) {
            arr[i - 1] = arr[i];
            if (arr[0] == i) {
                arr[i] = 0;
                break;
            }
        }
        --arr[0];
        return firstValue;
    }
}
