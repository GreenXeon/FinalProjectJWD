package by.epam.jwd.finalproj.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class UserDto {
    int id;
    String login;
    String password;
    String name;
    String surname;
    String email;
    BigDecimal cash;
    boolean isBlocked;
    Roles role;
    Timestamp registrationDate;

    public UserDto(int id, String login, String password, String name, String surname, String email, BigDecimal cash, Roles role, boolean isBlocked, Timestamp registrationDate) {
        this.id = id;
        this.login = login;
        this.password  = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cash = cash;
        this.isBlocked = isBlocked;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
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

    public BigDecimal getCash() {
        return cash;
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
        UserDto userDto = (UserDto) o;
        return id == userDto.id && isBlocked == userDto.isBlocked && Objects.equals(login, userDto.login) && Objects.equals(password, userDto.password) && Objects.equals(name, userDto.name) && Objects.equals(surname, userDto.surname) && Objects.equals(email, userDto.email) && Objects.equals(cash, userDto.cash) && role == userDto.role && Objects.equals(registrationDate, userDto.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, surname, email, cash, isBlocked, role, registrationDate);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cash=" + cash +
                ", isBlocked=" + isBlocked +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
