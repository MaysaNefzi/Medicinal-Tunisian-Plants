package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Code;
    private @NotNull LocalDate Date;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Order_Line",
            joinColumns =
            @JoinColumn (name = "Order_Code", referencedColumnName = "Code"),
            inverseJoinColumns =
            @JoinColumn (name = "Product_Code", referencedColumnName = "Id")
    )
    private Set<Product> Products=new HashSet<>();

    public Order(@NotNull LocalDate date, Set<Product> products) {
        Date = date;
        Products = products;
    }
}

