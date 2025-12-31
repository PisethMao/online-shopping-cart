import controller.*;
import model.*;
import service.Payment;
import view.CustomerView;
import view.OrderView;
import view.ProductView;

void main() {
    // Initialize Controllers
    CustomerController customerController = new CustomerController();
    ProductController productController = new ProductController();
    ShoppingCartController cartController = new ShoppingCartController();
    OrderController orderController = new OrderController();
    PaymentController paymentController = new PaymentController();

    // Initialize Views
    CustomerView customerView = new CustomerView();
    ProductView productView = new ProductView();
    OrderView orderView = new OrderView();

    System.out.println("=== E-Commerce System Started ===\n");

    // Create Addresses
    Address addr1 = new Address("123 Main St", "New York", "10001");
    Address addr2 = new Address("456 Oak Ave", "Los Angeles", "90001");

    // Create Products
    Product laptop = productController.createProduct("Laptop", 999.99, "High-performance laptop", addr1);
    Product mouse = productController.createProduct("Wireless Mouse", 29.99, "Ergonomic mouse", addr1);
    Product keyboard = productController.createProduct("Mechanical Keyboard", 79.99, "RGB keyboard", addr2);

    // Display all products
    productView.displayAllProducts(productController.getAllProducts());

    // Create Customer
    Customer customer = customerController.createCustomer("John Doe", "john@example.com", "555-1234");
    customerView.displayCustomer(customer);

    // Add items to cart
    cartController.addToCart(customer, laptop, 1);
    cartController.addToCart(customer, mouse, 2);
    cartController.addToCart(customer, keyboard, 1);

    // View cart
    cartController.viewCart(customer);

    // Create order from cart
    Order order = orderController.createOrderFromCart(customer);
    orderView.displayOrder(order);

    // Confirm order
    orderController.confirmOrder(order.getOrderId());

    // Create payment
    Payment cardPayment = paymentController.createCardPayment("1234567890123456", "John Doe");

    // Generate invoice
    Invoice invoice = orderController.generateInvoice(order, cardPayment);

    // Process payment
    paymentController.processPayment(invoice);

    // Create shipment
    Shipment shipment = orderController.createShipment(order);
    shipment.setStatus("SHIPPED");
    shipment.setStatus("DELIVERED");

    // Display final order
    orderView.displayOrder(order);

    // Display all orders
    orderView.displayAllOrders(orderController.getAllOrders());

    System.out.println("\n=== E-Commerce System Demo Completed ===");
}
