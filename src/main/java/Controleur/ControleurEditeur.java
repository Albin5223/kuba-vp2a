package Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import Model.*;

public class ControleurEditeur extends MouseAdapter implements KeyListener{
    
    Model model;
    boolean controlPressed = false;
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

    
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            controlPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && controlPressed){
            System.out.println("Les touches ctrl et s sont préssées en même temps");
            try {
                model.ajouteNiveau();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_O && controlPressed){
            System.out.println("Les touches ctrl et o sont préssées en même temps");
            model.ouvreDefi(1);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            controlPressed = false;
        }
    }
}
