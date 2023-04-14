package ru.belous.AutoVolumeBoot.dtos;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "не пустое")
    @Size(min = 2,max = 50,message = "от 2 до 50 символов")
    private String username;

    @Min(value = 1950,message = "Год рождения не меньше 1950")
    @Max(value = 2005,message = "Год рождения не больше 2005")
    private int yearOfBirth;


    private String role;

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


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
