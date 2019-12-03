package com.isg.soa.Projet.MedicinalTunisianPlants.Models;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Use_table")
public class Use {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String Title;
    private String Guide;
    @ManyToMany(mappedBy ="UsesOfFlower")
    private Set<Flower>FlowerUsedIn= new HashSet<>();
    @ManyToMany(mappedBy ="UsesOfPlant")
    private Set<Plant>PlantUsedIn= new HashSet<>();

    public Use() {
    }

    public Use(@NotNull String title, String guide, Set<Flower> flowerUsedIn, Set<Plant> plantUsedIn) {
        Title = title;
        Guide = guide;
        FlowerUsedIn = flowerUsedIn;
        PlantUsedIn = plantUsedIn;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGuide() {
        return Guide;
    }

    public void setGuide(String guide) {
        Guide = guide;
    }

    public Set<Flower> getFlowerUsedIn() {
        return FlowerUsedIn;
    }

    public void setFlowerUsedIn(Set<Flower> flowerUsedIn) {
        FlowerUsedIn = flowerUsedIn;
    }

    public Set<Plant> getPlantUsedIn() {
        return PlantUsedIn;
    }

    public void setPlantUsedIn(Set<Plant> plantUsedIn) {
        PlantUsedIn = plantUsedIn;
    }
}
