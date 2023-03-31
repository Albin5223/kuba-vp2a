package Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.*;

public class ControleurEditeur extends MouseAdapter{
    
    Model model;
    
    int SIZE;

    public ControleurEditeur(Model m,int n){
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
}
