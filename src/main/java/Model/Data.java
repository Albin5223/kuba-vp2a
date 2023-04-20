package Model;

public interface Data {

    public Marble getMarble(int i,int j);

    public State getState();

    public Joueur getVainqueur();

    public void reset();

    public boolean tourIA();

    public int getJoueurCurrent();

    public boolean estEditeur();

    public int[][] billesCapturees();

    public boolean isIa();


}
