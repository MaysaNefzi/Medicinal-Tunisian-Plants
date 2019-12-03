package com.isg.soa.Projet.MedicinalTunisianPlants.Models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="Product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String Name;
    @NotNull
    private double Price;
    @ManyToMany(mappedBy = "Products")
    private Set<Order>Orders = new HashSet<>();

    public Product() {
    }

    public Product(@NotNull String name, @NotNull double price, Set<Order> orders) {
        Name = name;
        Price = price;
        Orders = orders;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Set<Order> getOrders() {
        return Orders;
    }

    public void setOrders(Set<Order> orders) {
        Orders = orders;
    }
}
