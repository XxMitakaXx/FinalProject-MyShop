package org.example.finalprojectmyshop.promotion.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.product.models.entities.Product;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @ManyToMany
    private List<Product> products;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public Promotion() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    // TODO check is it optimal
    public boolean isActive() {
        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        int i = now.compareTo(this.endDate);

        if (i == -1) {
            this.setActive(false);
        } else {
            this.setActive(true);
        }
        return this.isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
