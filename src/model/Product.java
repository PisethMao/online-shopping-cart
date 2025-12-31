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

}
