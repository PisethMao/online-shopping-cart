package model;

import lombok.*;

@Getter
@Setter
public class Product {
    private Integer productId;
    private String name;
    private double price;
    private String description;
    private Address address;

    public Product(Integer productId, String name, double price, String description, Address address) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.address = address;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
