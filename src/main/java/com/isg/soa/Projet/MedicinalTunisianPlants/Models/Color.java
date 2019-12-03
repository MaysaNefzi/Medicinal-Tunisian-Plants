package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


public enum Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    public Color(@NotNull String color, Set<com.isg.soa.Projet.MedicinalTunisianPlants.Models.Plant> plant) {
        Color = color;
        Plant = plant;
    }

    @NotNull
    private String Color;
    //Red='red';
    @ManyToMany(mappedBy ="Colors")
    private Set<Plant> Plant= new HashSet<>();
}
