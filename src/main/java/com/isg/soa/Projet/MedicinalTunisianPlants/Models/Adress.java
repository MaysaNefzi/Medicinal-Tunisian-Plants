package com.isg.soa.Projet.MedicinalTunisianPlants.Models;




import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Customer_Adress")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private @NotNull String City;
    private @NotNull Long  StreetNumber;
    private String PostalCode;
    @OneToMany(mappedBy = "adress")
    private Set<Customer> customer;
    public Adress() {
    }

    public Adress(@NotNull String city, @NotNull Long streetNumber, String postalCode, Set<Customer> customer) {
        City = city;
        StreetNumber = streetNumber;
        PostalCode = postalCode;
        this.customer = customer;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Long getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(Long streetNumber) {
        StreetNumber = streetNumber;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public Set<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customer = customer;
    }
}
