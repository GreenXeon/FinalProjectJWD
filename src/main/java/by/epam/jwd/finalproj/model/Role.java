package by.epam.jwd.finalproj.model;

public enum Role {
    GUEST(0),
    USER(1),
    ADMIN(2);

    private final int i;

    Role(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public static Role findRoleById(int i){
        for (Role role : Role.values()){
            if (role.i == i){
                return role;
            }
        }
        return GUEST;
    }

    public static Role findRoleByName(String name){
        for (Role role: Role.values()) {
            if (role.name().equalsIgnoreCase(name)){
                return role;
            }
        }
        return GUEST;
    }
}
