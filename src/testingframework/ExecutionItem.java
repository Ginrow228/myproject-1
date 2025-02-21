package testingframework;

import java.lang.reflect.Method;

/**
 * @param <T> - describing a type of actual and expected result
 */
public record ExecutionItem<T>(
        Class<?> testingClass,
        Method testingMethod,
        T expectedResult,
        T actualResult,
        boolean success
) {
}
