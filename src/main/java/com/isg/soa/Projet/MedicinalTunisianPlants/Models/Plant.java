package com.isg.soa.Projet.MedicinalTunisianPlants.Models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="Plant_table")
public class Plant extends Product {
    private String Origin;
    private String Description;

    public Plant(@NotNull String name, @NotNull double price, Set<Order> orders, String origin, String description, Set<Photo> photos, Set<Use> usesOfPlant, Flower flower, Set<Color> colors) {
        super(name, price, orders);
        Origin = origin;
        Description = description;
        Photos = photos;
        UsesOfPlant = usesOfPlant;
        this.flower = flower;
        Colors = colors;
    }


    @OneToMany(mappedBy = "plant",cascade = CascadeType.ALL)
    private Set<Photo> Photos = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Plant_Used_In",
            joinColumns =
            @JoinColumn(name="Plant_Id" , referencedColumnName = "Id"),
            inverseJoinColumns =
            @JoinColumn(name="Use_Id", referencedColumnName="Id")
    )
    private Set<Use>UsesOfPlant = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Flower_P", referencedColumnName = "Id")
    private Flower flower;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Plant_Color",
            joinColumns =
            @JoinColumn(name="Plant_Id" , referencedColumnName = "Id"),
            inverseJoinColumns =
            @JoinColumn(name="Color_Id", referencedColumnName="Id")
    )
    private Set<Color>Colors=new HashSet<>();
}
