package lesson26;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMethods {

    @Test
    public void testElementsAfterLastFourMiddle(){
        int[] input = {1, 2, 4, 5, 6, 7};
        int[] expected = {5, 6, 7};

        int[] result = ArrayUtils.ElementsAfterLastFour(input);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testElementsAfterLastFourMultipleFours() {
        int[] input = {1, 4, 2, 4, 5, 6};
        int[] expected = {5, 6};

        int[] result = ArrayUtils.ElementsAfterLastFour(input);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testElementsAfterLastFourAtEnd() {
        int[] input = {1, 2, 3, 4};
        int[] expected = {};

        int[] result = ArrayUtils.ElementsAfterLastFour(input);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testElementsAfterLastFourAtBeginning() {
        int[] input = {4, 1, 2, 3};
        int[] expected = {1, 2, 3};

        int[] result = ArrayUtils.ElementsAfterLastFour(input);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testElementsAfterLastFourWithNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            ArrayUtils.ElementsAfterLastFour(null);
        });
    }

    @Test
    public void testContainsOnlyOneAndFourPositive() {
        int[] input = {1, 4, 1, 1, 4, 4};
        boolean result = ArrayUtils.containsOnlyOneAndFour(input);

        Assertions.assertTrue(result);
    }

    @Test
    public void testContainsOnlyOneAndFourOnlyOnes() {
        int[] input = {1, 1, 1};
        boolean result = ArrayUtils.containsOnlyOneAndFour(input);

        Assertions.assertFalse(result);
    }

    @Test
    public void testContainsOnlyOneAndFourOnlyFours() {
        int[] input = {4, 4, 4};
        boolean result = ArrayUtils.containsOnlyOneAndFour(input);

        Assertions.assertFalse(result);
    }

    @Test
    public void testContainsOnlyOneAndFourWithOtherNumbers() {
        int[] input = {1, 4, 5, 1};
        boolean result = ArrayUtils.containsOnlyOneAndFour(input);

        Assertions.assertFalse(result);
    }

    @Test
    public void testContainsOnlyOneAndFourWithEmptyArray() {
        int[] input = {};
        boolean result = ArrayUtils.containsOnlyOneAndFour(input);

        Assertions.assertFalse(result);
    }
}