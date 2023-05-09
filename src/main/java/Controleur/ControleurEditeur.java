package Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import Model.*;

public class ControleurEditeur extends MouseAdapter{
    
    public static boolean SolutionMod;
    Model model;
    int SIZE;
    public static String solutions;

    int positionDepartX = -1;
    int positionDepartY = -1;
    
    int positionArriveX = -1;
    int positionArriveY = -1;

    public ControleurEditeur(Model m,int n){
        solutions="";
        this.model = m;
        SIZE = n;
    }


    public void setModel(Model m){
        model = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Position p = new Position(e.getY()/SIZE,e.getX()/SIZE);
        model.changeCouleur(p);
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(SolutionMod){
            Position p1 = new Position(positionDepartX,positionDepartY);
		    Position p2 = new Position(positionArriveX,positionArriveY);
            Direction direction = Controleur.determineDirection(p1,p2);
            

            Move m = new Move(p1, direction);
            solutions+=";"+m.toString();
            model.push(new Position(p1.getJ(), p1.getI()),direction);

            System.out.println(solutions);
        }
		
    }

	@Override
	public void mousePressed(MouseEvent e) {
        if(SolutionMod){
            positionDepartX = e.getX()/SIZE;
            positionDepartY = e.getY()/SIZE;
            
            positionArriveX = positionDepartX;
            positionArriveY = positionDepartY;
        }
		
	}

    @Override
	public void mouseDragged(MouseEvent e) {
        if(SolutionMod){
            if (positionDepartX != -1 && positionDepartY != -1) {
                if (positionDepartX != e.getX()/SIZE || positionDepartY != e.getY()/SIZE) {
                    positionArriveX = e.getX()/SIZE;
                    positionArriveY = e.getY()/SIZE;
                }
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
