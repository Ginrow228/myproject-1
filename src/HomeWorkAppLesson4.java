import java.util.Arrays;

public class HomeWorkAppLesson4 {
    public static void main(String[] args) {
//        int[] arr = {0, 1, 0, 1, 0};
//        replace1With0(arr);
//        fillArray(250);
//        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
//        arr = multiplyArray(arr);
//        System.out.println(Arrays.toString(arr));
//        swapArray(7, 5);
/*        int[][] arr = {
                  {0, 0, 0, 0, 0},
                  {0, 0, 0, 0, 0},
                  {0, 0, 0, 0, 0},
                  {0, 0, 0, 0, 0},
                  {0, 0, 0, 0, 0}
          };
          matrixDiagonal(arr); */
//        int[] arr = {83, 33, 6, 65, 97, 2, 1, 0, -5, -7};
//        findMinMax(arr);
//        int[] arr = {1, 1, 1, 2, 1, 5};
//        System.out.println(checkSum(arr));

    }

    static void replace1With0(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] > 0 ? 0 : 1;
            System.out.print(arr[i] + " ");
        }
    }

    static void fillArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
            System.out.print(arr[i] + " ");
        }
    }

    static int[] multiplyArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        return arr;
    }

    static int[] swapArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    static void matrixDiagonal(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j || i + j == arr[i].length - 1) {
                    arr[i][j] = 1;
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void findMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Minimal: " + min);
        System.out.println("Maximum: " + max);
    }

    static boolean checkSum(int[] arr) {
        if (arr.length < 2) {
            return false;
        }
        int totalSum = 0;
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            leftSum += arr[i];
            int rightSum = totalSum - leftSum;
            if (leftSum == rightSum) {
                return true;
            }
        }
        return false;
    }

}
