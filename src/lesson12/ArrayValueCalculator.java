package lesson12;

public class ArrayValueCalculator {

    public int doCalc(String[][] value) throws ArraySizeException, ArrayDataException{
            if(value == null){
                throw new ArraySizeException("Массив не может быть null", null);
            }

            if(value.length != 4){
                throw new ArraySizeException("Массив должен быть 4x4", null);
            }

            int sum = 0;
            for (int i = 0; i < value.length; i++) {
                if(value[i] == null){
                    throw new ArrayDataException("Строка массива " + i + " не может быть null", null);
                }
                if(value[i].length != 4){
                    throw new ArraySizeException("Массив должен быть 4x4", null);
                }

                for (int j = 0; j < value.length; j++) {
                    if(value[i][j] == null){
                        throw new ArrayDataException("Значение массива " + i + " в столбце " + j + " не может быть null", null);
                    }
                    try {
                        sum += Integer.parseInt(value[i][j]);
                    } catch (NumberFormatException e) {
                        throw new ArrayDataException(
                                "Элементы массива в строке " + i + " и в столбце " + j + " должны быть числом", e);
                    }
            }
        }
        return sum;
    }
}
