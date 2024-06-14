package org.example.finalprojectmyshop.user.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.role.models.entities.Role;
import org.example.finalprojectmyshop.user.models.entities.enums.Status;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;


// TODO
//    private Addresses

//  TODO
//    private wishList

//   TODO
//    private orders

//   TODO
//    private reviews

//   TODO
//    private warranties

    @ManyToMany
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private Status status;


    public User() {
        this.roles = new HashSet<>();
    }

    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }


}

