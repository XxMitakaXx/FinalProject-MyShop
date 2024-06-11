package org.example.finalprojectmyshop.warranty.models.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "warranties")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//  TODO
//    private product

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    // TODO to be calculated to depend dates
    @Column(name = "is_active")
    private boolean isActive;

    public Warranty() {}

    public Warranty(Date startDate, Date endDate, boolean isActive) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
}
