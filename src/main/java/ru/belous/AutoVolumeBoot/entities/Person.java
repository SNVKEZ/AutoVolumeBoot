package ru.belous.AutoVolumeBoot.entities;

import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
public class Person {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "не пустое")
    @Size(min = 2,max = 50,message = "от 2 до 50 символов")
    private String username;

    @Column(name = "year_of_birth")
    @Min(value = 1950,message = "Год рождения не меньше 1950")
    @Max(value = 2005,message = "Год рождения не больше 2005")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "created_ad")
    private LocalDateTime createdAt;

    @Column(name = "updated_ad")
    private LocalDateTime updatedAt;

    @Column(name = "created_who")
    @NotEmpty
    private String createdWho;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
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
