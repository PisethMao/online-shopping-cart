package model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor


public class Address {
    private String street;
    private String city;
    private String postalCode;

    public Address(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getFullAddress() {
        return street + ", " + city + " " + postalCode;
    }

}
