package Model;

import GUI.View;

import java.util.LinkedList;


public class Model implements Observé<Data> {
    Plateau plat;
    Joueur[] joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
    View view;

    LinkedList<Observeur<Data>> obsList;

    public Model(int n){
        obsList=new LinkedList<>();
        joueurs = new Joueur[2];
        plat = new Plateau(n);
        Joueur j1 = new Joueur(Colour.BLACK,n);
        Joueur j2 = new Joueur(Colour.WHITE,n);
        plat.initialiseBille();

        joueurs[1] = j1;
        joueurs[2] = j2;
    }

    public void setView(View v){
        view = v;
    }

    public Joueur getCurrentPlayer(){
        return joueurs[joueurCurrent];
    }

    public Joueur getOtherPlayer(){
        if (joueurCurrent==0){
            return joueurs[1];
        }
        return joueurs[0];
    }

    public void joueurSuivant(){
        joueurCurrent ++;
        if (joueurCurrent>=2){
            joueurCurrent = 0;
        }
    }

    public boolean isEnd(){
        return partieFinie;
    }

    public void push(Position p,Direction d){

        
        State state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());
        if(plat.isOver(joueurs[0],joueurs[1])==null){
            if(state != State.PUSHOPPMARBLE && state != State.PUSHREDMARBLE){
            joueurSuivant();
            }
        }
        else{
            partieFinie = true;
        }
        
        //view.update();
    }

    public void addObserveur(Observeur<Data> obs) {
        obsList.add(obs);
    }

    @Override
    public void noticeObserveurs(Data obj) {
        for (Observeur<Data> o:
                obsList) {
            o.update(this,obj);
        }
    }

    
}
