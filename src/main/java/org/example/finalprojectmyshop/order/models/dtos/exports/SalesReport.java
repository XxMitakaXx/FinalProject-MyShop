package org.example.finalprojectmyshop.order.models.dtos.exports;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SalesReport {
    private int salesCount;
    private double incomeFromSales;
    private Set<SalesReportProduct> products;
    private Date date;

    public SalesReport() {
        this.products = new HashSet<>();
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

    public Set<SalesReportProduct> getProducts() {
        return this.products;
    }

    public void setProducts(Set<SalesReportProduct> products) {
        this.products = products;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
