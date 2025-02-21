package testingframework;

public class AssertException extends RuntimeException {
    private final AssertResult<?> result;

    public AssertException(AssertResult<?> result) {
        super("Method executed");
        this.result = result;
    }

    public AssertResult<?> getResult() {
        return result;
    }
}
