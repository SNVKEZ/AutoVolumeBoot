package ru.belous.AutoVolumeBoot.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthDTO {
    @NotEmpty(message = "не пустое")
    @Size(min = 2,max = 50,message = "от 2 до 50 символов")
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
