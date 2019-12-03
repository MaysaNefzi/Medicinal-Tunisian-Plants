package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name="Use_table")
public class Use {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    public Use(@NotNull String title, String guide, Set<Flower> flowerUsedIn, Set<Plant> plantUsedIn) {
        Title = title;
        Guide = guide;
        FlowerUsedIn = flowerUsedIn;
        PlantUsedIn = plantUsedIn;
    }

    @NotNull
    private String Title;
    private String Guide;
    @ManyToMany(mappedBy ="UsesOfFlower")
    private Set<Flower>FlowerUsedIn= new HashSet<>();
    @ManyToMany(mappedBy ="UsesOfPlant")
    private Set<Plant>PlantUsedIn= new HashSet<>();
}
