package org.example.exerciceProduct.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String brand;
    private String reference;
    @Temporal(TemporalType.DATE)
    private LocalDate purchaseDate;
    private double price;
    private int stock;

    public Product() {

    }

    public Product(String brand, String reference, LocalDate purchaseDate, double price, int stock) {
        this.brand = brand;
        this.reference = reference;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", reference='" + reference + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
