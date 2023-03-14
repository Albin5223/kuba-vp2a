package Model;

import Model.*;
import java.util.LinkedList;

public class Defi extends Model {

	private int numero ; 	
	private Direction [][] coups;
	private int [][] casex;
	private int [][] casey ;

	public Defi(int n){
		super(4);

        initialiseDefi(0);

        numero = n;

    }

    public void initialiseDefi(int x){

    	Colour [][]plateau = new Colour[15][15];
    	
    	plateau[0][0] = Colour.BLACK;/*
        //plateau[0][1] = Colour.BLACK;
        for (int i = 0; i < 27; i++){
        	for (int j = 0; j < 27; j++ ){
				plateau[i][j] = Colour.WHITE;
        	}
        	
        }
        //plateau[0][i] = Colour.WHITE;
        plat.clear();*/
        plat.setBoard(plateau);

    }
	


}