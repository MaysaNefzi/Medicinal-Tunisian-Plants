package com.isg.soa.Projet.MedicinalTunisianPlants.Models;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name="Customer_table")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Code;


    @NotNull
    private String Name;
    private String Type;
    private int Coeff_Fid;
    private Long Phone;
    private String Email;
    private String Login;
    private String Password;
    @NotNull
    private Profile profile;

    public Customer(@NotNull String name, Set<Order> orders , String type, int coeff_Fid, Long phone, String email, String login, String password, @NotNull Profile profil, Adress adress) {
        Name = name;
        Type = type;
        Coeff_Fid = coeff_Fid;
        Phone = phone;
        Email = email;
        Login = login;
        Password = password;
        profile = profil;
        this.adress = adress;
/****************************************/
        Orders = orders ;
/******************************************/
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Adress_Id" , referencedColumnName = "Id")
    private Adress adress;

    /***************/

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Order> Orders = new HashSet<>();
/*************************************/


    public String Fidelite (int Coeff){
        String S="Not Loyal";
        if (Coeff>=3) {
            S =  "Loyal";
        }
        return S;
    }
}
