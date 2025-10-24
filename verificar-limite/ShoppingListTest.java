import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {

    private ShoppingList shoppingList;
    private Product product;
    private Product secondProduct;

    private static final String NAME_PRODUCT = "Test";
    private static final BigDecimal PRICE_PRODUCT = BigDecimal.valueOf(10);

    @BeforeEach
    public void setUp() {
        shoppingList = new ShoppingList(BigDecimal.valueOf(30.00));
        product = new Product(NAME_PRODUCT, PRICE_PRODUCT);
        secondProduct = new Product(NAME_PRODUCT, PRICE_PRODUCT);
    }

    @Test
    public void shouldAddLimitWhenValueIsValid() {
        BigDecimal limitExpected = BigDecimal.valueOf(50.00).setScale(2, RoundingMode.HALF_UP);

        shoppingList.addLimit(BigDecimal.valueOf(20));

        assertEquals(limitExpected, shoppingList.getPurchaseLimit());
    }

    @Test
    public void shouldAddProductsToProductListWhenValid() {
        int sizeExpected = 2;

        shoppingList.addProduct(product);
        shoppingList.addProduct(secondProduct);

        assertEquals(sizeExpected, shoppingList.getProductList().size());
    }

    @Test
    public void shouldRemoveProductsToProductListWhenIndexValid() {
        int sizeExpected = 1;

        shoppingList.addProduct(product);
        shoppingList.addProduct(secondProduct);

        shoppingList.removeProduct(secondProduct);

        assertEquals(sizeExpected, shoppingList.getProductList().size());
        assertFalse(shoppingList.getProductList().contains(secondProduct));
    }

    @Test
    public void shouldThrowExceptionWhenAddLimitIsNegativeArgument() {
        assertThrows(IllegalArgumentException.class, () -> shoppingList.addLimit(BigDecimal.valueOf(-1)));
    }

    @Test
    public void shouldThrowExceptionWhenAddProductNull() {
        Product productNull = null;
        assertThrows(IllegalArgumentException.class, () ->  shoppingList.addProduct(productNull));
    }

    @Test
    public void shouldThrowExceptionWhenRemoveProductNonExistentInProductList() {
        Product productOffList = new Product(NAME_PRODUCT, PRICE_PRODUCT);
        assertThrows(IllegalArgumentException.class, () ->  shoppingList.removeProduct(productOffList));
    }

    @Test
    public void shouldThrowExceptionWhenFinishingPurchaseWithLimitInsufficient() {
        Product productGreaterValue = new Product(NAME_PRODUCT, BigDecimal.valueOf(100));

        shoppingList.addProduct(productGreaterValue);

        assertThrows(RuntimeException.class, () ->  shoppingList.finishPurchase());
    }

    @Test
    public void shouldFinishPurchaseWhenLimitAvailable() {
        BigDecimal newLimitAvailable = BigDecimal.valueOf(10.00).setScale(2, RoundingMode.HALF_UP);

        shoppingList.addProduct(product);
        shoppingList.addProduct(secondProduct);

        assertTrue(shoppingList.finishPurchase());
        assertEquals(newLimitAvailable, shoppingList.getPurchaseLimit());
        assertEquals(0, shoppingList.getProductList().size());

    }
}
