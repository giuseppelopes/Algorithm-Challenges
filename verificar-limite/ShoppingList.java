import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private BigDecimal purchaseLimit;
    private List<Product> productList;

    public ShoppingList(BigDecimal purchaseLimit) {
        if(purchaseLimit.equals(new BigDecimal(0))) throw new IllegalArgumentException("Purchase Limit is zero or negative!");
        this.purchaseLimit = purchaseLimit.setScale(2, RoundingMode.HALF_UP);
        this.productList = new ArrayList<>();
    }

    public BigDecimal getPurchaseLimit() {
        return this.purchaseLimit;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public BigDecimal getTotalPriceProductList() {
        BigDecimal totalPurchase = new BigDecimal(0);
        for(Product p : productList) {
            totalPurchase = totalPurchase.add(p.getPrice());
        }
        return totalPurchase;
    }

    public void addLimit(BigDecimal value) {
        if(value.longValue() < BigDecimal.valueOf(0).longValue()) throw new IllegalArgumentException("Purchase Limit add negative!");
        purchaseLimit = purchaseLimit.add(value).setScale(2, RoundingMode.HALF_UP);
    }

    private void discountLimit(BigDecimal value) {
        purchaseLimit = purchaseLimit.subtract(value);
    }

    public void addProduct(Product product) {
        if(product == null) throw new IllegalArgumentException("Product is null!");
        productList.add(product);
    }

    public void removeProduct(Product product) {
        if(!productList.remove(product)) throw new IllegalArgumentException("Product non exists!");
    }

    public Boolean finishPurchase() {
        BigDecimal totalPurchase = getTotalPriceProductList();
        if(purchaseLimit.longValue() < totalPurchase.longValue()) throw new RuntimeException("Insufficient purchase limit!");
        this.productList.clear();
        discountLimit(totalPurchase);
        return true;
    }

    public String getReport() {
        return "PurchaceLimit: " + getPurchaseLimit()
                + " | Products: " + getProductList();
    }
}
