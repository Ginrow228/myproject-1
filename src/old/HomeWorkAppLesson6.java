package old;

import java.util.Arrays;

public class HomeWorkAppLesson6 {
    public static void main(String[] args){
            int[] source = {1, 2, 3, 4};
            int[] target = {};

            int[] source2 = {1, 2, 3, 4};
            int[] target2 = {5, 6, 7};

            arrayCopy(source, target);
            arrayCopy(source2, target2);

//            int[] forSort = {5, 7, 1, 2, 9, 6, 4, 8, 3};
//            shakeSort(forSort);
//            System.out.println(Arrays.toString(forSort));

            int[] test = {1, 1, 1, 1, 1};
            shakeSort(test);
            System.out.println(Arrays.toString(test));
    }

    static void arrayCopy(int[] source, int[] target){
        int[] newArr = new int[source.length + target.length];
        int indexSource = 0;
        for (int i = 0; i < newArr.length; i++) {
            if(i < target.length){
                newArr[i] = target[i];
            } else {
                newArr[i] = source[indexSource];
                indexSource++;
            }
        }
        System.out.println(Arrays.toString(newArr));
    }

    static void shakeSort(int[] array){
        int left = 0;
        int right = array.length -1;
        boolean swapped = true;
        while (left < right) {
            swapped = false; // задаем false, т.к еще никаких проходов не было
            for (int j = 0; j < right; j++) {
                if(array[j] > array[j + 1]){
                    var temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
            right--;

            swapped = false;
            for (int j = right; j > 0; j--) {
                if(array[j] < array[j - 1]){
                    var temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    swapped = true;
                }
            }
            left++;
            if(!swapped){
                break;
            }
        }
    }
}

