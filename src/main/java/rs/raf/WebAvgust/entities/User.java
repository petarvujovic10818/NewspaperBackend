package rs.raf.WebAvgust.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull(message = "Email field is required")
    @NotEmpty(message = "Email field is required")
    private String email;
    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name field is required")
    private String name;
    @NotNull(message = "Surname field is required")
    @NotEmpty(message = "Surname field is required")
    private String surname;
    @NotNull(message = "Type field is required")
    @NotEmpty(message = "Type field is required")
    private String tip;
    @NotNull(message = "Status field is required")
    @NotEmpty(message = "Status field is required")
    private String status;
    @NotNull(message = "Password field is required")
    @NotEmpty(message = "Password field is required")
    private String password;

    public User(){

    }

    public User(String email, String name, String surname, String tip, String status, String password){
        this.email=email;
        this.name=name;
        this.surname=surname;
        this.tip=tip;
        this.status=status;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
