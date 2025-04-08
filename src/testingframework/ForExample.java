package testingframework;

import testclass.Employee;

public class ForExample {

    @Test
    void shouldSumGivenIntegers() {
        Assertions.equal(10, 3 + 3 + 3 + 1);
    }

    @Test
    void shouldDivideGivenIntegers() {
        Assertions.equal(5, Math.divideExact(10, 2));
    }

    @Test
    void shouldContainSubArrayInArray() {
        Integer[] current = {5, 9, 1, 2, 3, 10};
        Integer[] toContain = {1, 2, 3};

        Assertions.contains(current, toContain);
    }

    @Test
    void shouldNotContainSubArrayInArray() {
        Integer[] current = {5, 9, 1, 2, 3, 10};
        Integer[] toContain = {2, 3, 11};

        Assertions.contains(current, toContain);
    }

    @Test
    void testEqualRecursively() {
        Employee employee1 = new Employee("Jerry", 25, "Manager");
        Employee employee2 = new Employee("Jerry", 25, "Manager");

        Assertions.equalRecursively(employee1, employee2);
    }

    @Test
    void testNotEqualRecursively() {
        Employee employee1 = new Employee("Jerry", 25, "Manager");
        Employee employee2 = new Employee("Maria", 25, "Admin");

        Assertions.equalRecursively(employee1, employee2);
    }

}
