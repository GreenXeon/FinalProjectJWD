package by.epam.jwd.finalproj.model.user;

import by.epam.jwd.finalproj.model.Role;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserDto implements Serializable {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private BigDecimal cash;
    private boolean isBlocked;
    private Role role;
    private Timestamp registrationDate;

    public UserDto(int id, String login, String password, String name, String surname, String email, BigDecimal cash, Role role, boolean isBlocked, Timestamp registrationDate) {
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

    public UserDto(){}

    public static class Builder{
        private UserDto userDto;

        public Builder(){
            userDto = new UserDto();
        }

        public Builder withId(int id){
            userDto.id = id;
            return this;
        }

        public Builder withLogin(String login){
            userDto.login = login;
            return this;
        }

        public Builder withPassword(String password){
            userDto.password = password;
            return this;
        }

        public Builder withName(String name){
            userDto.name = name;
            return this;
        }

        public Builder withSurname(String surname){
            userDto.surname = surname;
            return this;
        }

        public Builder withEmail(String email){
            userDto.email = email;
            return this;
        }

        public Builder withCash(BigDecimal cash){
            userDto.cash = cash;
            return this;
        }

        public Builder withRegistrationDate(Timestamp registrationDate){
            userDto.registrationDate = registrationDate;
            return this;
        }

        public Builder isBlocked(boolean isBlocked){
            userDto.isBlocked = isBlocked;
            return this;
        }

        public Builder withRole(Role role){
            userDto.role = role;
            return this;
        }

        public UserDto build(){
            return userDto;
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

        UserDto userDto = (UserDto) o;

        if (id != userDto.id) return false;
        if (isBlocked != userDto.isBlocked) return false;
        if (login != null ? !login.equals(userDto.login) : userDto.login != null) return false;
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) return false;
        if (name != null ? !name.equals(userDto.name) : userDto.name != null) return false;
        if (surname != null ? !surname.equals(userDto.surname) : userDto.surname != null) return false;
        if (email != null ? !email.equals(userDto.email) : userDto.email != null) return false;
        if (cash != null ? !cash.equals(userDto.cash) : userDto.cash != null) return false;
        if (role != userDto.role) return false;
        return registrationDate != null ? registrationDate.equals(userDto.registrationDate) : userDto.registrationDate == null;
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
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
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
