import Model.*;
import java.util.LinkedList;

public class Defi extends Model {

	private int numero ; 	
	private Direction [][] coups;
	private (int,int) [][] poscoup= ;

	public Defi(int n){

        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        plat = new Plateau(2);

        initialiseDefi(0);
        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        state=State.SUCCESS;
        joueurs[0] = j1;
        joueurs[1] = j2;
        numero = n;
    }

    public void initialiseDefi(int x){

    	Colour [][]plateau = new Colour[7][7];
        plateau[0][1] = Colour.WHITE;
        for (int i = 2; i < 7; i++){
        	plateau[0][i] = Colour.WHITE;
        }
        plat.setBoard(plateau);

    }
	


}