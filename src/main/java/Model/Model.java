package Model;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import GUI.MenuNiveaux;
import GUI.MenuNiveaux;


public class Model implements Observe<Data>,Data{
    Plateau plat;
    Joueur[] joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
    int n;
    State state;
    LinkedList<Observeur<Data>> observeurs;
    MenuNiveaux menuNiveaux= new MenuNiveaux(this);
    ModeJeu modeJ;

    public Model(int n,ModeJeu mode) throws CloneNotSupportedException{
        modeJ = mode;
        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        plat =new Plateau(n,j1,j2); 
        plat.initialiseBille(); 
        switch(mode){
            case DEFI : plat = new Defi(n,j1,j2);break;
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

    
    public void ajouteNiveau() throws IOException {
        String nom ;
        try {
            String s = Integer.toString(n)+";"+plat.toString()+"\n";
            Files.write(Paths.get("ressource/Editeur.txt"),s.getBytes(),StandardOpenOption.APPEND);        
        }
        catch (IOException e){
        }
    }

    public void ouvreDefi(int n){
        menuNiveaux.panneauOuverture();


    }
    
    public void changePlateau(int n){
        String a = menuNiveaux.getDefi(n,false);
        String platNiveau = "";
        int x = 0;
        int y = 0;
        while (y < 2){
            if (a.charAt(x) == ';') y++;
            x++;
        }
        for (int i = x; i<a.length()-1; i ++){
            platNiveau += a.charAt(i);
        }
        plat.setBoard(platNiveau);

    }

    
    public void ajouteNiveau() throws IOException {
        String nom ;
        try {
            String s = Integer.toString(n)+";"+plat.toString()+"\n";
            Files.write(Paths.get("ressource/Editeur.txt"),s.getBytes(),StandardOpenOption.APPEND);        
        }
        catch (IOException e){
        }
    }

    public void ouvreDefi(int n){
        menuNiveaux.panneauOuverture();


    }
    
    public void changePlateau(int n){
        String a = menuNiveaux.getDefi(n,false);
        String platNiveau = "";
        int x = 0;
        int y = 0;
        while (y < 2){
            if (a.charAt(x) == ';') y++;
            x++;
        }
        for (int i = x; i<a.length()-1; i ++){
            platNiveau += a.charAt(i);
        }
        plat.setBoard(platNiveau);

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
        try {
            move = NoeudIA.determineBestMove(plat, 5);
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
        }

        return move;
    }

    public State push(Position p,Direction d){
        /* 
        if (isIa() && joueurCurrent == 1) {
            Move move;
            try {
                move = NoeudIA.determineBestMove(plat, 5);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
            state = plat.push(move.pos,move.dir,joueurs[1],joueurs[0]);
        }
        else {
            state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());
        }
        */
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
