package controller;

import model.Invoice;
import service.Payment;
import service.impl.BankTransferPayment;
import service.impl.CardPayment;
import service.impl.DigitalWalletPayment;

public class PaymentController {
    public void processPayment(Invoice invoice) {
        if (invoice != null && invoice.getPayment() != null) {
            System.out.println("\n========== PAYMENT PROCESSING ==========");
            System.out.println("Invoice ID: " + invoice.getInvoiceId());
            System.out.printf("Amount: $%.2f%n", invoice.getTotalAmount());
            invoice.getPayment().processPayment();
            System.out.println("✓ Payment successful!");
            System.out.println("========================================\n");
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
