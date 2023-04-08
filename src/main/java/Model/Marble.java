package Model;

import java.util.Random;

public class Marble implements Cloneable {
    
    Colour couleur;
    Power power;

    public Marble(Colour c,Power p){
        couleur = c;
        power = p;
    }

    public Marble(Colour c){
        this(c, Power.NORMAL);
    }

    public Marble(){
        this(null);
    }

    public static boolean equalsColour(Marble b1,Marble b2){
        return b1.couleur == b2.couleur;
    }

    public boolean isColour(Colour c){
        return this.couleur == c;
    }

    public void reset(){
        couleur = null;
    }

    public Colour getColour(){
        return couleur;
    }

    public Power getPower(){
        return power;
    }

    public void setColor(Colour c){
        couleur = c;
    }

    public void setPower(Power p){
        power = p;
    }

    public Marble clone(){
        Marble m = new Marble(couleur, power);
        return m;
    }

    public boolean setRedMarblePower(){
        Random r = new Random();
        int x = r.nextInt(0, 5);
        if(couleur == Colour.RED && x==0){
            setPower(Power.UNMOVABLE);
            return true;
        }
        return false;
    }
}
