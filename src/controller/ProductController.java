package controller;

import model.Address;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private List<Product> products;
    private int nextProductId;

    public ProductController() {
        this.products = new ArrayList<>();
        this.nextProductId = 1;
        initializeProducts();
    }

    private void initializeProducts() {
        Address addr1 = new Address("123 Tech Street", "New York", "10001");
        Address addr2 = new Address("456 Electronics Ave", "Los Angeles", "90001");
        Address addr3 = new Address("789 Gadget Road", "Chicago", "60601");
        Address addr4 = new Address("321 Device Lane", "Houston", "77001");

        products.add(new Product(nextProductId++, "Laptop Dell XPS 15", 1299.99, "High-performance laptop with 16GB RAM", addr1));
        products.add(new Product(nextProductId++, "MacBook Pro 14", 1999.99, "Apple M2 chip, 512GB SSD", addr1));
        products.add(new Product(nextProductId++, "Wireless Mouse Logitech", 29.99, "Ergonomic wireless mouse", addr2));
        products.add(new Product(nextProductId++, "Mechanical Keyboard", 79.99, "RGB backlit gaming keyboard", addr2));
        products.add(new Product(nextProductId++, "27-inch Monitor", 349.99, "4K UHD LED monitor", addr3));
        products.add(new Product(nextProductId++, "USB-C Hub", 49.99, "7-in-1 USB-C adapter", addr3));
        products.add(new Product(nextProductId++, "Webcam HD", 89.99, "1080p HD webcam with microphone", addr4));
        products.add(new Product(nextProductId++, "Wireless Headset", 149.99, "Noise-cancelling Bluetooth headset", addr4));
        products.add(new Product(nextProductId++, "External SSD 1TB", 119.99, "Portable solid-state drive", addr1));
        products.add(new Product(nextProductId++, "Laptop Stand", 39.99, "Adjustable aluminum stand", addr2));
    }

    public Product createProduct(String name, double price, String description, Address address) {
        Product product = new Product(nextProductId++, name, price, description, address);
        products.add(product);
        System.out.println("Product created: " + product.getName());
        return product;
    }

    public Product getProductById(int productId) {
        return products.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public void updateProduct(int productId, String name, double price, String description) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            System.out.println("Product updated: " + product.getName());
        }
    }

    public void deleteProduct(int productId) {
        products.removeIf(p -> p.getProductId() == productId);
        System.out.println("Product deleted with ID: " + productId);
    }
}
