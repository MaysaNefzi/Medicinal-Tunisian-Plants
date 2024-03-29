package com.isg.soa.Projet.MedicinalTunisianPlants.Models;




import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
@Entity
@Table(name="Plant_table")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    private String Name;
    @NotNull
    private double Price;
    @ManyToMany(mappedBy = "Plants")
    private Set<Order>Orders = new HashSet<>();
    private String Origin;
    private String Description;
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
    private Color Color ;

    public Plant( @NotNull String name, @NotNull double price, Set<Order> orders, String origin, String description, Set<Photo> photos, Set<Use> usesOfPlant, Flower flower, com.isg.soa.Projet.MedicinalTunisianPlants.Models.Color color) {

        Name = name;
        Price = price;
        Orders = orders;
        Origin = origin;
        Description = description;
        Photos = photos;
        UsesOfPlant = usesOfPlant;
        this.flower = flower;
        Color = color;
    }

    public Plant() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Set<Order> getOrders() {
        return Orders;
    }

    public void setOrders(Set<Order> orders) {
        Orders = orders;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Set<Photo> getPhotos() {
        return Photos;
    }

    public void setPhotos(Set<Photo> photos) {
        Photos = photos;
    }

    public Set<Use> getUsesOfPlant() {
        return UsesOfPlant;
    }

    public void setUsesOfPlant(Set<Use> usesOfPlant) {
        UsesOfPlant = usesOfPlant;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public com.isg.soa.Projet.MedicinalTunisianPlants.Models.Color getColor() {
        return Color;
    }

    public void setColor(com.isg.soa.Projet.MedicinalTunisianPlants.Models.Color color) {
        Color = color;
    }
}