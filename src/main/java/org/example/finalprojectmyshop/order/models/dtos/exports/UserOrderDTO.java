package org.example.finalprojectmyshop.order.models.dtos.exports;

import org.example.finalprojectmyshop.order.models.enums.OrderLogisticStatus;

import java.util.Date;

public class UserOrderDTO {
    private long id;
    private double priceForSum;
    private Date orderDate;
    private Date deliveryDate;
    private OrderLogisticStatus logisticStatus;
    private boolean isPicked;

    public UserOrderDTO() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPriceForSum() {
        return this.priceForSum;
    }

    public void setPriceForSum(double priceForSum) {
        this.priceForSum = priceForSum;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderLogisticStatus getLogisticStatus() {
        return this.logisticStatus;
    }

    public void setLogisticStatus(OrderLogisticStatus logisticStatus) {
        this.logisticStatus = logisticStatus;
    }

    public boolean isPicked() {
        return this.logisticStatus == OrderLogisticStatus.RECEIVED;
    }
}
