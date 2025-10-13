import java.math.BigDecimal;
import java.math.RoundingMode;


public class IncomeCalculator {

    private final Double selicRateLimit;

    public IncomeCalculator(Double selicRateLimit) {
        this.selicRateLimit = selicRateLimit;
    }

    public IncomeReport calcuteSavingsIncome(Double initialAmount, Integer months, Double selicRate, Double referenceRate) {
        if (initialAmount <= 0 || months <= 0) throw new IllegalArgumentException("Initial Amount or Months invalid");
        Double monthlyYield = calculeMonthlyYield(selicRate, referenceRate);
        IncomeReport incomeReport = new IncomeReport(new BigDecimal(initialAmount));
        BigDecimal currentAmount = new BigDecimal(initialAmount);

        for (int i = 1; i <= months; i++) {
            BigDecimal monthlyResult = currentAmount;
            monthlyResult = monthlyResult.multiply(BigDecimal.valueOf(1 + monthlyYield)).setScale(2, RoundingMode.HALF_UP);
            BigDecimal monthlyIncome = monthlyResult.subtract(currentAmount);
            incomeReport.addValues(monthlyIncome, monthlyResult);
            currentAmount = monthlyResult;
        }

        return incomeReport;
    }

    private Double calculeMonthlyYield(Double selicRate, Double referenceRate) {
        if (selicRate <= 0 && referenceRate <= 0) throw new IllegalArgumentException("Selic and Reference Rate are zero");
        return (selicRate <= selicRateLimit)
                ? (selicRate * 0.7 / 12) / 100 + (referenceRate / 100)
                : (0.5 / 100 + (referenceRate / 100));
    }
}
