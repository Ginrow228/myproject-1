public class HomeWorkAppLesson4 {
    public static void main(String[] args){
//        int[] arr = {0, 1, 0, 1, 0};
//        replace1With0(arr);
//        fillArray(100);

    }
    static void replace1With0(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] > 0 ? 0 : 1;
            System.out.print(arr[i] + " ");
        }
    }

    static void fillArray(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
            System.out.print(arr[i] + " ");
        }
    }
}
