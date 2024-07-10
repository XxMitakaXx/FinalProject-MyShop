package org.example.finalprojectmyshop.user.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.order.models.entities.Address;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.user.models.entities.enums.Status;
import org.example.finalprojectmyshop.product.models.entities.Warranty;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToMany()
    private Set<Address> addresses;

    @ManyToMany
    private Set<Product> wishlist;

    @OneToMany
    private Set<Order> orders;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany
    private Set<Warranty> warranties;

    @ManyToMany
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private MediaFile profilePicture;

    public User() {
        this.addresses = new HashSet<>();
        this.wishlist = new HashSet<>();
        this.roles = new HashSet<>();
        this.reviews = new HashSet<>();
        this.warranties = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Product> getWishlist() {
        return this.wishlist;
    }

    public void setWishlist(Set<Product> wishlist) {
        this.wishlist = wishlist;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Warranty> getWarranties() {
        return this.warranties;
    }

    public void setWarranties(Set<Warranty> warranties) {
        this.warranties = warranties;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MediaFile getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(MediaFile profilePicture) {
        this.profilePicture = profilePicture;
    }
}

