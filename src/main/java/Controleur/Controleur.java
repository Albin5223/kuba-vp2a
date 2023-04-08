package Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import Model.*;

public class Controleur extends MouseAdapter{

    Model model;
    int positionDepartX = -1;
    int positionDepartY = -1;
    
    int positionArriveX = -1;
    int positionArriveY = -1;
    
    int SIZE;

    public boolean defi = false;


    public Controleur(Model m,int n){
        this.model = m;
        SIZE = n;
    }

    
    public void setModel(Model m){
        model = m;
    }
    
    
    private static Direction detailDirectionEstWest(Position depart, Position arrive, Direction dir) {
    	int detailX = arrive.getI() - depart.getI();
    	int detailY = arrive.getJ() - depart.getJ();
    	if (Math.abs(detailX)>Math.abs(detailY)){
    		if(detailX<=0) {
    			return Direction.WEST;
    		}
    		return Direction.EAST;
        }
    	return dir;
    }
    
    
    private static Direction determineDirection(Position depart, Position arrive){
		if(depart.getJ()==arrive.getJ() && depart.getI() == arrive.getI()){
			return Direction.INVALID;
		}
        if (depart.getJ()<arrive.getJ()){
            return detailDirectionEstWest(depart,arrive,Direction.SOUTH);
        }
        else{
			return detailDirectionEstWest(depart,arrive,Direction.NORTH);
			
        }
    }

    
    public State move(Position depart, Position arrive){
        Direction direction = determineDirection(depart,arrive);
		State retour = State.SUCCESS;
        if(!model.isEnd()){
        	retour = model.push(new Position(depart.getJ(), depart.getI()),direction);
		}
		return retour;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	Position p1 = new Position(positionDepartX,positionDepartY);
		Position p2 = new Position(positionArriveX,positionArriveY);
			
		State retour = move(p1,p2);
    	resetPosition();
		
		if(model.isIa() && retour != State.PUSHOPPMARBLE && retour != State.PUSHREDMARBLE ){
			jouerIA(p1,p2);
		}
    }

	public void jouerIA(Position p1,Position p2){
		Timer vibe = new Timer();
			vibe.schedule(new TimerTask() {
				int time = 100;
				
				public void run() {
					if(time == 0){
						cancel();
						move(p1,p2);
						
					}
					time--;
			}
		},0,15);
	}

	@Override
	public void mousePressed(MouseEvent e) {
			
		positionDepartX = e.getX()/SIZE;
		positionDepartY = e.getY()/SIZE;
		
		positionArriveX = positionDepartX;
		positionArriveY = positionDepartY;	
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (positionDepartX != -1 && positionDepartY != -1) {
			if (positionDepartX != e.getX()/SIZE || positionDepartY != e.getY()/SIZE) {
				positionArriveX = e.getX()/SIZE;
				positionArriveY = e.getY()/SIZE;
			}
		}
		
	}
	
	public void resetPosition() {
		positionDepartX = -1;
		positionDepartY = -1;
		
		positionArriveX = -1;
		positionArriveY = -1;
	}
}



