package GUI;

import javax.swing.*;
import java.awt.*;

import Controleur.Controleur;
import Model.*;

public class View extends JFrame{
    
    
   	Model m;
	int longueur;
	int n;
	int taille_case;
	JPanel conteneur;
    
    public View(Model m) {
    	this.setVisible(true);
    	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.m = m;

		n = m.getN();


		longueur = 4*n -1;
        taille_case = (this.getHeight()-100)/longueur;
		update();
		
		Controleur ctrl = new Controleur(m,taille_case);

		conteneur.addMouseMotionListener(ctrl);
		conteneur.addMouseListener(ctrl);


    	this.add(conteneur);

		//PlateauG plat = new PlateauG(n);
		//this.add(plat);
		revalidate();
    	
    	
    }

	public void updatePlateau(Graphics g){
		Plateau p = m.getPlateau();
		for (int i = 0;i<longueur;i++){
			for (int j = 0;j<longueur;j++){
				Colour c = p.getColor(j, i);
				if (c != null){
					switch(c){
						case RED : g.setColor(Color.red);g.fillOval(i*taille_case,j*taille_case,taille_case, taille_case);break;
						case WHITE : g.setColor(Color.white);g.fillOval(i*taille_case,j*taille_case,taille_case, taille_case);break;
						case BLACK : g.setColor(Color.black);g.fillOval(i*taille_case,j*taille_case,taille_case, taille_case);break;
					}
				}
				
			}
		}
	}

	public void update(){
		conteneur = new JPanel(){
			public void paintComponent(Graphics g){
				g.setColor(Color.black);
				for (int i = 0;i<longueur;i++){
					for (int j = 0;j<longueur;j++){
						g.drawRect(i*taille_case,j*taille_case,taille_case,taille_case);
					}
				}

				updatePlateau(g);
			}
		};
		this.repaint();
	}
    
}
