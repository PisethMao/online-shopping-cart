package controller;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers;
    private int nextCustomerId;

    public CustomerController() {
        this.customers = new ArrayList<>();
        this.nextCustomerId = 1;
    }

    public Customer createCustomer(String name, String email, String phone) {
        Customer customer = new Customer(nextCustomerId++, name, email, phone);
        customers.add(customer);
        System.out.println("Customer created: " + customer.getName());
        return customer;
    }

    public Customer getCustomerById(int customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void updateCustomer(int customerId, String name, String email, String phone) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            System.out.println("Customer updated: " + customer.getName());
        }
    }

    public void deleteCustomer(int customerId) {
        customers.removeIf(c -> c.getCustomerId() == customerId);
        System.out.println("Customer deleted with ID: " + customerId);
    }
}
