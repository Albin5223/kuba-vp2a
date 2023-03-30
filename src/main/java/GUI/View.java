package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

import Controleur.Controleur;
import Model.*;

public class View extends JFrame implements Observeur<Data>{
    
    
	int longueur;
	int n;
	boolean isOver;
	boolean isReloading;
	int taille_case;
	JPanel plateau;
	JPanel conteneur;
	JoueurView jv1;
	JoueurView jv2;
	JoueurView currentJoueur;
	OptionView optView;

	Image imageBackground;

	JFrame launcher;

	Image[] banqueMarblImages;
	Image imageBackgroundScale;
    public View(int nb,JFrame l) {
		launcher = l;
		this.setTitle("Plateau KUBA");
		banqueMarblImages = new Image[3];

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
    	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);

 		n = nb;


		longueur = 4*n -1;
        taille_case = ((this.getHeight()-100)/longueur)*7/8;

		//Trouver une boone image de fond
		try {
			imageBackground = ImageIO.read(new File("src/ressource/background.jpg"));
			for (int i = 0;i<3;i++){
				String s="src/ressource/Balle"+i+".png";
				Image marble = ImageIO.read(new File(s));
				Image marbleScaled = marble.getScaledInstance(taille_case,taille_case,Image.SCALE_FAST);
				banqueMarblImages[i] = marbleScaled;
			}

		} catch (IOException e1) {
			System.out.println("Image non trouvé");
			e1.printStackTrace();
		}

		imageBackgroundScale=imageBackground.getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_FAST);

		conteneur = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(imageBackgroundScale,0,0,null);
				this.repaint();
				
			}
		};
		conteneur.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		conteneur.setLayout(null);
    }

	public void deployerPanneau(boolean ouverture){
		Timer vibe = new Timer();
		if(ouverture){
			optView.visibility(true);
		}
		System.out.println((optView.getWidth()-optView.icWidth)/10);
		vibe.schedule(new TimerTask() {
			int time = (optView.getWidth()-optView.icWidth)/11;
			public void run() {
				if(ouverture){
					optView.setBounds(optView.getX()-10,optView.getY(), optView.getWidth(), optView.getHeight());
				}
				else{
					optView.setBounds(optView.getX()+10,optView.getY(), optView.getWidth(), optView.getHeight());
				}
				

				if(time == 0){
					cancel();
					
				}
				time--;
			}
		},0,10);
		if(!ouverture) optView.visibility(false);
	}

	public void start(Data obj){
		plateau = new JPanel(){
			public void paintComponent(Graphics g){
				g.setColor(Color.black);
				for (int i = 0;i<longueur-1;i++){
					for (int j = 0;j<longueur-1;j++){
						g.drawRect(taille_case/2+i*taille_case,taille_case/2+j*taille_case,taille_case,taille_case);
					}
				}
				try {
					updatePlateau(g,obj);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		plateau.setBounds(this.getWidth()/2-taille_case*longueur/2,this.getHeight()/2-taille_case*longueur/2,taille_case*longueur+1,taille_case*longueur+1);
		jv1 = new JoueurView(Colour.WHITE);
		int taille_Jv = plateau.getX()-20;
		jv1.setBounds(10,plateau.getY(),taille_Jv,longueur*taille_case/3);
		jv1.initialisePaneMarbleCaptured();
		jv1.mettreBarre();
		currentJoueur = jv1;
		jv2 = new JoueurView(Colour.BLACK);
		jv2.setBounds(10,plateau.getY()+longueur*taille_case/2,taille_Jv,longueur*taille_case/3);
		jv2.initialisePaneMarbleCaptured();

		

		optView = new OptionView(this,launcher);
		optView.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-300,50, 300,200);
		deployerPanneau(false);

		optView.getReplayLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
				if(!isOver && !isReloading){
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
				optView.getReplayLabel().setForeground(Color.GRAY);
			}
        });


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

	public void updatePlateau(Graphics g,Data plateau) throws IOException {
		for (int i = 0;i<longueur;i++){
			for (int j = 0;j<longueur;j++){
				Colour c = plateau.getMarble(j , i);
				if (c != null){
					g.drawImage(banqueMarblImages[c.ordinal()],i*taille_case,j*taille_case,null);
							
				}
			}
		}
	}

	public void bougerPlateau(Direction d){
		plateau.setBounds(plateau.getX()+d.dirX()*20,plateau.getY()+d.dirY()*10, plateau.getWidth(), plateau.getHeight());
	}


	public void vibrer(State state){
		Timer vibe = new Timer();
		vibe.schedule(new TimerTask() {
			int time = 4;
			boolean i = false;
			int posX = plateau.getX();

    		public void run() {
				if(i){
					bougerPlateau(Direction.NORTH);
				}
				else{
					bougerPlateau(Direction.SOUTH);
				}
				i=!i;
				if(time == 0){
					cancel();
					plateau.setBounds(posX,plateau.getY(), plateau.getWidth(), plateau.getHeight());
					afficherPopUp(state);

				}
				time--;
    		}
		},0,100);
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
					
					PanneauFinDeJeu panneauFinDeJeu = new PanneauFinDeJeu(c);
					panneauFinDeJeu.setBounds(View.this.getWidth()/2-150, View.this.getHeight()/2-100, 300, 200);
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
		
	}


	public void afficherPopUp(State state){
		PopUpError popUp = new PopUpError(state);
		
		popUp.setBounds(plateau.getWidth()/2-100,plateau.getHeight()/2-50,250,100);
		plateau.add(popUp);
		plateau.repaint();
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

	

	public void joueurSuivant(Data obj){
		if (obj.getJoueur().getColor()==Colour.WHITE){
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

	public int getTaille_case(){
		return taille_case;
	}
	@Override
	public void update(Data obj) {
		if(plateau==null){
			start(obj);
		}
		else {
			if (obj.getState() != State.PUSHOPPMARBLE && obj.getState() != State.PUSHREDMARBLE && obj.getState() != State.SUCCESS) {
				vibrer(obj.getState());
			} else {
				if (obj.getState() == State.PUSHOPPMARBLE) {
					currentJoueur.addMarble(1);
					currentJoueur.repaint();
				} else {
					if (obj.getState() == State.PUSHREDMARBLE) {
						currentJoueur.addMarble(0);
						currentJoueur.repaint();
					}
				}
				if (currentJoueur != null) {
					joueurSuivant(obj);
				}

			}
			this.repaint();
			if(obj.getVainqueur()!=null){
				isOver=true;
				plateauMove(obj);
			}
		}
		
	}
}
