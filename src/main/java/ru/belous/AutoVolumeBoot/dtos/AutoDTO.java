package ru.belous.AutoVolumeBoot.dtos;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class AutoDTO {
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
}
