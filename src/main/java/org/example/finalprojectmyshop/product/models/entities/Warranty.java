package org.example.finalprojectmyshop.product.models.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "warranties")
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "is_active")
    private boolean isActive;

    public Warranty() {}

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        Timestamp now = new Timestamp(System.currentTimeMillis());
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
