package lesson26;

public final class ArrayUtils {
    public static int[] ElementsAfterLastFour(int[] array) {
        if (array == null) {
            throw new RuntimeException("Массив не может быть null");
        }

        int lastFour = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4)  {
                lastFour = i;
            }
        }
        if(lastFour == -1) {
            throw new RuntimeException("В массиве должна быть хотя бы одна четверка");
        }

        int[] result = new int[array.length - lastFour - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[lastFour + 1 + i];
        }

        return result;
    }

    public static boolean containsOnlyOneAndFour(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        boolean one = false;
        boolean four = false;
        for (int value : array) {
            if (value == 1) {
                one = true;
            } else if (value == 4) {
                four = true;
            } else {
                return false;
            }
        }
        return one && four;
    }
}
