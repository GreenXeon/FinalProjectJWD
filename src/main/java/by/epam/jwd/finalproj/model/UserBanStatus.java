package by.epam.jwd.finalproj.model;

public enum UserBanStatus {
    BANNED(1),
    UNBANNED(0);

    private final int i;

    UserBanStatus(int i){
        this.i = i;
    }

    public int getI() {
        return i;
    }

}
