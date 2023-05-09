package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import Controleur.*;
import Model.*;
import SearchFile.*;

public class View extends JFrame implements Observeur<Data>{
    
    
	int longueur;
	int n;
	private boolean isOver;
	private boolean isReloading;
	int taille_case;
	JPanel plateau;
	JPanel conteneur;
	JoueurView jv1;
	JoueurView jv2;
	JoueurView[] joueurs; 
	boolean isViber;
	boolean isTurnIA;
	
	OptionView optView;
	PanneauFinDeJeu panneauFinDeJeu;


	JFrame launcher;

	

    public View(int nb,JFrame l) {

		joueurs = new JoueurView[2];
		launcher = l;
		this.setTitle("Plateau KUBA");
		

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
    	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setIconImage(BanqueImage.images[8]);

 		n = nb;
		isTurnIA = false;

		longueur = 4*n -1;
        taille_case = ((this.getHeight()-100)/longueur)*7/8;

		BanqueImage.scaleMarble(taille_case);
		
		BanqueImage.images[5] = BanqueImage.scaleImage(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,BanqueImage.images[5]);

		conteneur = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(BanqueImage.images[5],0,0,null);
				
			}
		};
		conteneur.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		conteneur.setLayout(null);
    }

	

	public void start(Data obj){
		plateau = new JPanel(){
			public void paintComponent(Graphics g){
				Graphics2D g1 = (Graphics2D) g;
				BasicStroke line = new BasicStroke(4.0f);
				g1.setStroke(line);
				g1.setColor(Color.black);
				for (int i = 0;i<longueur-1;i++){
					for (int j = 0;j<longueur-1;j++){
						g1.drawRect(taille_case/2+i*taille_case,taille_case/2+j*taille_case,taille_case,taille_case);
					}
				}
				updatePlateau(g,obj);
			}
		};
		plateau.setBounds(this.getWidth()/2-taille_case*longueur/2,this.getHeight()/2-taille_case*longueur/2,taille_case*longueur+1,taille_case*longueur+1);
		jv1 = new JoueurView(Colour.WHITE);
		int taille_Jv = plateau.getX()-20;
		jv1.setBounds(10,plateau.getY(),taille_Jv,longueur*taille_case/3);
		jv1.initialisePaneMarbleCaptured();
		jv1.mettreBarre();
		jv2 = new JoueurView(Colour.BLACK);
		jv2.setBounds(10,plateau.getY()+longueur*taille_case/2,taille_Jv,longueur*taille_case/3);
		jv2.initialisePaneMarbleCaptured();

		optView = new OptionView(this,launcher);
		optView.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-300,50, 300,200);
		optView.deployerPanneau(false);

		optView.getReplayLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
				if(!isOver && !isReloading && !isTurnIA){
					isReloading = true;
					Timer vibe = new Timer();
					vibe.schedule(new TimerTask() {
					int time = 80;
    				public void run() {

						View.this.bougerPlateau(Direction.EAST);

						if(time == 0){
							cancel();
							View.this.rejouerJeu(obj);
						}
						time--;
    				}
					},0,10);
					vibe.purge();
				}
            }

			@Override
			public void mouseEntered(MouseEvent e){
				View.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				optView.getReplayLabel().setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e){
				View.this.setCursor(Cursor.getDefaultCursor());
				optView.getReplayLabel().setForeground(new Color(173, 103, 53));
			}
        });

		joueurs[0] = jv1;
		joueurs[1] = jv2;
		this.setContentPane(conteneur);

		conteneur.add(jv1);
		conteneur.add(jv2);
		conteneur.add(plateau);
		conteneur.add(optView);
	}

	public void addCtrl(Controleur ctrl){
		plateau.addMouseMotionListener(ctrl);
		plateau.addMouseListener(ctrl);
	}

	public void addCtrlEditeur (ControleurEditeur ctrl){
		plateau.addMouseListener(ctrl);
		plateau.addMouseMotionListener(ctrl);

		this.addKeyListener(new KeyListener() {
			boolean controlPressed;
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					controlPressed = true;
					
				}
				if(e.getKeyCode() == KeyEvent.VK_R && controlPressed && !PanneauEnregistrement.openSave){
					ControleurEditeur.SolutionMod = true;
					GestionnaireNiveaux.savePlateau();
				}
				if (e.getKeyCode() == KeyEvent.VK_S && controlPressed && !PanneauEnregistrement.openSave){
					View.this.plateau.setVisible(false);
					PanneauEnregistrement pe = new PanneauEnregistrement(View.this);
					pe.setBounds(View.this.getWidth()/2-150, View.this.getHeight()/2-100, 300, 200);
					pe.initialise();
					View.this.add(pe);
					View.this.repaint();

					pe.getEnregistrerButton().addActionListener( event->{
						View.this.remove(pe);
						GestionnaireNiveaux.ajouteDefi(pe.getNom(),ControleurEditeur.solutions);
						View.this.plateau.setVisible(true);
						View.this.requestFocus();
						PanneauEnregistrement.openSave = false;
						afficherPopUp(null,"Sauvegarde réussie");
						ControleurEditeur.SolutionMod = false;
					});

					pe.getAnnulerButton().addActionListener( event->{
						PanneauEnregistrement.openSave = false;
						View.this.remove(pe);
						View.this.plateau.setVisible(true);
						View.this.requestFocus();
					});
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					controlPressed = false;
				}
			}
			
		});
	}

	public void updatePlateau(Graphics g,Data obj){
		for (int i = 0;i<longueur;i++){
			for (int j = 0;j<longueur;j++){
				Colour c = obj.getMarble(j , i).getColour();
				Power p = obj.getMarble(j, i).getPower();
				if (c != null){
					switch(p){
						case NORMAL:g.drawImage(BanqueImage.banqueMarbleImages[c.ordinal()],i*taille_case,j*taille_case,null);break;
						default : g.drawImage(BanqueImage.banquePowerImages[c.ordinal()],i*taille_case,j*taille_case,null);break;
					}
				}
			}
		}
	}

	public void bougerPlateau(Direction d){
		plateau.setBounds(plateau.getX()+d.dirX()*20,plateau.getY()+d.dirY()*10, plateau.getWidth(), plateau.getHeight());
	}


	public void vibrer(State state){
		PopUp popUp = new PopUp(state);
		popUp.setBounds(plateau.getWidth()/2-100,plateau.getHeight()/2-50,250,100);
		plateau.add(popUp);
		plateau.repaint();
		Timer vibe = new Timer();
		int posX = plateau.getX();
		vibe.schedule(new TimerTask() {
			int time = 4;
			boolean i = false;

    		public void run() {
				if(i){
					bougerPlateau(Direction.NORTH);
				}
				else{
					bougerPlateau(Direction.SOUTH);
				}
				i = !i;
				if(time == 0){
					isViber = false;
					cancel();
					plateau.remove(popUp);
					plateau.setBounds(posX,plateau.getY(), plateau.getWidth(), plateau.getHeight());
					conteneur.repaint();
					
				}
				time--;
    		}
		},0,100);

		vibe.purge();
	}

	public void plateauMove(Data data){
		Colour c = data.getVainqueur().getColor();

		Timer vibe = new Timer();
		vibe.schedule(new TimerTask() {
			int time = 200;

    		public void run() {

				if(time<=80){
					bougerPlateau(Direction.EAST);
				}

				if(time == 0){
					cancel();
					panneauFinDeJeu = new PanneauFinDeJeu(c);
					panneauFinDeJeu.setBounds(View.this.getWidth()/2-200, View.this.getHeight()/2-150, 400, 300);
					panneauFinDeJeu.initialise();

					conteneur.add(panneauFinDeJeu);
					conteneur.repaint();

					panneauFinDeJeu.getButtonRejouer().addActionListener( e->{
						conteneur.remove(panneauFinDeJeu);
						rejouerJeu(data);
					});

					panneauFinDeJeu.getButtonQuitter().addActionListener(e ->{
						View.this.dispose();
						launcher.setVisible(true);
					});

					
					
				}
				time--;
    		}
		},0,10);
		vibe.purge();
	}



	public void rejouerJeu(Data obj){
		isOver=false;
		obj.reset();
		jv1.resetData();
		jv2.resetData();
		conteneur.repaint();
		Timer vibe = new Timer();
		vibe.schedule(new TimerTask() {
			int time = 80;
    		public void run() {

				bougerPlateau(Direction.WEST);

				if(time == 0){
					cancel();
					isReloading = false;
				}
				time--;
    		}
		},0,10);
		vibe.purge();
		
	}


	public void afficherPopUp(State state, String msg){
		PopUp popUp;
		if (msg == null) {
			popUp = new PopUp(state);
		}
		else {
			popUp = new PopUp(msg);
		}

		popUp.setBounds(plateau.getWidth()/2-100,plateau.getHeight()/2-50,250,100);
		plateau.add(popUp);
		plateau.repaint();
		Timer affiche = new Timer();
		affiche.schedule(new TimerTask() {	
			int time = 10;
    		public void run() {
				if(time == 0){
					cancel();
					plateau.remove(popUp);
					conteneur.repaint();
					isViber=false;
				}
				if (msg == null) {
					time -= 2;
				}
				else {
					time--;
				}
			}
		},0, 150);
		affiche.purge();
		
	}

	

	public void joueurSuivant(Data obj){
		int n = obj.getJoueurCurrent();
		joueurs[n].mettreBarre();
		joueurs[(n+1)%2].enleverBarre();
	}

	public int getTaille_case(){
		return taille_case;
	}

	@Override
	public void update(Data obj) {
		isTurnIA = obj.tourIA();
		if(obj.getVainqueur()!=null){

			animationVictoire();

			isOver=true;
			plateauMove(obj);
			return;
		}
		if(plateau==null){
			start(obj);
		}
		else {
			if (obj.getState() != State.PUSHOPPMARBLE && obj.getState() != State.PUSHREDMARBLE && obj.getState() != State.SUCCESS) {
				if(!isViber){
					isViber=true;
					vibrer(obj.getState());
				}
			} else {
				joueurs[obj.getJoueurCurrent()].updateBille(obj.billesCapturees());
				joueurs[obj.getJoueurCurrent()].repaint();

				if (joueurs[obj.getJoueurCurrent()] != null) {
					joueurSuivant(obj);
				}
				if(obj.tourIA()){
					IAturn(obj);
				}
				this.repaint();
			}
		}
	}

	public void IAturn(Data obj){
		Timer affiche = new Timer();
		affiche.schedule(new TimerTask() {
			int time = 5;

    		public void run() {
				if(time == 0){
					cancel();
					if (obj.tourIA()) {
						Controleur.nClicks ++;
					}
					plateau.getMouseListeners()[0].mouseReleased(null);
				}
				time--;
    		}
		},0, 150);
		affiche.purge();
	}

	public void animationVictoire(){
		AnimationVicory[] av = new AnimationVicory[4];
		double[] angles = {0,180,90,270};
		for (int i =0;i<av.length;i++){
			av[i] = new AnimationVicory(plateau.getX()+plateau.getWidth()/2, plateau.getY()+plateau.getHeight()/2, plateau.getWidth()/2, 0, conteneur);
			av[i].setAngle(angles[i]);
			av[i].setBounds(av[i].posX, av[i].posY, 100, 100);
			conteneur.add(av[i]);
			conteneur.repaint();
			av[i].anime();
		}
	}

}


