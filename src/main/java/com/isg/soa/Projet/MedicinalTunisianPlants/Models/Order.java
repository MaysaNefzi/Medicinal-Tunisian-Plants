package com.isg.soa.Projet.MedicinalTunisianPlants.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Code;
    private @NotNull LocalDate Date;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Order_Flower_Line",
            joinColumns =
            @JoinColumn (name = "Order_Code", referencedColumnName = "Code"),
            inverseJoinColumns =
            @JoinColumn (name = "Flower_Code", referencedColumnName = "Id")
    )
    private Set<Flower> Flowers=new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Order_Plant_Line",
            joinColumns =
            @JoinColumn (name = "Order_Code", referencedColumnName = "Code"),
            inverseJoinColumns =
            @JoinColumn (name = "Plant_Code", referencedColumnName = "Id")
    )
    private Set<Plant> Plants=new HashSet<>();
    @ManyToOne
    @JoinColumn(name="Customer_Code")
    private Customer customer;

    public Order(@NotNull LocalDate date, Set<Flower> flowers, Set<Plant> plants, Customer customer) {
        Date = date;
        Flowers = flowers;
        Plants = plants;
        this.customer = customer;
    }

    public Order() {
    }

    public Long getCode() {
        return Code;
    }

    public void setCode(Long code) {
        Code = code;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public Set<Flower> getFlowers() {
        return Flowers;
    }

    public void setFlowers(Set<Flower> flowers) {
        Flowers = flowers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Plant> getPlants() {
        return Plants;
    }

    public void setPlants(Set<Plant> plants) {
        Plants = plants;
    }
}

