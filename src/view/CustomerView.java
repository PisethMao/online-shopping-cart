package view;

import model.Customer;

import java.util.List;

public class CustomerView {
    public void displayCustomer(Customer customer) {
        if (customer != null) {
            System.out.println("\n========== CUSTOMER DETAILS ==========");
            System.out.println("ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("======================================\n");
        }
    }

    public void displayAllCustomers(List<Customer> customers) {
        System.out.println("\n========== AVAILABLE CUSTOMERS ==========");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.printf("%d. %-20s | Email: %-25s | Phone: %s%n",
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone());
            }
        }
        System.out.println("=========================================\n");
    }
}
