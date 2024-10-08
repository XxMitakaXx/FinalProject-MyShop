package org.example.finalprojectmyshop.user.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.order.models.entities.Address;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.user.models.entities.enums.Status;
import org.example.finalprojectmyshop.product.models.entities.Warranty;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
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
    private LocalDate birthdate;

    @ManyToMany
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )private Set<Address> addresses;

    @OneToOne
    private CartEntity cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id")
    )
    private Set<Product> favorites;

    @OneToMany(mappedBy = "buyer")
    private Set<Order> orders;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany
    private Set<Warranty> warranties;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRoleEntity> userRoleEntities;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private MediaFileEntity profilePicture;

    public UserEntity() {
        this.addresses = new HashSet<>();
        this.favorites = new HashSet<>();
        this.userRoleEntities = new HashSet<>();
        this.reviews = new HashSet<>();
        this.warranties = new HashSet<>();
    }

    public UserEntity(String firstName, String lastName, String email, String password, String phoneNumber, LocalDate birthdate) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
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

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthDate) {
        this.birthdate = birthDate;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public CartEntity getCart() {
        return this.cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public Set<UserRoleEntity> getUserRoleEntities() {
        return userRoleEntities;
    }

    public void setUserRoleEntities(Set<UserRoleEntity> userRoleEntities) {
        this.userRoleEntities = userRoleEntities;
    }

    public Set<Product> getFavorites() {
        return this.favorites;
    }

    public void setFavorites(Set<Product> wishlist) {
        this.favorites = wishlist;
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

    public Set<UserRoleEntity> getRoles() {
        return this.userRoleEntities;
    }

    public void setRoles(Set<UserRoleEntity> userRoleEntities) {
        this.userRoleEntities = userRoleEntities;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MediaFileEntity getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(MediaFileEntity profilePicture) {
        this.profilePicture = profilePicture;
    }
}

