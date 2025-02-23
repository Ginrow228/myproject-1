package testingframework;

import java.lang.reflect.Field;
import java.util.Arrays;

public final class Assertions {
    private Assertions() {
    }

    public static <T> void equal(T expected, T actual) {
        var result = expected.equals(actual);
        throw new AssertException(new AssertResult<>(expected, actual, result));
    }

    public static <T> void contains(T[] current, T[] toContain) {
        boolean found = false;
        for (int i = 0; i <= current.length - toContain.length; i++) {
            boolean match = true;
            for (int j = 0; j < toContain.length; j++) {
                if (!current[i + j].equals(toContain[j])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                found = true;
                break;
            }
        }
        throw new AssertException(new AssertResult<>(
                    Arrays.toString(toContain),
                    Arrays.toString(current),
                    found));
    }
}
