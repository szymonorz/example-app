package pl.example.app.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 256, message = "Password length must be between 8 and 256 characters")
    private String password;

    /*
        Settery i gettery MUSZĄ istnieć ponieważ inaczej Thymeleaf i Hibernate nie wiedzą
        co mają ze sobą zrobić i wrzucają wszędzie null.

        Alternatywnie można zrobić wszystkie pola publiczne.
     */
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
