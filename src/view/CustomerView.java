package view;

import model.Customer;

import java.util.List;

public class CustomerView {
    public void displayCustomer(Customer customer) {
        if (customer != null) {
            System.out.println("\n=== Customer Details ===");
            System.out.println("ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
        }
    }

    public void displayAllCustomers(List<Customer> customers) {
        System.out.println("\n=== All Customers ===");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(customer -> {
                System.out.println("ID: " + customer.getCustomerId() +
                        ", Name: " + customer.getName() +
                        ", Email: " + customer.getEmail());
            });
        }
    }
}
