import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplyArrayTest {

    private MultiplyArray multiplyArray;

    @BeforeEach
    public void setUp() {
        multiplyArray = new MultiplyArray();
    }

    @Test
    public void shouldReturnNewArrayOfMultiplyAllElementsInArray() {
        Integer[] expectedArray = new Integer[]{120, 60, 40, 30, 24};
        Integer[] requestArray = new Integer[]{1, 2, 3, 4, 5};

        Integer[] resultArray = multiplyArray.multiply(requestArray);

        assertNotNull(resultArray);
        assertEquals(Arrays.toString(expectedArray), Arrays.toString(resultArray));
    }

    @Test
    public void shouldReturnExceptionWhenArrayNull() {
        Integer[] requestArray = null;

        assertThrows(IllegalArgumentException.class, () -> multiplyArray.multiply(requestArray));
    }

    @Test
    public void shouldReturnNewArrayEmptyWhenArrayEmpty() {
        Integer[] requestArray = new Integer[]{};
        assertThrows(IllegalArgumentException.class, () -> multiplyArray.multiply(requestArray));
    }
}
