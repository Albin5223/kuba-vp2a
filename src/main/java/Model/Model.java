package Model;

import GUI.View;

import java.util.LinkedList;


public class Model implements Observe<Data>,Data{
    Plateau plat;
    Joueur[] joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
    //View view;
    int n;
    State state;
    LinkedList<Observeur<Data>> observeurs;
    public Model(int n){
        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        plat = new Plateau(n);
        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        state=State.SUCCESS;
        joueurs[0] = j1;
        joueurs[1] = j2;
        this.n = n;
    }

    public void initialiseBille(){
        plat.initialiseBille();

    }

    public void setView(View v){
        //view = v;
        addObserveur(v);
        plat.initialiseBille();
        noticeObserveurs(this);
    }

    public int getN(){
        return n;
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

    public Plateau getPlateau(){
        return plat;
    }

    public void push(Position p,Direction d){        
        state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());
    
        if(plat.isOver(joueurs[0],joueurs[1])==null){
            if(State.SUCCESS == state){
            joueurSuivant();
            }
        }
        else{
            partieFinie = true;
        }
        
        noticeObserveurs(this);
    }


    @Override
    public void addObserveur(Observeur<Data> obs) {
        if(!observeurs.contains(obs)){
        observeurs.add(obs);}
    }

    @Override
    public void noticeObserveurs(Data obj) {
        for (Observeur<Data> o: observeurs) {
            o.update(obj);
        }
    }

    @Override
    public Colour getMarble(int i, int j) {
        return plat.getColor(i,j);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Joueur getJoueur() {
        return getCurrentPlayer();
    }
}
