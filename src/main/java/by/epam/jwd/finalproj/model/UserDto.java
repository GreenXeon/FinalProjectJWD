package by.epam.jwd.finalproj.model;

import java.sql.Timestamp;
import java.util.Objects;

public class UserDto {
    String login;
    String password;
    String name;
    String surname;
    String email;
    boolean isBlocked;
    Roles role;
    Timestamp registrationDate;

    public UserDto(String login, String password, String name, String surname, String email, Roles role, boolean isBlocked, Timestamp registrationDate) {
        this.login = login;
        this.password  = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public Roles getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDTO = (UserDto) o;
        return Objects.equals(login, userDTO.login) && Objects.equals(name, userDTO.name) && Objects.equals(surname, userDTO.surname) && Objects.equals(email, userDTO.email) && Objects.equals(registrationDate, userDTO.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, surname, email, registrationDate);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", isBlocked=" + isBlocked +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
