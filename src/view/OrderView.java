package view;

import model.Order;

import java.util.List;

public class OrderView {
    public void displayOrder(Order order) {
        if (order != null) {
            System.out.println("\n========== ORDER DETAILS ==========");
            System.out.println("Order ID: #" + order.getOrderId());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("Status: " + order.getStatus());
            System.out.println("-----------------------------------");
            System.out.println("Items:");
            order.getOrderItems().forEach(item -> {
                System.out.printf("  %-25s x %d = $%.2f%n",
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice());
            });
            System.out.println("-----------------------------------");
            System.out.printf("TOTAL: $%.2f%n", order.getTotalAmount());
            System.out.println("===================================\n");
        }
    }

    public void displayAllOrders(List<Order> orders) {
        System.out.println("\n========== ALL ORDERS ==========");
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            orders.forEach(order -> {
                System.out.printf("Order #%-6d | Date: %-12s | Status: %-10s | Total: $%.2f%n",
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getTotalAmount());
            });
        }
        System.out.println("================================\n");
    }
}
