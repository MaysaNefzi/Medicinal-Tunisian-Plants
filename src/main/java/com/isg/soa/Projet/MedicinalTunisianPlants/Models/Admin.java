package com.isg.soa.Projet.MedicinalTunisianPlants.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admin_table")
public class Admin {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String firstName;
    private String login;
    private String password;

    public Admin(String name, String firstName, String login, String password) {
        this.name = name;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
