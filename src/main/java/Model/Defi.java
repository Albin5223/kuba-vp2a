package Model;

import Model.*;

public class Defi extends Plateau {

	private int numero;
	private int prog = 0;

	private Position [][] solpos = {{new Position(0,7),new Position(0,8),new Position(0,9)}};
	private Direction [][] solcoups = {{Direction.EAST,Direction.EAST,Direction.EAST}} ;


	private Position [][] pos;
	private Direction [][] coups ;

	public Defi(int n){

		super(3);
        initialiseDefi(n);
        numero = n;
		this.defi = true;

    }



    public void initialiseDefi(int x){

    	Colour [][]plateau = new Colour[11][11];
    	
    	plateau[0][7] = Colour.WHITE;
        plateau[0][8] = Colour.BLACK;
        plateau[0][9] = Colour.BLACK;
        plateau[0][10] = Colour.BLACK;

    }

	@override
    public State push(Position p,Direction d,Joueur j1,Joueur j2){ 

    	State state = coupValide(p,d);

    
        if(state == State.SUCCESS && this.isOver(j1,j2)==null){
			state = push_rec(p,d,null,j1,j2);
            }
        
        return state;
    }

    public State coupValide (Position position, Direction direction){

    	if(position.i == solpos[numero][prog].i && position.j == solpos[numero][prog].j && direction == solcoups[numero][prog]){
    			pos[numero][prog] = position;
    			coups[numero][prog] = direction;
    			prog++;
    			return State.SUCCESS;
    	}

		if (direction == Direction.INVALID) {
			return State.WRONGDIRECTION;
		}
		if (board[position.i][position.j] ==  null) {
			return State.EMPTYTILE;
		}
		if ( Colour.WHITE != board[position.i][position.j]) {
			return State.MARBLEOWNERSHIPERROR;
		}
		//Mauvaise gestion 
		if (position.j+direction.dirInverse().dirY() != -1 && position.j+direction.dirInverse().dirY() != this.longueur && position.i+direction.dirInverse().dirX() != -1 && position.i+direction.dirInverse().dirX() != this.longueur) {
			if (this.board[position.i+direction.dirInverse().dirX()][position.j+direction.dirInverse().dirY()] != null) {
				System.out.println(position.j+direction.dirInverse().dirX());
				System.out.println(position.i+direction.dirInverse().dirY());
				return State.TILEBEFORENOTEMPTY;
			}
		}
    
    	return State.WRONGDIRECTION;
    }
	


}