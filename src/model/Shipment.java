package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Shipment {
    // Getters and Setters
    private int shipmentId;
    private String deliveryStatus;
    private String trackingNumber;

    public Shipment(int shipmentId, String trackingNumber) {
        this.shipmentId = shipmentId;
        this.trackingNumber = trackingNumber;
        this.deliveryStatus = "PROCESSING";
    }

    public void setStatus(String status) {
        this.deliveryStatus = status;
        System.out.println("Shipment " + shipmentId + " status updated to: " + status);
    }

}
