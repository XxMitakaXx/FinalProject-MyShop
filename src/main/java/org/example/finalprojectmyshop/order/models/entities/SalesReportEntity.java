package org.example.finalprojectmyshop.order.models.entities;

import jakarta.persistence.*;
import org.example.finalprojectmyshop.product.models.entities.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sales_reports")
public class SalesReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sales_count", nullable = false)
    private int salesCount;

    @Column(name = "income_from_sales", nullable = false)
    private double incomeFromSales;

    @ManyToMany
    @JoinTable(
            name = "sales_reports_products",
            joinColumns = @JoinColumn(name = "sales_report_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @Column(nullable = false)
    private Date date;

    public SalesReportEntity() {
        this.products = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSalesCount() {
        return this.salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public double getIncomeFromSales() {
        return this.incomeFromSales;
    }

    public void setIncomeFromSales(double incomeFromSales) {
        this.incomeFromSales = incomeFromSales;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
