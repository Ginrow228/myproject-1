package testingframework;

public interface InvokeFunction<T> {
    T invoke() throws ReflectiveOperationException;
}
