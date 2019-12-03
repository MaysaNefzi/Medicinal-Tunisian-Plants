package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
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

}

