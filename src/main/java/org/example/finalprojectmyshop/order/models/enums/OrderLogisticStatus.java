package org.example.finalprojectmyshop.order.models.enums;

public enum OrderLogisticStatus {
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    IN_OFFICE("In Office"),
    RECEIVED("Received");

    private String value;

    OrderLogisticStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
