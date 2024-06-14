package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.order.models.entities.enums.OrderStatus;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

//   TODO
//    private buyer;

//   TODO
//    private product

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "is_picked")
    private boolean isPicked;

    public Order() {}

    public Order(Date date, String address, String description, OrderStatus status, boolean isPicked) {
        this.date = date;
        this.address = address;
        this.description = description;
        this.status = status;
        this.isPicked = isPicked;
    }
}
