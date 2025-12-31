package controller;

import model.Invoice;
import service.Payment;
import service.impl.BankTransferPayment;
import service.impl.CardPayment;
import service.impl.DigitalWalletPayment;

public class PaymentController {
    public void processPayment(Invoice invoice) {
        if (invoice != null && invoice.getPayment() != null) {
            System.out.println("\n=== Processing Payment ===");
            System.out.println("Invoice ID: " + invoice.getInvoiceId());
            System.out.println("Amount: $" + invoice.getTotalAmount());
            invoice.getPayment().processPayment();
            System.out.println("Payment successful!");
        }
    }

    public Payment createCardPayment(String cardNumber, String cardHolderName) {
        return new CardPayment(cardNumber, cardHolderName);
    }

    public Payment createDigitalWalletPayment(String walletId) {
        return new DigitalWalletPayment(walletId);
    }

    public Payment createBankTransferPayment(String accountNumber, String bankName) {
        return new BankTransferPayment(accountNumber, bankName);
    }
}
