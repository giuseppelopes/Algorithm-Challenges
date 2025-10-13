import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IncomeReport {

    private BigDecimal initialAmount;
    private BigDecimal finalAmount;
    private List<BigDecimal> incomeMonthly;
    private List<BigDecimal> amountMonthly;

    public IncomeReport(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
        this.finalAmount = new BigDecimal(0);
        this.incomeMonthly = new ArrayList<>();
        this.amountMonthly = new ArrayList<>();
    }

    public void addValues(BigDecimal income, BigDecimal amount) {
        incomeMonthly.add(income);
        amountMonthly.add(amount);
        finalAmount = amount;
    }

    public StringBuilder generateImport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Initial Amount: $%.2f\n", initialAmount));
        for(int i = 0; i < incomeMonthly.size(); i++) {
            report.append(String.format("Month %d - Income: $%.2f - Monthly Amount: $%.2f\n",
                    i + 1, incomeMonthly.get(i), amountMonthly.get(i)));
        }
        report.append(String.format("Final Amount: $%.2f\n", finalAmount));
        report.append("---");
        return report;
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public List<BigDecimal> getIncomeMonthly() {
        return incomeMonthly;
    }

    public List<BigDecimal> getAmountMonthly() {
        return amountMonthly;
    }
}
