package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Flower_table")
public class Flower extends Product{
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

    public Flower(@NotNull String name, @NotNull double price, Set<Order> orders, String smell, Plant plant, Set<Use> usesOfFlower) {
        super(name, price, orders);
        Smell = smell;
        this.plant = plant;
        UsesOfFlower = usesOfFlower;
    }

    public Flower(@NotNull String name, @NotNull double price, Set<Order> orders) {
        super(name, price, orders);
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
}
