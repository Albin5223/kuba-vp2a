package Controleur;
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


    
    
    private static Direction detailDirectionEstWest(Pos depart, Pos arrive, Direction dir) {
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
    
    
    private static Direction determineDirection(Pos depart, Pos arrive){
        if (depart.getJ()<arrive.getJ()){
            return detailDirectionEstWest(depart,arrive,Direction.SOUTH);
        }
        else{
            return detailDirectionEstWest(depart,arrive,Direction.NORTH);
        }
    }

    
    public void move(Pos depart, Pos arrive){
        Direction direction = determineDirection(depart,arrive);
        System.out.println(direction.dirName());
        //if(!model.isEnd()){
        	//model.push(depart,direction);
        //}
		
         

    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	Pos p1 = new Pos(positionDepartX,positionDepartY);
		Pos p2 = new Pos(positionArriveX,positionArriveY);
		
		
		
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



