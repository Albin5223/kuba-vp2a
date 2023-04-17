package Model;

public enum ModeJeu {
    NORMAL,
    IA,
    EDITION,
    DEFI,
    FUN;

    public static String[] name = {"NORMAL","IA","EDITION","DEFI","FUN"};

    public String toString(){
        return  name[this.ordinal()];
    }

    public ModeJeu next(){
        switch(this){
            case NORMAL : return IA;
            case IA : return EDITION;
            case EDITION : return DEFI;
            case DEFI : return FUN;
            default : return NORMAL;
        }
    }
}
