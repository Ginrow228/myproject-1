package testingframework;

/**
 * @param <T> - describing a type of actual and expected result
 */
public record AssertResult<T>(T expectedResult, T actualResult, boolean success) {
}
