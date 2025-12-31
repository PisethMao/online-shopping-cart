package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class ShoppingCart {
    private int cartId;
    private List<CartItem> cartItems;
    private double totalAmount;

    public ShoppingCart(int cartId) {
        this.cartId = cartId;
        this.cartItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void setItem(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + quantity);
                calculateTotal();
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
        calculateTotal();
    }

    public void removeItem(Product product) {
        cartItems.removeIf(item -> item.getProduct().getProductId() == product.getProductId());
        calculateTotal();
    }

    public void clearCart() {
        cartItems.clear();
        totalAmount = 0.0;
    }

    public double getTotal() {
        return totalAmount;
    }

    private void calculateTotal() {
        totalAmount = cartItems.stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

}
