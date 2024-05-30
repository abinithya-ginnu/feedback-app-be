package com.ict.feedbackappbe.models;

import javax.persistence.*;

enum Profession {
    PARTICIPANT,
    IQA,
    TRAINER;
}
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    Profession profession;

    public UserModel() {
    }

    public UserModel(int id, String name, String email, String password, Profession profession) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profession = profession;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
