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
            int choice = getMenuChoice();

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
        List<Customer> customers = customerController.getAllCustomers();
        customerView.displayAllCustomers(customers);

        int customerId = getCustomerIdInput(customers);
        currentCustomer = customerController.getCustomerById(customerId);

        if (currentCustomer == null) {
            System.out.println("✗ Invalid customer ID! Selecting first customer by default.");
            currentCustomer = customers.get(0);
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
        List<Product> products = productController.getAllProducts();
        productView.displayAllProducts(products);

        System.out.print("Enter product ID to view details (0 to skip): ");
        int productId = getProductIdInput(products, true);

        if (productId > 0) {
            Product product = productController.getProductById(productId);
            if (product != null) {
                productView.displayProduct(product);
            }
        }
    }

    private static void addProductToCart() {
        List<Product> products = productController.getAllProducts();
        productView.displayAllProducts(products);

        int productId = getProductIdInput(products, false);
        Product product = productController.getProductById(productId);

        if (product == null) {
            System.out.println("✗ Product not found!\n");
            return;
        }

        int quantity = getPositiveIntInput("Enter quantity: ");
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
            System.out.println("✗ Cart is empty!\n");
            return;
        }

        List<Product> products = productController.getAllProducts();
        int productId = getProductIdInput(products, false);
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
        int paymentChoice = getPaymentMethodChoice();

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
        int orderId = getOrderIdInput(customerOrders);
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

    // ========== VALIDATION METHODS ==========

    private static int getMenuChoice() {
        while (true) {
            System.out.print("Enter your choice (1-9): ");
            String input = scanner.nextLine().trim();

            // Reject inputs with leading zeros or multiple digits
            if (input.isEmpty() || input.length() > 1 || !Character.isDigit(input.charAt(0))) {
                System.out.println("✗ Invalid input! Please enter a single digit (1-9).");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 9) {
                    return choice;
                } else {
                    System.out.println("✗ Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }

    private static int getCustomerIdInput(List<Customer> customers) {
        List<Integer> validIds = customers.stream()
                .map(Customer::getCustomerId)
                .toList();

        while (true) {
            System.out.print("Select customer ID to continue: ");
            String input = scanner.nextLine().trim();

            // Reject empty input
            if (input.isEmpty()) {
                System.out.println("✗ Input cannot be empty!");
                continue;
            }

            // Reject inputs with leading zeros (unless it's "0")
            if (!input.equals("0") && input.startsWith("0")) {
                System.out.println("✗ Please enter a number without leading zeros.");
                continue;
            }

            try {
                int id = Integer.parseInt(input);
                if (validIds.contains(id)) {
                    return id;
                } else {
                    System.out.println("✗ Invalid customer ID! Available IDs: " + validIds);
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }

    private static int getProductIdInput(List<Product> products, boolean allowZero) {
        List<Integer> validIds = products.stream()
                .map(Product::getProductId)
                .toList();

        while (true) {
            if (allowZero) {
                System.out.print("Enter product ID (0 to skip, available IDs: " + validIds + "): ");
            } else {
                System.out.print("Enter product ID (available IDs: " + validIds + "): ");
            }

            String input = scanner.nextLine().trim();

            // Reject empty input
            if (input.isEmpty()) {
                System.out.println("✗ Input cannot be empty!");
                continue;
            }

            // For product IDs, we might want to allow "0" but reject "01", "001", etc.
            if (allowZero) {
                // If it's "0", accept it immediately
                if (input.equals("0")) {
                    return 0;
                }
                // Otherwise reject any input starting with "0"
                if (input.startsWith("0")) {
                    System.out.println("✗ Please enter a number without leading zeros.");
                    continue;
                }
            } else {
                // If not allowing zero, reject any input starting with "0"
                if (input.startsWith("0")) {
                    System.out.println("✗ Please enter a number without leading zeros.");
                    continue;
                }
            }

            try {
                int id = Integer.parseInt(input);
                if (validIds.contains(id)) {
                    return id;
                } else {
                    System.out.println("✗ Invalid product ID!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }

    private static int getPositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Reject empty input
            if (input.isEmpty()) {
                System.out.println("✗ Input cannot be empty!");
                continue;
            }

            // Reject inputs with leading zeros (unless it's "0")
            if (!input.equals("0") && input.startsWith("0")) {
                System.out.println("✗ Please enter a number without leading zeros.");
                continue;
            }

            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("✗ Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }

    private static int getPaymentMethodChoice() {
        while (true) {
            System.out.print("Enter choice (1-3): ");
            String input = scanner.nextLine().trim();

            // Reject inputs with leading zeros or multiple digits
            if (input.isEmpty() || input.length() > 1 || !Character.isDigit(input.charAt(0))) {
                System.out.println("✗ Invalid input! Please enter a single digit (1-3).");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("✗ Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }

    private static int getOrderIdInput(List<Order> orders) {
        List<Integer> validIds = orders.stream()
                .map(Order::getOrderId)
                .toList();

        while (true) {
            System.out.print("Enter order ID (available IDs: " + validIds + "): ");
            String input = scanner.nextLine().trim();

            // Reject empty input
            if (input.isEmpty()) {
                System.out.println("✗ Input cannot be empty!");
                continue;
            }

            // Reject inputs with leading zeros (unless it's "0")
            if (!input.equals("0") && input.startsWith("0")) {
                System.out.println("✗ Please enter a number without leading zeros.");
                continue;
            }

            try {
                int id = Integer.parseInt(input);
                if (validIds.contains(id)) {
                    return id;
                } else {
                    System.out.println("✗ Invalid order ID!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            }
        }
    }
}