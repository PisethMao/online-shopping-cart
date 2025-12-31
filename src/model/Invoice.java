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
    private int invoiceId;
    private LocalDate issueDate;
    private double totalAmount;
    private Payment payment;

    public Invoice(int invoiceId, LocalDate issueDate, double totalAmount) {
        this.invoiceId = invoiceId;
        this.issueDate = issueDate;
        this.totalAmount = totalAmount;
    }



    // Getters and Setters
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public void setInvoice(Order order) {
        this.totalAmount = order.getTotalAmount();
        System.out.println("Invoice " + invoiceId + " generated for order " + order.getOrderId());
    }

}
