import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplyArrayTest {

    private MultiplyArray multiplyArray;

    @BeforeEach
    public void setUp() {
        multiplyArray = new MultiplyArray();
    }

    @Test
    public void shouldReturnNewArrayOfMultiplyAllElementsInArray() {
        List<Long> expected = new ArrayList<>(List.of(120L, 60L, 40L, 30L, 24L));
        List<Integer> request = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        List<Long> result = multiplyArray.multiply(request);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnEmptyNewArrayWhenArrayEmpty() {
        List<Integer> requestArray = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> multiplyArray.multiply(requestArray));
    }

    @Test
    public void shouldReturnNewArrayWhenContainOneZero() {
        List<Long> expected = new ArrayList<>(List.of(0L, 8L, 0L));
        List<Integer> request = new ArrayList<>(List.of(2, 0, 4));

        List<Long> result = multiplyArray.multiply(request);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnAllZerosNewArrayWhenContainTwoOrMoreZero() {
        List<Long> expected = new ArrayList<>(List.of(0L, 0L, 0L, 0L));
        List<Integer> request = new ArrayList<>(List.of(2, 0, 4, 0));

        List<Long> result = multiplyArray.multiply(request);

        assertNotNull(result);
        assertEquals(expected, result);
    }
}
