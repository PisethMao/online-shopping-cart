package service.impl;

import lombok.Getter;
import lombok.Setter;
import service.Payment;

@Getter
@Setter
public class DigitalWalletPayment implements Payment {
    private String walletId;

    public DigitalWalletPayment(String walletId) {
        this.walletId = walletId;
    }
    @Override
    public void processPayment() {
        System.out.println("Processing digital wallet payment");
        System.out.println("Wallet ID: " + walletId);
    }
}
