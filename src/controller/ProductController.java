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
