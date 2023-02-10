package main.java.MVC1;
import java.util.LinkedList;
import main.java.Model1.*;


public class Model {
    Plateau plat;
    LinkedList<Joueur> joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    View view;
    


    public Model(int n,String s1,String s2){
        joueurs = new LinkedList<>();
        plat = new Plateau(n);
        Joueur j1 = new Joueur(Color.BLACK,n,s1);
        Joueur j2 = new Joueur(Color.WHITE,n,s2);
        plat.initialiseBille();

        joueurs.add(j1);
        joueurs.add(j2);
    }

    public void setView(View v){
        view = v;
    }

    public Joueur getCurrentPlayer(){
        return joueurs.get(joueurCurrent);
    }

    public Joueur getOtherPlayer(){
        if (joueurCurrent==0){
            return joueurs.get(1);
        }
        return joueurs.get(0);
    }

    public void joueurSuivant(){
        joueurCurrent ++;
        if (joueurCurrent>=joueurs.size()){
            joueurCurrent = 0;
        }
    }

    public State push(Position p,Direction d){
        
        return plat.push(p, d, getCurrentPlayer(), getOtherPlayer());
        //view.update();
    }

    
}
