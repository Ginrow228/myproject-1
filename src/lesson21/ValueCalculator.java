package lesson21;

import java.util.Arrays;

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

    public void doCalc() {
        long startTime = System.currentTimeMillis();
        Arrays.fill(values, 1.0f);

        int middle = values.length / 2;

        float[] v1 = new float[middle];
        float[] v2 = new float[middle];
        System.arraycopy(values, 0, v1, 0, middle);
        System.arraycopy(values, middle, v2, 0, middle);

        Thread thread1 = new Thread(() -> run(v1));
        Thread thread2 = new Thread(() -> run(v2));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(v1, 0, values, 0, middle);
        System.arraycopy(v2, 0, values, middle, middle);

        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Затрачено " + endTime + " миллисекунд");
    }

    private void run(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
