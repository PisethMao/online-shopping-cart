package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class Customer {
    // Getters and Setters
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private ShoppingCart cart;
    private List<Order> orders;

    public Customer(int customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cart = new ShoppingCart(customerId);
        this.orders = new ArrayList<>();
    }

    public void setCart(Product product, int quantity) {
        cart.setItem(product, quantity);
    }

    public Order getOrder(int orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
