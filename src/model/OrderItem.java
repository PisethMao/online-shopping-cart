package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderItem {
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

    // Getters and Setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }
}
