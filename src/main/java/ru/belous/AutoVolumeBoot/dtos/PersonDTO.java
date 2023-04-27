package ru.belous.AutoVolumeBoot.dtos;

import javax.validation.constraints.*;

public class PersonDTO {

    @NotEmpty(message = "не пустое")
    @NotNull
    @Size(min = 4,max = 50,message = "от 2 до 50 символов")
    private String username;

    @Min(value = 1950,message = "Год рождения не меньше 1950")
    @Max(value = 2005,message = "Год рождения не больше 2005")
    private int yearOfBirth;

    private String password;

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
}
