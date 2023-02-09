package main.java.MVC1;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame{
    
    
    Model model;
    
    public View(Model m) {
    	this.setVisible(true);
    	this.setSize(600, 800);
    	model = m;
    	
    	JPanel conteneur = new JPanel();
    	Controleur ctrl = new Controleur(m,100);
    	conteneur.addMouseListener(ctrl);
    	conteneur.addMouseMotionListener(ctrl);
    	this.add(conteneur);
    	
    	
    	
    }
    
}
