package ru.belous.AutoVolumeBoot.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "не пустое")
    @Size(min = 4,max = 15,message = "длина логина должана быть от 4 до 15 символов")
    private String username;

    @Column(name = "year_of_birth")
    @Min(value = 1950, message = "год рождения не меньше 1950")
    @Max(value = 2005, message = "год рождения не больше 2005")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Auto> autos;

    @Column(name = "role")
    private String role;

    public Person(){}

    public Person(String username, int yearOfBirth) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString(){
        return "id: "+id+"\n"+
                "username: "+username+"\n"+
                "pass: "+password+"\n"+
                "date: "+yearOfBirth+"\n"+
                "role: "+role;
    }
}
