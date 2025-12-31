import controller.*;
import model.*;
import service.Payment;
import view.CustomerView;
import view.OrderView;
import view.ProductView;
import java.util.List;
import java.util.Scanner;

public class ECommerceSystem {
    private static CustomerController customerController;
    private static ProductController productController;
    private static ShoppingCartController cartController;
    private static OrderController orderController;
    private static PaymentController paymentController;

    private static CustomerView customerView;
    private static ProductView productView;
    private static OrderView orderView;

    private static Customer currentCustomer;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Initialize Controllers
        customerController = new CustomerController();
        productController = new ProductController();
        cartController = new ShoppingCartController();
        orderController = new OrderController();
        paymentController = new PaymentController();

        // Initialize Views
        customerView = new CustomerView();
        productView = new ProductView();
        orderView = new OrderView();

        scanner = new Scanner(System.in);

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO E-COMMERCE SYSTEM         ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Select Customer
        selectCustomer();

        // Main Menu Loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    removeProductFromCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    viewMyOrders();
                    break;
                case 7:
                    viewOrderDetails();
                    break;
                case 8:
                    switchCustomer();
                    break;
                case 9:
                    System.out.println("\n✓ Thank you for using our E-Commerce System!");
                    System.out.println("Goodbye, " + currentCustomer.getName() + "!\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n✗ Invalid choice! Please try again.\n");
            }
        }

        scanner.close();
    }

    private static void selectCustomer() {
        customerView.displayAllCustomers(customerController.getAllCustomers());
        int customerId = getIntInput("Select customer ID to continue: ");
        currentCustomer = customerController.getCustomerById(customerId);

        if (currentCustomer == null) {
            System.out.println("✗ Invalid customer ID! Selecting first customer by default.");
            currentCustomer = customerController.getAllCustomers().get(0);
        }

        System.out.println("\n✓ Welcome, " + currentCustomer.getName() + "!\n");
        customerView.displayCustomer(currentCustomer);
    }

    private static void switchCustomer() {
        System.out.println("\n========== SWITCH CUSTOMER ==========");
        selectCustomer();
    }

    private static void displayMainMenu() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            MAIN MENU                     ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. Browse Products                      ║");
        System.out.println("║  2. Add Product to Cart                  ║");
        System.out.println("║  3. View Shopping Cart                   ║");
        System.out.println("║  4. Remove Product from Cart             ║");
        System.out.println("║  5. Checkout                             ║");
        System.out.println("║  6. View My Orders                       ║");
        System.out.println("║  7. View Order Details                   ║");
        System.out.println("║  8. Switch Customer                      ║");
        System.out.println("║  9. Exit                                 ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void browseProducts() {
        productView.displayAllProducts(productController.getAllProducts());
        System.out.print("Enter product ID to view details (0 to skip): ");
        int productId = getIntInput("");

        if (productId > 0) {
            Product product = productController.getProductById(productId);
            if (product != null) {
                productView.displayProduct(product);
            } else {
                System.out.println("✗ Product not found!\n");
            }
        }
    }

    private static void addProductToCart() {
        productView.displayAllProducts(productController.getAllProducts());
        int productId = getIntInput("Enter product ID to add to cart: ");
        Product product = productController.getProductById(productId);

        if (product == null) {
            System.out.println("✗ Product not found!\n");
            return;
        }

        int quantity = getIntInput("Enter quantity: ");
        if (quantity <= 0) {
            System.out.println("✗ Invalid quantity!\n");
            return;
        }

        cartController.addToCart(currentCustomer, product, quantity);
        System.out.println("✓ Product added to cart successfully!\n");
    }

    private static void viewCart() {
        cartController.viewCart(currentCustomer);
    }

    private static void removeProductFromCart() {
        cartController.viewCart(currentCustomer);

        if (currentCustomer.getCart().getCartItems().isEmpty()) {
            return;
        }

        int productId = getIntInput("Enter product ID to remove from cart: ");
        Product product = productController.getProductById(productId);

        if (product == null) {
            System.out.println("✗ Product not found!\n");
            return;
        }

        cartController.removeFromCart(currentCustomer, product);
        System.out.println("✓ Product removed from cart!\n");
    }

    private static void checkout() {
        cartController.viewCart(currentCustomer);

        if (currentCustomer.getCart().getCartItems().isEmpty()) {
            System.out.println("✗ Cannot checkout with empty cart!\n");
            return;
        }

        System.out.print("Proceed to checkout? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("yes") && !confirm.equals("y")) {
            System.out.println("✗ Checkout cancelled.\n");
            return;
        }

        // Create order
        Order order = orderController.createOrderFromCart(currentCustomer);
        if (order == null) {
            return;
        }

        orderView.displayOrder(order);

        // Select payment method
        System.out.println("Select payment method:");
        System.out.println("1. Card Payment");
        System.out.println("2. Digital Wallet");
        System.out.println("3. Bank Transfer");
        int paymentChoice = getIntInput("Enter choice: ");

        Payment payment = null;
        switch (paymentChoice) {
            case 1:
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter cardholder name: ");
                String cardHolder = scanner.nextLine();
                payment = paymentController.createCardPayment(cardNumber, cardHolder);
                break;
            case 2:
                System.out.print("Enter wallet ID: ");
                String walletId = scanner.nextLine();
                payment = paymentController.createDigitalWalletPayment(walletId);
                break;
            case 3:
                System.out.print("Enter bank account number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter bank name: ");
                String bankName = scanner.nextLine();
                payment = paymentController.createBankTransferPayment(accountNumber, bankName);
                break;
            default:
                System.out.println("✗ Invalid payment method! Using Card Payment as default.");
                payment = paymentController.createCardPayment("1234567890123456", currentCustomer.getName());
        }

        // Generate invoice and process payment
        Invoice invoice = orderController.generateInvoice(order, payment);
        paymentController.processPayment(invoice);

        // Confirm order
        orderController.confirmOrder(order.getOrderId());

        // Create shipment
        Shipment shipment = orderController.createShipment(order);
        if (shipment != null) {
            shipment.setStatus("SHIPPED");
        }

        System.out.println("✓ Order completed successfully!");
        System.out.println("Order ID: #" + order.getOrderId());
        if (shipment != null) {
            System.out.println("Tracking Number: " + shipment.getTrackingNumber());
        }
        System.out.println();
    }

    private static void viewMyOrders() {
        List<Order> customerOrders = currentCustomer.getOrders();

        if (customerOrders.isEmpty()) {
            System.out.println("\n✗ You have no orders yet.\n");
            return;
        }

        orderView.displayAllOrders(customerOrders);
    }

    private static void viewOrderDetails() {
        List<Order> customerOrders = currentCustomer.getOrders();

        if (customerOrders.isEmpty()) {
            System.out.println("\n✗ You have no orders yet.\n");
            return;
        }

        orderView.displayAllOrders(customerOrders);
        int orderId = getIntInput("Enter order ID to view details: ");
        Order order = currentCustomer.getOrder(orderId);

        if (order == null) {
            System.out.println("✗ Order not found!\n");
            return;
        }

        orderView.displayOrder(order);

        if (order.getShipment() != null) {
            System.out.println("========== SHIPMENT INFO ==========");
            System.out.println("Tracking Number: " + order.getShipment().getTrackingNumber());
            System.out.println("Status: " + order.getShipment().getDeliveryStatus());
            System.out.println("===================================\n");
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("✗ Invalid input! Please enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }
}