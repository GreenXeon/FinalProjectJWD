package by.epam.jwd.finalproj.model;

public enum Roles {
    USER(1),
    ADMIN(2);

    private final int i;

    Roles(int i) {
        this.i = i;
    }

    public static Roles findRoleById(int i){
        for (Roles role : Roles.values()){
            if (role.i == i){
                return role;
            }
        }
        return null; //todo: logging error
    }
}
