package com.isg.soa.Projet.MedicinalTunisianPlants.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Photo_table")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String Photo;
    @ManyToOne
    @JoinColumn(name="Plant_id")
    private Plant plant;
    @ManyToOne
    @JoinColumn(name="Flower_id")
    private Flower flower ;

    public Photo(Long id, @NotNull String photo, Plant plant) {
        Id = id;
        Photo = photo;
        this.plant = plant;
    }

    public Photo() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}

