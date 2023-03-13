package Model;

import Model.*;
import java.util.LinkedList;

public class Defi extends Model {

	private int numero ; 	
	private Direction [][] coups;
	private int [][] casex;
	private int [][] casey ;

	public Defi(int n){
		super(2);

        initialiseDefi(0);

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