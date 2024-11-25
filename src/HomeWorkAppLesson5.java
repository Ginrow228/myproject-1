import java.util.Arrays;

public class HomeWorkAppLesson5 {
    public static void main(String[] args){
        int[] arr;
    /** Блок односвязного списка
        arr = initializationList(5);
        arr = addLast(arr, 4);
        System.out.println(Arrays.toString(arr));
        System.out.println(searchIndex(arr,4));
        System.out.println(lengthList(arr)); */
        arr = queue(3);
        addInQueue(arr, 2);
        addInQueue(arr, 4);
        addInQueue(arr, 5);
        System.out.println(Arrays.toString(arr));
        addInQueue(arr, 2);
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

    static int[] queue(int sizeQueue){
        if(sizeQueue <= 0){
            return new int[0];
        }
        return new int[sizeQueue + 1];
    }

    // текущая длинна массива 4, эту длинну мы не можем превышать поэтому длинна должна быть больше
    // текущей длинны
    static void addInQueue(int[] arr, int val){
        if(arr.length <= 0 || arr.length - 1 == arr[0]){ // проверка на выход за пределы массива
            return;  // если длина меньше или равна 0 ИЛИ если длина -1 равна количеству тек. очереди
        }   // выйти и не выполнять метод. ДЛИНА ВСЕГДА БОЛЬШЕ ТЕКУЩЕЙ ОЧЕРЕДИ НА 1
        ++arr[0]; // arr[0] = количество текущей очереди, каждый раз когда метод вызывается, количество текущей очереди
        arr[arr[0]] = val; // увеличивается, arr[arr[0]] = val = добавление значений в очередь
    }
}
