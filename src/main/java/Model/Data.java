package Model;

public interface Data {

    public Marble getMarble(int i,int j);

    public State getState();

    public Joueur getJoueur();

    public Joueur getVainqueur();

    public void reset();

    public int getJoueurCurrent();

    public boolean estEditeur();


}
