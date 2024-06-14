package org.example.finalprojectmyshop.sale.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.user.models.entities.User;

import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private User buyer;

//   TODO
//    private product


}
