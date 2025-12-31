package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class Order {
    private int orderId;
    private LocalDate orderDate;
    private String status;
    private double totalAmount;
    private List<OrderItem> orderItems;
    private Invoice invoice;
    private Shipment shipment;

    public Order(int orderId, LocalDate orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = "PENDING";
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void confirmOrder() {
        this.status = "CONFIRMED";
        System.out.println("Order " + orderId + " confirmed!");
    }

    public void cancelOrder() {
        this.status = "CANCELLED";
        System.out.println("Order " + orderId + " cancelled!");
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        calculateTotal();
    }

    private void calculateTotal() {
        totalAmount = orderItems.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }
}
