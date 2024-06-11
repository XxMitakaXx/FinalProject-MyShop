package org.example.finalprojectmyshop.review.models.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_last_name")
    private String userLastName;

//  TODO
//   private userImage

    @Column(name = "rating")
    private int mark;

    @Column(name = "date")
    private Date date;


}
