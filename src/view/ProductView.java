package view;

import model.Product;

import java.util.List;

public class ProductView {
    public void displayProduct(Product product) {
        if (product != null) {
            System.out.println("\n=== Product Details ===");
            System.out.println("ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Location: " + product.getAddress().getFullAddress());
        }
    }

    public void displayAllProducts(List<Product> products) {
        System.out.println("\n=== All Products ===");
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(product -> {
                System.out.println("ID: " + product.getProductId() +
                        ", Name: " + product.getName() +
                        ", Price: $" + product.getPrice());
            });
        }
    }
}
