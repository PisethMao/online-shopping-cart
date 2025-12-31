package service.impl;

import lombok.Getter;
import lombok.Setter;
import service.Payment;

@Getter
@Setter
public class BankTransferPayment implements Payment {
    private String bankAccountNumber;
    private String bankName;

    public BankTransferPayment(String bankAccountNumber, String bankName) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }
    @Override
    public void processPayment() {
        System.out.println("Processing bank transfer payment");
        System.out.println("Bank: " + bankName);
        System.out.println("Account: *****" + bankAccountNumber.substring(bankAccountNumber.length() - 4));
    }
}
