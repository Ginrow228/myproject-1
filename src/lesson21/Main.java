package lesson21;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        float[] val = new float[1_000_000];
        Arrays.fill(val, 1.0f);

        ValueCalculator valCalc = new ValueCalculator(val, val.length);
        valCalc.doCalc();
    }
}
