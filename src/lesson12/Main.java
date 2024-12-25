package lesson12;

public class Main {
    public static void main(String[] args){

        ArrayValueCalculator calculator = new ArrayValueCalculator();

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = calculator.doCalc(array);
            System.out.println(result);
        } catch (ArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (ArrayDataException e) {
            System.out.println(e.getMessage());
        }

        String[][] array2 = {
                {"1", "2", "3", "4"},
                {null, "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = calculator.doCalc(array2);
            System.out.println(result);
        } catch (ArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (ArrayDataException e) {
            System.out.println(e.getMessage());
        }

        String[][] array3 = {
                {"1", "2", "3", "4"},
                {"5", "abc", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = calculator.doCalc(array3);
            System.out.println(result);
        } catch (ArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (ArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }
}
