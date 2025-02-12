package lesson21;

public class ValueCalculator {

    private final float[] values;
    private int size;

    public ValueCalculator(float[] values, int size) {
        this.values = values;
        this.size = size;
    }

    public ValueCalculator(float[] values) {
        this(values, values.length);
    }

    public float[] getValues() {
        return values;
    }

    public int getSize() {
        return size;
    }

}
