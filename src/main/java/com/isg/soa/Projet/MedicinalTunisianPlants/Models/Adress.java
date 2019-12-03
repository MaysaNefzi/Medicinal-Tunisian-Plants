package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private @NotNull String City;
    private @NotNull Long  StreetNmbr;

    public Adress(@NotNull String city, @NotNull Long streetNmbr, String postalCode, Customer customer) {
        City = city;
        StreetNmbr = streetNmbr;
        PostalCode = postalCode;
        this.customer = customer;
    }

    private String PostalCode;
    @OneToOne(mappedBy = "adress")
    private Customer customer;


}
