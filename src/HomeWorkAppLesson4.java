public class HomeWorkAppLesson4 {
    public static void main(String[] args){
//        int[] arr = {0, 1, 0, 1, 0};
//        replace1With0(arr);

    }
    static void replace1With0(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] > 0 ? 0 : 1;
            System.out.print(arr[i] + " ");
        }
    }
}
