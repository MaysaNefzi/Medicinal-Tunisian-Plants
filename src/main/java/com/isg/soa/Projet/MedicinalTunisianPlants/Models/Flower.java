package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Flower_table")
public class Flower{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String Name;
    @NotNull
    private double Price;
    @ManyToMany(mappedBy = "Flowers")
    private Set<Order>Orders = new HashSet<>();
    private String Smell;
    @OneToOne(mappedBy = "flower")
    private Plant plant;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Flower_Used_In",
            joinColumns =
            @JoinColumn(name="Flower_Id" , referencedColumnName = "Id"),
            inverseJoinColumns =
            @JoinColumn(name="Use_Id", referencedColumnName="Id")
    )
    private Set<Use>UsesOfFlower = new HashSet<>();
    @OneToMany(mappedBy = "flower",cascade = CascadeType.ALL)
    private Set<Photo> Photos = new HashSet<>();

    public Flower( @NotNull String name, @NotNull double price, Set<Order> orders, String smell, Plant plant, Set<Use> usesOfFlower, Set<Photo> photos) {

        Name = name;
        Price = price;
        Orders = orders;
        Smell = smell;
        this.plant = plant;
        UsesOfFlower = usesOfFlower;
        Photos = photos;
    }

    public Flower() {
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

    public String getSmell() {
        return Smell;
    }

    public void setSmell(String smell) {
        Smell = smell;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Set<Use> getUsesOfFlower() {
        return UsesOfFlower;
    }

    public void setUsesOfFlower(Set<Use> usesOfFlower) {
        UsesOfFlower = usesOfFlower;
    }

    public Set<Photo> getPhotos() {
        return Photos;
    }

    public void setPhotos(Set<Photo> photos) {
        Photos = photos;
    }
}