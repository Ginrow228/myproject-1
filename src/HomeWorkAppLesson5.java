import java.util.Arrays;

public class HomeWorkAppLesson5 {
    public static void main(String[] args){
        int[] arr;
        arr = initializationList(5);
        arr = addLast(arr, 4);
        System.out.println(Arrays.toString(arr));
        System.out.println(searchIndex(arr,4));
        System.out.println(lengthList(arr));
    }

    static int[] initializationList(int sizeList){
        return new int[sizeList];
    }

    static int[] addLast(int[] arr, int value){
        arr[arr.length - 1] = value;
        return arr;
    }

    static int searchIndex(int[] arr, int index){
        if(index < 0 || index > arr.length){
            System.out.println("Переданное значение меньше нуля или вышло за пределы массива");
            return -1;
        }
        return arr[index];
    }

    static int lengthList(int[] arr){
        return arr.length;
    }
}
