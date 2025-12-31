package controller;

import model.*;
import service.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private List<Order> orders;
    private int nextOrderId;
    private int nextInvoiceId;
    private int nextShipmentId;

    public OrderController() {
        this.orders = new ArrayList<>();
        this.nextOrderId = 1001;
        this.nextInvoiceId = 5001;
        this.nextShipmentId = 9001;
    }

    public Order createOrderFromCart(Customer customer) {
        if (customer == null || customer.getCart().getCartItems().isEmpty()) {
            System.out.println("Cannot create order: Cart is empty");
            return null;
        }

        Order order = new Order(nextOrderId++,LocalDate.now());

        // Convert cart items to order items
        customer.getCart().getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem(
                    cartItem.getProduct(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()
            );
            order.addOrderItem(orderItem);
        });

        customer.addOrder(order);
        orders.add(order);

        // Clear cart after order
        customer.getCart().clearCart();

        System.out.println("\n✓ Order created successfully: #" + order.getOrderId());
        return order;
    }

    public void confirmOrder(int orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.confirmOrder();
        }
    }

    public void cancelOrder(int orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.cancelOrder();
        }
    }

    public Invoice generateInvoice(Order order, Payment payment) {
        if (order != null) {
            Invoice invoice = new Invoice(nextInvoiceId++, LocalDate.now(), order.getTotalAmount());
            invoice.setPayment(payment);
            invoice.setInvoice(order);
            order.setInvoice(invoice);
            return invoice;
        }
        return null;
    }

    public Shipment createShipment(Order order) {
        if (order != null && order.getStatus().equals("CONFIRMED")) {
            String trackingNumber = "TRK" + nextShipmentId;
            Shipment shipment = new Shipment(nextShipmentId++, trackingNumber);
            order.setShipment(shipment);
            System.out.println("Shipment created with tracking: " + trackingNumber);
            return shipment;
        }
        return null;
    }

    public Order getOrderById(int orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}
