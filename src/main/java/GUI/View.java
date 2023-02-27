package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
	JoueurView currentJoueur;
    
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
		update(State.SUCCESS);
		plateau.setBounds(this.getWidth()/2-taille_case*longueur/2,this.getHeight()/2-taille_case*longueur/2,taille_case*longueur+1,taille_case*longueur+1);
		
		Controleur ctrl = new Controleur(m,taille_case);

		plateau.addMouseMotionListener(ctrl);
		plateau.addMouseListener(ctrl);


		jv1 = new JoueurView(m.getCurrentPlayer());
		int taille_Jv = plateau.getX()-20;
		jv1.setBounds(10,plateau.getY(),taille_Jv,longueur*taille_case/3);
		jv1.initialisePaneMarbleCaptured();
		currentJoueur = jv1;
		jv2 = new JoueurView(m.getOtherPlayer());
		jv2.setBounds(10,plateau.getY()+longueur*taille_case/2,taille_Jv,longueur*taille_case/3);
		jv2.initialisePaneMarbleCaptured();

    	this.setContentPane(conteneur);

		conteneur.add(jv1);
		conteneur.add(jv2);
		conteneur.add(plateau);
		this.joueurSuivant();
    	
    	
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

	public void update(State state){
		if(state != State.PUSHOPPMARBLE && state != State.PUSHREDMARBLE && state != State.SUCCESS){
			vibrer(state);
		}
		if (state == State.PUSHOPPMARBLE){
			currentJoueur.addOpponentMarble();
			currentJoueur.repaint();
		}
		else{
			if(state == State.PUSHREDMARBLE){
				currentJoueur.addRedMarble();
				currentJoueur.repaint();
			}
		}
		this.repaint();
	}

	public void bougerRight(){
		plateau.setBounds(plateau.getX()+20,plateau.getY(), plateau.getWidth(), plateau.getHeight());
	}

	public void bougerLeft(){
		plateau.setBounds(plateau.getX()-20,plateau.getY(), plateau.getWidth(), plateau.getHeight());
	}

	public void vibrer(State state){
		Timer vibe = new Timer();
		vibe.schedule(new TimerTask() {
			int time = 8;
			boolean i = false;
			int posX = plateau.getX();

    		public void run() {
				if(i){
					bougerLeft();
				}
				else{
					bougerRight();
				}
				i=!i;
				if(time == 0){
					cancel();
					plateau.setBounds(posX,plateau.getY(), plateau.getWidth(), plateau.getHeight());
					afficherPopUp(state);

				}
				time--;
    		}
		},0, 50);
	}


	public void afficherPopUp(State state){
		PopUpError popUp = new PopUpError(state);
		
		popUp.setBounds(plateau.getWidth()/2-250,plateau.getHeight()/2-250,500,500);
		plateau.add(popUp);
		Timer affiche = new Timer();
		affiche.schedule(new TimerTask() {
			int time = 8;

    		public void run() {
				if(time == 0){
					cancel();
					plateau.remove(popUp);
					conteneur.repaint();
				}
				time--;
    		}
		},0, 150);
		
	}

	

	public void joueurSuivant(){
		if (m.getCurrentPlayer().getColor()==Colour.WHITE){
			jv1.mettreBarre();
			jv2.enleverBarre();
			currentJoueur = jv1;
		}
		else{
			jv2.mettreBarre();
			jv1.enleverBarre();
			currentJoueur = jv2;
		}
	}
    
}
