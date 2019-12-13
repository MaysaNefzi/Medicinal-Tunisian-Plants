package com.isg.soa.Projet.MedicinalTunisianPlants.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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
    private Profile Profile;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Adress_Id")
    private Adress adress;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Order> Orders = new HashSet<>();

    public Customer(@NotNull String name, String type, int coeff_Fid, Long phone, String email, String login, String password, com.isg.soa.Projet.MedicinalTunisianPlants.Models.@NotNull Profile profile, Adress adress, Set<Order> orders) {
        Name = name;
        Type = type;
        Coeff_Fid = coeff_Fid;
        Phone = phone;
        Email = email;
        Login = login;
        Password = password;
        Profile = profile;
        this.adress = adress;
        Orders = orders;
    }

    public Customer() {
    }
    public String Fidelite (int Coeff){
        String S="Not Loyal";
        if (Coeff>=3) {
            S =  "Loyal";
        }
        return S;
    }

    public Long getCode() {
        return Code;
    }

    public void setCode(Long code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getCoeff_Fid() {
        return Coeff_Fid;
    }

    public void setCoeff_Fid(int coeff_Fid) {
        Coeff_Fid = coeff_Fid;
    }

    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Profile getProfile() {
        return Profile;
    }

    public void setProfile(Profile profile) {
        Profile = profile;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
