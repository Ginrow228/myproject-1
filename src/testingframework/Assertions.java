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

    public static <T> void equalRecursively(T expected, T actual) {
        if (expected == null && actual == null) {
            throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), true));
        }
        if (expected == null || actual == null) {
            throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), false));
        }

        if (expected == actual) {
            throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), true));
        }

        if (!expected.getClass().equals(actual.getClass())) {
            throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), false));
        }
        compareFields(expected, actual);
        throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), true));

    }

    private static <T> void compareFields(T expected, T actual) {
        Field[] fields = expected.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                var expectedField = field.get(expected);
                var actualField = field.get(actual);
                if (expectedField == null && actualField == null) {
                    continue;
                }
                if (expectedField == null || actualField == null) {
                    throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), false));
                }
                if (expected.getClass().isPrimitive() ||
                        expectedField instanceof String ||
                        expectedField instanceof Number ||
                        expectedField instanceof Boolean) {
                    if(!expectedField.equals(actualField)) {
                        throw new AssertException(new AssertResult<>(formatObject(expected), formatObject(actual), false));
                    }
                } else {
                    equalRecursively(expectedField, actualField);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static <T> String formatObject(T object) {
        if (object == null) {
            return "null";
        }
        StringBuilder result = new StringBuilder("{");
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                result.append(field.getName())
                        .append(": ")
                        .append(field.get(object));
                if (i < fields.length - 1) {
                    result.append(", ");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        result.append("}");
        return result.toString();
    }
}
