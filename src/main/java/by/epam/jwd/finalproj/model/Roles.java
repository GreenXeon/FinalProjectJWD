package by.epam.jwd.finalproj.model;

import by.epam.jwd.finalproj.command.CommandManager;

public enum Roles {
    GUEST(0),
    USER(1),
    ADMIN(2);

    private final int i;

    Roles(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public static Roles findRoleById(int i){
        for (Roles role : Roles.values()){
            if (role.i == i){
                return role;
            }
        }
        return null; //todo: logging error
    }

    public static Roles findRoleByName(String name){
        for (Roles role: Roles.values()) {
            if (role.name().equalsIgnoreCase(name)){
                return role;
            }
        }
        return GUEST;
    }
}
