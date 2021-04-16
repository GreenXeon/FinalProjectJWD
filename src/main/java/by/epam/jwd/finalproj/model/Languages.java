package by.epam.jwd.finalproj.model;

public enum Languages {
    en(1),
    ru(2),
    iw(3);

    private final int i;

    public int getI() {
        return i;
    }

    Languages(int i) {
        this.i = i;
    }

    public static String getLangById(int id){
        for(Languages lang : values()){
            if(lang.i == id){
                return lang.name();
            }
        }
        return en.name();
    }
}
