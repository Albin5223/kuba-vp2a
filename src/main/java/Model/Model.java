package Model;


import java.util.LinkedList;


public class Model implements Observe<Data>,Data{
    Plateau plat;
    Joueur[] joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
    int n;
    State state;
    LinkedList<Observeur<Data>> observeurs;
    boolean isIA;
    boolean estDefi;
    boolean estEditeur;

    public Model(int n, boolean b,boolean x,boolean y){
        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        if(x){
            plat = new Defi(n,j1,j2);
        }
        else{
            plat = new Plateau(n,j1,j2);
        }
        state=State.SUCCESS;
        joueurs[0] = j1;
        joueurs[1] = j2;
        this.n = n;
        this.estDefi = x;
        this.isIA = b;
        this.estEditeur = y;
        if (estEditeur){
            plat.crerPlatVide();
        }
    }

    public void initialiseBille(){
        plat.initialiseBille();
    }


    public int getN(){
        return n;
    }

    public boolean isIa(){
        return isIA;
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

    public State push(Position p,Direction d){
        if (isIA && joueurCurrent == 1) {
            Move move;
            try {
                move = NoeudIA.determineBestMove(plat, 5, getOtherPlayer(), getCurrentPlayer());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return State.WRONGDIRECTION;
            }
            state = plat.push(move.pos,move.dir,getCurrentPlayer(),getOtherPlayer());
        }
        else {
            state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());
        }

        if(plat.isOver(joueurs[0],joueurs[1])==null ){
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

    @Override
    public Joueur getVainqueur() {
        return plat.isOver(joueurs[0],joueurs[1]);
    }

    @Override
    public void reset(){
        partieFinie=false;
        plat.resetAll();
        plat.initialiseBille();
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
