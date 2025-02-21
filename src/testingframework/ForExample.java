package testingframework;

public class ForExample {

    @Test
    void shouldSumGivenIntegers() {
        Assertions.equal(10, 3 + 3 + 3 + 1);
    }

    @Test
    void shouldDivideGivenIntegers() {
        Assertions.equal(5, Math.divideExact(10, 2));
    }
}
