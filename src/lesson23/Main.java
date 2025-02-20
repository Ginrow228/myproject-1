package lesson23;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            TestRunner.start(MathUtilsTest.class);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static class TestRunner {
        public static void start(Class<?> aClass) throws Exception {
            Method[] methods = aClass.getDeclaredMethods();
            Method beforeSuitemethod = null;
            Method afterSuiteMethod = null;
            List<Method> testMethods = new ArrayList<>();

            for(Method method : methods){
                if(method.isAnnotationPresent(BeforeSuite.class)){
                    if(beforeSuitemethod != null){
                        throw new IllegalStateException("@BeforeSuite метод уже существует");
                    }
                    beforeSuitemethod = method;
                } else if (method.isAnnotationPresent(AfterSuite.class)){
                    if(afterSuiteMethod != null) {
                        throw new IllegalStateException("@AfterSuite метож уже существует");
                    }
                    afterSuiteMethod = method;
                } else if (method.isAnnotationPresent(Test.class)){
                    testMethods.add(method);
                }
            }
            if(beforeSuitemethod != null){
                beforeSuitemethod.setAccessible(true);
                beforeSuitemethod.invoke(null);
            }

            testMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).order()));

            for(Method method : testMethods){
                System.out.println("Запускаем тест:" + method.getName());
                method.setAccessible(true);
                method.invoke(null);
            }

            if(afterSuiteMethod != null){
                afterSuiteMethod.setAccessible(true);
                afterSuiteMethod.invoke(null);
            }

        }
    }
}
