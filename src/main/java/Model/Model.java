package Model;
import java.util.LinkedList;

import GUI.View;
import Model.*;


public class Model {
    Plateau plat;
    LinkedList<Joueur> joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
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

    public boolean isEnd(){
        return partieFinie;
    }

    public void push(Position p,Direction d){
        
        State state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());
        if(plat.isOver(joueurs.get(0),joueurs.get(1))==null){
            if(state != State.OPPREPLAY && state != State.REDREPLAY){
            joueurSuivant();
            }
        }
        else{
            partieFinie = true;
        }
        
        //view.update();
    }

    
}
