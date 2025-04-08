package testingframework;

import testingframework.printers.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

public class ExtendedTestAutomationRunner implements Runner {

    private final List<Class<?>> testingClasses;
    private final Set<Printer> printers;

    public ExtendedTestAutomationRunner(List<Class<?>> testingClasses, Set<Printer> printers) {
        this.testingClasses = testingClasses;
        this.printers = printers;
    }


    @Override
    public void run() {
        // Execution
        var executions = new ArrayList<Execution>();

        for (Class<?> testingClass : testingClasses) {
            var beforeAllMethods = new ArrayList<Method>();
            var afterAllMethods = new ArrayList<Method>();
            var testingMethods = new ArrayList<Method>();

            Arrays.stream(testingClass.getDeclaredMethods()).forEach(m -> {
                if (m.isAnnotationPresent(BeforeAll.class)) beforeAllMethods.add(m);
                if (m.isAnnotationPresent(AfterAll.class)) afterAllMethods.add(m);
                if (m.isAnnotationPresent(Test.class)) testingMethods.add(m);
            });

            checkMethodSignature(beforeAllMethods, testingClass, BeforeAll.class);
            checkMethodSignature(afterAllMethods, testingClass, AfterAll.class);

            Object testingInstance = invokeReflectiveOperation(
                    () -> testingClass.getDeclaredConstructor().newInstance()
            );

            var execution = new Execution(
                    testingClass,
                    executeTestingMethods(testingMethods, testingInstance),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            executions.add(execution);

        }
        // Printing based on executions ...
        for (Execution execution : executions) {
            printExecutionResult(execution);
        }
    }

    private void printExecutionResult(Execution execution) {
        for (ExecutionItem<?> item : execution.executions()) {
            String result = String.format("Метод теста: %s.%s, Ожидаемый результат: %s, Фактический результат: %s, Успех: %s",
                    item.testingClass().getSimpleName(),
                    item.testingMethod().getName(),
                    item.expectedResult(),
                    item.actualResult(),
                    item.success()
            );
            for (Printer printer : printers) {
                printer.print(result);
            }
        }
    }

    private List<ExecutionItem<?>> executeTestingMethods(List<Method> testingMethods, Object testingInstance) {
        var executionItems = new ArrayList<ExecutionItem<?>>();

        testingMethods.stream()
                .filter(m -> m.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Test.class).order()))
                .forEach(m -> {
                    try {
                        invokeReflectiveOperation(() -> m.invoke(testingInstance));
                    } catch (AssertException e) {
                        executionItems.add(
                                new ExecutionItem<>(
                                        testingInstance.getClass(),
                                        m,
                                        e.getResult().expectedResult(),
                                        e.getResult().actualResult(),
                                        e.getResult().success()
                                )
                        );
                    }
                });

        return executionItems;
    }

    private void checkMethodSignature(List<Method> methods, Class<?> targetClass, Class<?> annotation) {
        if (methods.size() > 1) {
            throw new InvalidSignatureException(String.format(
                    "Class [%s] must not have more than one annotation %s",
                    targetClass.getSimpleName(),
                    annotation.getSimpleName()
            ));
        }
    }

    private <T> T invokeReflectiveOperation(InvokeFunction<T> func) {
        try {
            return func.invoke();
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AssertException assertException) {
                throw assertException;
            } else {
                throw new RuntimeException(e);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
