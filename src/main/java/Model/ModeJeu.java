package Model;

public enum ModeJeu {
    NORMAL,
    IA,
    EDITION,
    DEFI,
    FUN,
    TUTO;

    public static String[] name = {"NORMAL","IA","EDITION","DEFI","FUN"};

    public String toString(){
        return  name[this.ordinal()];
    }

    public ModeJeu prev(){
        switch(this){
            case NORMAL : return FUN;
            case IA : return NORMAL;
            case EDITION : return IA;
            case DEFI : return EDITION;
            default : return DEFI;
        }
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
