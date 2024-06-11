package org.example.finalprojectmyshop.sale.models.entities;

import jakarta.persistence.*;

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

//   TODO
//    private buyer

//   TODO
//    private product


}
