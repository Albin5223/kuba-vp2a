package Model;

import Model.*;
import java.util.ArrayList;

public class Defi extends Model {

	private int numero;
	private int prog = 0;

	private Position [][] solpos = {{new Position(0,7),new Position(0,8),new Position(0,9)}};
	private Direction [][] solcoups = {{Direction.EAST,Direction.EAST,Direction.EAST}} ;


	private Position [][] pos;
	private Direction [][] coups ;

	public Defi(int n){
		super(3);

        initialiseDefi(0);

        numero = n;

    }



    public void initialiseDefi(int x){

    	Colour [][]plateau = new Colour[11][11];
    	
    	plateau[0][7] = Colour.WHITE;
        plateau[0][8] = Colour.BLACK;
        plateau[0][9] = Colour.BLACK;
        plateau[0][10] = Colour.BLACK;


        plat.setBoard(plateau);

    }
    public void push(Position p,Direction d){        
    	State state2 = coupValide(p,d);

        state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());

    
        if(state2 == State.SUCCESS && plat.isOver(joueurs[0],joueurs[1])==null){
            if(State.SUCCESS == state){
            joueurSuivant();
            }
        }
        
        noticeObserveurs(this);
    }

    public State coupValide (Position position, Direction direction){

    	if(position.i == solpos[prog].i && position.j == solpos[prog].j && direction == solcoups[progression]){
    			pos[prog] = position;
    			coups[prog] = direction;
    			prog++;
    			if (prog = solpos.length()){
    				partieFinie = true;
    			}
    			return State.SUCCESS;
    	}
    	else {
    		return State.WRONGDIRECTION;
    	}


    }
	


}