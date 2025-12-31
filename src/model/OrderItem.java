package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItem {
    // Getters and Setters
    private Product product;
    private int quantity;
    private double purchasePrice;

    public OrderItem(Product product, int quantity, double purchasePrice) {
        this.product = product;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public double getTotalPrice() {
        return purchasePrice * quantity;
    }

}
