package view;

import model.Order;

import java.util.List;

public class OrderView {
    public void displayOrder(Order order) {
        if (order != null) {
            System.out.println("\n=== Order Details ===");
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Total: $" + order.getTotalAmount());
            System.out.println("\nItems:");
            order.getOrderItems().forEach(item -> {
                System.out.println("  - " + item.getProduct().getName() +
                        " x " + item.getQuantity() +
                        " = $" + item.getTotalPrice());
            });
        }
    }

    public void displayAllOrders(List<Order> orders) {
        System.out.println("\n=== All Orders ===");
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            orders.forEach(order -> {
                System.out.println("Order #" + order.getOrderId() +
                        ", Date: " + order.getOrderDate() +
                        ", Status: " + order.getStatus() +
                        ", Total: $" + order.getTotalAmount());
            });
        }
    }
}
