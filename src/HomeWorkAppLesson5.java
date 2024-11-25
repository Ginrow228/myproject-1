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
        /** Блок очереди
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
        System.out.println(Arrays.toString(arr)); */
        arr = init(10);
        System.out.println(Arrays.toString(arr));
        addToHead(arr, 5);
        addToHead(arr, 15);
        addToHead(arr, 25);
        addToHead(arr, 35);
        addToHead(arr, 45);
        addToTail(arr, 55);
        addToTail(arr, 65);
        addToTail(arr, 75);
        addToTail(arr, 85);
        addToTail(arr, 95);
        addToHead(arr, 105);
        System.out.println(Arrays.toString(arr));
        System.out.println(getFromHead(arr));
        System.out.println(Arrays.toString(arr));
        System.out.println(getFromTail(arr));
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

    /*
    static int[] queue(int sizeQueue) {
        if (sizeQueue <= 0) {
            return new int[0];
        }
        return new int[sizeQueue + 1];
    } */

    /*
    static void addInQueue(int[] arr, int val) {
        if (arr.length <= 0 || arr.length - 1 == arr[0]) {
            return;
        }
        ++arr[0];
        arr[arr[0]] = val;
    } */

    /*
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
    }*/

    static int[] init(int size) {
        if (size <= 0) {
            return new int[0];
        }
        int[] arr = new int[size + 2];
        arr[0] = 2;
        arr[1] = arr.length - 1;
        return arr;
    }

    static void addToHead(int[] arr, int value) {
        if (arr.length <= 2 || arr[0] >= arr[1]) {
            return;
        }
        arr[arr[0]] = value;
        ++arr[0];
    }

    static int getFromHead(int[] arr) {
        if (arr[0] == 2) {
            return -1;
        }
        int value = arr[2];
        for (int i = 3; i < arr[0]; i++) {
            arr[i-1] = arr[i];
            if (arr[0] == i+1) {
                arr[i] = 0;
                break;
            }
        }
        --arr[0];
        return value;
    }

    static void addToTail(int[] arr, int value) {
        if (arr.length <= 2 || arr[0] + arr[1] == arr.length +1) {
            return;
        }
        arr[arr[1]] = value;
        --arr[1];
    }

    static int getFromTail(int[] arr) {
        int maxIndex = arr.length - 1;
        if (arr[1] == maxIndex) {
            return -1;
        }
        int value = arr[maxIndex];
        for (int i = maxIndex; i > arr[1]; i--) {
            arr[i] = arr[i-1];
        }
        ++arr[1];
        return value;
    }
}
