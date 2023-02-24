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
	JPanel plateau;
	JPanel conteneur;
	JoueurView jv1;
	JoueurView jv2;
    
    public View(Model m) {
    	this.setVisible(true);
    	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.m = m;

		n = m.getN();


		longueur = 4*n -1;
        taille_case = ((this.getHeight()-100)/longueur)*7/8;
		

		conteneur = new JPanel();
		conteneur.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		conteneur.setLayout(null);
		update();
		plateau.setBounds(this.getWidth()/2-taille_case*longueur/2,this.getHeight()/2-taille_case*longueur/2,taille_case*longueur+1,taille_case*longueur+1);
		
		Controleur ctrl = new Controleur(m,taille_case);

		plateau.addMouseMotionListener(ctrl);
		plateau.addMouseListener(ctrl);


		jv1 = new JoueurView(m.getCurrentPlayer());
		jv1.setBounds(10,plateau.getY(),plateau.getX()-20,longueur*taille_case/3);
		jv2 = new JoueurView(m.getOtherPlayer());
		jv2.setBounds(10,plateau.getY()+longueur*taille_case/2,plateau.getX()-20,longueur*taille_case/3);




    	this.setContentPane(conteneur);

		conteneur.add(jv1);
		conteneur.add(jv2);
		conteneur.add(plateau);
		this.joueurSuivant();

		this.repaint();
    	
    	
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
		plateau = new JPanel(){
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

	public void joueurSuivant(){
		System.out.println(m.getCurrentPlayer().getColor());
		if (m.getCurrentPlayer().getColor()==Colour.WHITE){
			jv1.mettreBarre();
			jv2.enleverBarre();
		}
		else{
			jv2.mettreBarre();
			jv1.enleverBarre();
		}
		this.repaint();
	}
    
}
