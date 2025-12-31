package model;

import lombok.*;
import service.Payment;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    // Getters and Setters
    private int invoiceId;
    private LocalDate issueDate;
    private double totalAmount;
    private Payment payment;

    public Invoice(int invoiceId, LocalDate issueDate, double totalAmount) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
        this.totalAmount = totalAmount;
    }


    public void setInvoice(Order order) {
        this.totalAmount = order.getTotalAmount();
        System.out.println("Invoice " + invoiceId + " generated for order " + order.getOrderId());
    }

}
