package by.epam.jwd.finalproj.model.user;

import by.epam.jwd.finalproj.model.Roles;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private BigDecimal cash;
    private Timestamp registrationDate;
    private boolean isBlocked;
    private Roles role;

    public User(int id, String login, String password, String name, String surname, String email, BigDecimal cash, Timestamp registrationDate, boolean isBlocked, Roles role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cash = cash;
        this.registrationDate = registrationDate;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    public User(){}

    public static class Builder{
        private User user;

        public Builder(){
            user = new User();
        }

        public Builder withId(int id){
            user.id = id;
            return this;
        }

        public Builder withLogin(String login){
            user.login = login;
            return this;
        }

        public Builder withPassword(String password){
            user.password = password;
            return this;
        }

        public Builder withName(String name){
            user.name = name;
            return this;
        }

        public Builder withSurname(String surname){
            user.surname = surname;
            return this;
        }

        public Builder withEmail(String email){
            user.email = email;
            return this;
        }

        public Builder withCash(BigDecimal cash){
            user.cash = cash;
            return this;
        }

        public Builder withRegistrationDate(Timestamp registrationDate){
            user.registrationDate = registrationDate;
            return this;
        }

        public Builder isBlocked(boolean isBlocked){
            user.isBlocked = isBlocked;
            return this;
        }

        public Builder withRole(Roles role){
            user.role = role;
            return this;
        }

        public User build(){
            return user;
        }
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
}
