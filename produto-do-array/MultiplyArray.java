import java.util.ArrayList;
import java.util.List;

public class MultiplyArray {

    public List<Long> multiply(List<Integer> numbers) {

        if (numbers.isEmpty()) throw new IllegalArgumentException();
        List<Long> result = new ArrayList<>();

        Long prefix = 1L;
        for (int i = 0; i < numbers.size(); i++) {
            result.add(prefix);
            prefix *= numbers.get(i);
        }

        Long suffix = 1L;
        for (int i = numbers.size() - 1; i >= 0; i--) {
            result.set(i, result.get(i) * suffix);
            suffix *= numbers.get(i);
        }

        return result;
    }
}
