package MVC;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.*;

public class Controleur extends MouseAdapter{

    Model model;
    int positionDepartX = -1;
    int positionDepartY = -1;
    
    int positionArriveX = -1;
    int positionArriveY = -1;
    
    int SIZE;


    public Controleur(Model m,int n){
        this.model = m;
        SIZE = n;
    }
    
    public void setModel(Model m){
        model = m;
    }


    //TODO : proriser les directions avec la différence la plus grande
    
    
    private static Direction detailDirectionEstWest(Position depart, Position arrive, Direction dir) {
    	int detailX = arrive.getX() - depart.getX();
    	int detailY = arrive.getY() - depart.getY();
    	if (Math.abs(detailX)>Math.abs(detailY)){
    		if(detailX<=0) {
    			return Direction.WEST;
    		}
    		return Direction.EAST;
        }
    	return dir;
    }
    
    
    private static Direction determineDirection(Position depart, Position arrive){
        if (depart.getY()<arrive.getY()){
            return detailDirectionEstWest(depart,arrive,Direction.SOUTH);
        }
        else{
            return detailDirectionEstWest(depart,arrive,Direction.NORTH);
        }
    }

    
    public void move(Position depart, Position arrive){
        Direction direction = determineDirection(depart,arrive);
        System.out.println(direction.dirName());
        if(!model.isEnd()){
        	//model.push(depart,direction);
        }
		
         

    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	Position p1 = new Position(positionDepartX,positionDepartY);
		Position p2 = new Position(positionArriveX,positionArriveY);
		
		
		
		move(p1,p2);
    	resetPosition();
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



