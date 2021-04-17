package by.epam.jwd.finalproj.model.user;

import by.epam.jwd.finalproj.model.Role;

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
    private Role role;

    public User(int id, String login, String password, String name, String surname, String email, BigDecimal cash, Timestamp registrationDate, boolean isBlocked, Role role) {
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

        public Builder withRole(Role role){
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

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isBlocked != user.isBlocked) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (cash != null ? !cash.equals(user.cash) : user.cash != null) return false;
        if (registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null)
            return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cash=" + cash +
                ", registrationDate=" + registrationDate +
                ", isBlocked=" + isBlocked +
                ", role=" + role +
                '}';
    }
}
