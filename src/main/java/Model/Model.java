package Model;


import java.util.LinkedList;

import SearchFile.GestionnaireNiveaux;



public class Model implements Observe<Data>,Data{
    Plateau plat;
    Joueur[] joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
    int n;
    State state;
    LinkedList<Observeur<Data>> observeurs;
    ModeJeu modeJ;
  

    public Model(int n,ModeJeu mode){
        GestionnaireNiveaux.addModel(this);
        modeJ = mode;
        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        plat =new Plateau(n,j1,j2); 
        plat.initialiseBille(); 
        switch(mode){
            case EDITION :plat.creerPlatVide(); break;
            case FUN : plat.initialiseBilleWithSpecialMarble();
            default : break;
        }

        state=State.SUCCESS;
        joueurs[0] = j1;
        joueurs[1] = j2;
        this.n = n;
    }



    public void initialiseBille(){
        plat.initialiseBille();
    }
    
    public void setBoard(Marble [][] newBoard){
        plat.setBoard(newBoard);
    }

    

    public int getN(){
        return n;
    }

    public boolean isIa(){
        return modeJ == ModeJeu.IA;
    }


    public Joueur getCurrentPlayer(){
        return joueurs[joueurCurrent];
    }

    public Joueur getOtherPlayer() {
        if (joueurCurrent==0){
            return joueurs[1];
        }
        return joueurs[0];
    }

    public void joueurSuivant() {
        if(modeJ == ModeJeu.TUTO){
            return;
        }
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

    public Move determineBestMove(){
        Move move = null;
        
        move = NoeudIA.determineBestMove(plat, 5);
        
        return move;
    }

    public State push(Position p,Direction d){
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
        return state;
    }

    public boolean tourIA(){
        return isIa() && getJoueurCurrent() == 1;
    }
    public int getJoueurCurrent(){
        return joueurCurrent;
    }

    public boolean estEditeur(){
        return modeJ == ModeJeu.EDITION;
    }

    public int[][] billesCapturees(){
        int nb = 2*(n*n);
        int[][] tab = {{joueurs[0].getBillesRougesCapturees(),nb-joueurs[1].getBilles()},
                        {joueurs[1].getBillesRougesCapturees(),nb-joueurs[0].getBilles()}};
        return tab;
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
    public Marble getMarble(int i, int j) {
        return plat.getMarble(i,j);
    }

    @Override
    public State getState() {
        return state;
    }


    @Override
    public Joueur getVainqueur() {
        return plat.isOver(joueurs[0],joueurs[1]);
    }

    @Override
    public void reset(){
        joueurCurrent = 0;
        partieFinie=false;
        plat.resetAll();
        plat.initialiseBille();
        switch(modeJ){
            case EDITION :plat.creerPlatVide(); break;
            case FUN : plat.initialiseBilleWithSpecialMarble(); break;
            default : break;
        }
        for (int i = 0;i<2;i++){
            joueurs[i].resetData();
        }

        noticeObserveurs(this);
    }

    public void changeCouleur(Position p){
        plat.changeCouleur(p);
        noticeObserveurs(this);
    }

}
