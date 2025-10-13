import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class IncomeCalculatorTest {

    private IncomeCalculator calculator;

    private static final Double SELIC_RATE_LIMIT = 8.5;
    private static final Double VALUE = 1000.00;
    private static final Integer MONTHS = 12;

    @BeforeEach
    public void setUp() {
        calculator = new IncomeCalculator(SELIC_RATE_LIMIT);
    }

    @Test
    public void shouldReturnValueWhenSelicIsBigger85() { // $1061,69 - Selic 9.0 and 12 months
        IncomeReport report = calculator.calcuteSavingsIncome(VALUE, MONTHS, 9.0, 0.0);

        assertNotNull(report);
        assertEquals(VALUE, report.getInitialAmount().doubleValue());
        assertEquals(MONTHS, report.getAmountMonthly().size());
        assertEquals(1061.69, report.getFinalAmount().doubleValue());
    }

    @Test
    public void shouldReturnValueWhenSelicIsSmaller85() { // $1057,46 - Selic 8.0 and 12 months
        IncomeReport report = calculator.calcuteSavingsIncome(VALUE, MONTHS, 8.0, 0.0);

        assertNotNull(report);
        assertEquals(VALUE, report.getInitialAmount().doubleValue());
        assertEquals(MONTHS, report.getAmountMonthly().size());
        assertEquals(1057.46, report.getFinalAmount().doubleValue());
    }

    @Test
    public void shouldReturnValueWhenSelicIsEqual85() { // $1061,15 - Selic 8.5 and 12 months
        IncomeReport report = calculator.calcuteSavingsIncome(VALUE, MONTHS, 8.5, 0.0);

        assertNotNull(report);
        assertEquals(VALUE, report.getInitialAmount().doubleValue());
        assertEquals(MONTHS, report.getAmountMonthly().size());
        assertEquals(1061.15, report.getFinalAmount().doubleValue());
    }

    @Test
    public void shouldReturnExceptionWhenSelicAndReferenceRateIsZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calcuteSavingsIncome(VALUE, MONTHS, 0.0, 0.0));
    }

    @Test
    public void shouldReturnExceptionWhenAmountAndMonthsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calcuteSavingsIncome(-10.00, -4, 8.0, 0.0));
    }
}
