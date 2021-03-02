package by.epam.jwd.finalproj.model;

import java.sql.Timestamp;

public class User {
    int id;
    String login;
    String password;
    String name;
    String surname;
    String email;
    Timestamp registrationDate;
    boolean isBlocked;
    Roles role;

    public User(int id, String login, String password, String name, String surname, String email, Timestamp registrationDate, boolean isBlocked, Roles role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.registrationDate = registrationDate;
        this.isBlocked = isBlocked;
        this.role = role;
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

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public Roles getRole() {
        return role;
    }
}
