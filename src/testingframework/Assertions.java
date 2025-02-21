package testingframework;

public final class Assertions {
    private Assertions() {
    }

    public static <T> void equal(T expected, T actual) {
        var result = expected.equals(actual);
        throw new AssertException(new AssertResult<>(expected, actual, result));
    }
}
