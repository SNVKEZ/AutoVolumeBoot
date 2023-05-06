package ru.belous.AutoVolumeBoot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "auto")
public class Auto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    @NotEmpty(message = "заполните марку")
    private String brand;

    @Column(name = "model")
    @NotEmpty(message = "заполните модель")
    private String model;

    @Column(name = "year_of_issue")
    @NotEmpty(message = "заполните год выпуска")
    @Pattern(regexp = "[1-2][09][0-9][0-9]")
    private String yearOfIssue;

    @Column(name = "color")
    @NotNull
    private String color;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;

    public Auto() {
    }

    public Auto(int id, String brand, String model, String yearOfIssue, Person person) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.yearOfIssue = yearOfIssue;
        this.owner = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
