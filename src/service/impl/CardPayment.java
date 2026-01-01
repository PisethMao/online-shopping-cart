package service.impl;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import service.Payment;

@Getter
@Setter
public class CardPayment implements Payment {
    private String cardNumber;
    private String cardHolderName;

    public CardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    @Override
    public void processPayment() {
        System.out.println("Processing card payment for: " + cardHolderName);
        System.out.println("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
    }
    
}
