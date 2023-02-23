package GUI;

import javax.swing.*;
import java.awt.*;

import Controleur.Controleur;
import Model.Model;

public class View extends JFrame{
    
    
    Model model;
    
    public View(Model m) {
    	this.setVisible(true);
    	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
    	model = m;
		int n = 2;


		int longueur = 4*n -1+2;
        int taille_case = (this.getHeight()-100)/longueur;

		JPanel conteneur = new JPanel(){
			public void paintComponent(Graphics g){
				for (int i = 0;i<longueur;i++){
					for (int j = 0;j<longueur;j++){
						g.drawRect(10+i*taille_case, 10+j*taille_case,taille_case,taille_case);
					}
				} 
			}
		};


    	//this.add(conteneur);

		PlateauG plat = new PlateauG(n);

		this.add(plat);

    	
    	
    }
    
}
