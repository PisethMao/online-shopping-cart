package controller;

import model.Customer;
import model.Product;
import model.ShoppingCart;

public class ShoppingCartController {
    public void addToCart(Customer customer, Product product, int quantity) {
        if (customer != null && product != null && quantity > 0) {
            customer.setCart(product, quantity);
            System.out.println("Added " + quantity + " x " + product.getName() + " to cart");
        }
    }

    public void removeFromCart(Customer customer, Product product) {
        if (customer != null && product != null) {
            customer.getCart().removeItem(product);
            System.out.println("Removed " + product.getName() + " from cart");
        }
    }

    public double getCartTotal(Customer customer) {
        if (customer != null) {
            return customer.getCart().getTotal();
        }
        return 0.0;
    }

    public void viewCart(Customer customer) {
        if (customer != null) {
            ShoppingCart cart = customer.getCart();
            System.out.println("\n=== Shopping Cart for " + customer.getName() + " ===");
            cart.getCartItems().forEach(item -> {
                System.out.println(item.getProduct().getName() + " x " + item.getQuantity() +
                        " = $" + item.getTotal());
            });
            System.out.println("Total: $" + cart.getTotal());
        }
    }
}
