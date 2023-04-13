package Model;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import GUI.MenuNiveaux;


public class Model implements Observe<Data>,Data{
    Plateau plat;
    Joueur[] joueurs;
    int joueurCurrent = 0; //L'entier indique le joueur courant
    boolean partieFinie;
    int n;
    State state;
    LinkedList<Observeur<Data>> observeurs;
    boolean isIA;
    boolean estDefi = false;
    boolean estEditeur = false;
    PrintWriter doc;
    MenuNiveaux menuNiveaux= new MenuNiveaux();

    public Model(int n, boolean b,boolean x,boolean y){
        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        this.n = n; 
        this.isIA = b;
        this.estEditeur = y;
        if(x){
            plat = new Defi(n,j1,j2);
            this.estDefi = true;
            System.out.println("est defi ");
        }
        else{ 
            plat = new Plateau(n,j1,j2);
            if (estEditeur){               
                plat.crerPlatVide();
                System.out.println("est Editeur ");
            }
            else{
            initialiseBille();
            System.out.println("est plateau ");
            }
        }
        state=State.SUCCESS;
        joueurs[0] = j1;
        joueurs[1] = j2;
       
    }


    public void initialiseBille(){
        plat.initialiseBille();
    }

    
    public void ajouteNiveau() throws IOException {
        String nom ;
        try {
            String s = Integer.toString(n)+";"+plat.toString()+"\n";
            Files.write(Paths.get("ressource/Editeur.txt"),s.getBytes(),StandardOpenOption.APPEND);        
        System.out.println("ajout fait");
        }
        catch (IOException e){
            System.out.println("erreur");
        }
    } 
    
    public void ouvreDefi(int n){
        String a = menuNiveaux.ouvreDefi(n);
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
        if (isIA && joueurCurrent == 1) {
            Move move;
            try {
                move = NoeudIA.determineBestMove(plat, 5, getOtherPlayer(), getCurrentPlayer());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return;
            }
            state = plat.push(move.pos,move.dir,getCurrentPlayer(),getOtherPlayer());
            plat.affiche();
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
