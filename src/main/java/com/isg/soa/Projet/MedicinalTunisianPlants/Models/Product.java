package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="Product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    public Product(@NotNull String name, @NotNull double price, Set<Order> orders) {
        Name = name;
        Price = price;
        Orders = orders;
    }

    @NotNull
    private String Name;
    @NotNull
    private double Price;
    @ManyToMany(mappedBy = "Products")
    private Set<Order>Orders = new HashSet<>();
}
