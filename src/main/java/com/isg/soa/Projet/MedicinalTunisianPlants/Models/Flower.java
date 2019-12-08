package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
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


    @OneToMany(mappedBy = "flower",cascade = CascadeType.ALL)
    private Set<Photo> Photos = new HashSet<>();

    public Flower(@NotNull String name, @NotNull double price, Set<Photo> photos,Set<Order> orders, String smell, Plant plant, Set<Use> usesOfFlower) {
        super(name, price, orders);
        Smell = smell;
        this.plant = plant;
        UsesOfFlower = usesOfFlower;
        Photos = photos;
    }
}
