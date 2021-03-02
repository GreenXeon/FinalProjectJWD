package by.epam.jwd.finalproj.model;

import java.sql.Timestamp;
import java.util.Objects;

public class UserDto {
    String login;
    String name;
    String surname;
    String email;
    Timestamp registrationDate;

    public UserDto(String login, String name, String surname, String email, Timestamp registrationDate) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
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
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
