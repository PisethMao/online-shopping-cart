package view;

import model.Product;

import java.util.List;

public class ProductView {
    public void displayProduct(Product product) {
        if (product != null) {
            System.out.println("\n========== PRODUCT DETAILS ==========");
            System.out.println("ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.printf("Price: $%.2f%n", product.getPrice());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Location: " + product.getAddress().getFullAddress());
            System.out.println("=====================================\n");
        }
    }

    public void displayAllProducts(List<Product> products) {
        System.out.println("\n==================== PRODUCT CATALOG ====================");
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.printf("%d. %-30s | $%-8.2f | %s%n",
                        product.getProductId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDescription());
            }
        }
        System.out.println("=========================================================\n");
    }
}
