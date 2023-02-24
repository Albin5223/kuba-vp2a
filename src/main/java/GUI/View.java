package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.Controleur;
import Model.Model;

public class View extends JFrame{
    
    
    Model model;
    
    public View(Model m) {
    	this.setVisible(true);
    	this.setSize(600, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	model = m;
    	
    	JPanel conteneur = new JPanel();
    	Controleur ctrl = new Controleur(m,100);
    	conteneur.addMouseListener(ctrl);
    	conteneur.addMouseMotionListener(ctrl);
    	this.add(conteneur);
    	
    	
    	
    }
    
}
