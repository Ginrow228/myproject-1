package lesson23;

public class MathUtilsTest {

    @BeforeSuite
    public static void setup(){
        System.out.println("Подготовка к тестам...");
    }

    @Test(order = 2)
    public static void shouldSumTwoGivenPositiveValues() {
        if (MathUtils.addition(10, 11) == 22) {
            System.out.println("shouldSumTwoGivenPositiveValues прошел тест");
        } else {
            System.out.println("shouldSumTwoGivenPositiveValues неудачно провалился");
        }
    }

    @Test(order = 1)
    public static void shouldSubtractTwoGivenPositiveValues() {
        if (MathUtils.subtract(20, 11) == 9) {
            System.out.println("shouldSubtractTwoGivenPositiveValues прошел тест");
        } else {
            System.out.println("shouldSubtractTwoGivenPositiveValues неудачно провалился");
        }
    }

    @AfterSuite
    public static void clean(){
        System.out.println("Происходит очистка данных после тестов...");
    }
}
