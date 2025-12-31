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

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
    public Invoice getInvoice() { return invoice; }
    public Shipment getShipment() { return shipment; }
    public void setShipment(Shipment shipment) { this.shipment = shipment; }
}
