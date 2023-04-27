package GUI;


import javax.swing.*;

import Controleur.*;
import Model.ModeJeu;
import Model.Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class PanneauDemarrage extends JPanel{

    JPanel container;
    JPanel containerButton;
    JLabel[] JlabelLettres;

    String[] lettres = {"C","U","B","A"};
    JFrame fenetre;
    Menu menu;

    JLabel[] button;
    /*
     * button[0] = play
     * button[1] = modeEdition
     * button[2] = quitter
     */


    public PanneauDemarrage(JFrame fen) {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
       
        container = new JPanel();

        fenetre = fen;
        JlabelLettres =new JLabel[4];
        container.setOpaque(false);
        for(int i = 0;i<4;i++){
            JlabelLettres[i] = new JLabel(lettres[i]);
            JlabelLettres[i].setFont(new Font("Impact",Font.PLAIN,150));
            JlabelLettres[i].setVisible(false);
            container.add(JlabelLettres[i]);
        }
        menu = new Menu(fenetre);


        containerButton = new JPanel();
        containerButton.setOpaque(false);
       
        containerButton.setLayout(null);


        String[] nom = {"Play","Mode Edition","Quitter"};
        button = new JLabel[3];
        for (int i = 0;i<button.length;i++){
            button[i]=new JLabel(nom[i]);
            button[i].setFont(new Font("Dyuthi",Font.PLAIN,50-(5*i)));
            button[i].setVisible(false);
            button[i].setHorizontalAlignment(SwingConstants.CENTER);
            button[i].setBounds(550, 76+53*i, 300, 100);
            containerButton.add(button[i]);
            
        }

        

        
        button[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToMenu();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button[0].setFont(new Font("Dyuthi",Font.BOLD,button[0].getFont().getSize()));
                button[0].setForeground(new Color(50,200,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button[0].setFont(new Font("Dyuthi",Font.PLAIN,button[0].getFont().getSize()));

                button[0].setForeground(new Color(38, 38, 38));
            }
        });
        
        
        button[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fenetre.setVisible(false);
                Model m;
                try {
                    m = new Model(3,ModeJeu.EDITION);
                    View v = new View(3,fenetre);
                    ControleurEditeur ctrl= new ControleurEditeur(m,v.getTaille_case());

                    m.addObserveur(v);
                    
                    m.noticeObserveurs(m);
                    v.addCtrlEditeur(ctrl);
                } catch (CloneNotSupportedException e1) {
                    System.out.println("Probleme avec le Clone du model");
                }
                
            }

            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button[1].setFont(new Font("Dyuthi",Font.BOLD,button[1].getFont().getSize()));
                button[1].setForeground(new Color(50,200,255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button[1].setFont(new Font("Dyuthi",Font.PLAIN,button[1].getFont().getSize()));
                button[1].setForeground(new Color(38, 38, 38));
            }
        });

        button[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fenetre.dispose();
                System.exit(0);
            }

            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button[2].setFont(new Font("Dyuthi",Font.BOLD,button[2].getFont().getSize()));
                button[2].setForeground(new Color(50,200,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button[2].setFont(new Font("Dyuthi",Font.PLAIN,button[1].getFont().getSize()));
                button[2].setForeground(new Color(38, 38, 38));
            }
        });
        


        this.add(container,BorderLayout.NORTH);

        
        this.add(containerButton,BorderLayout.CENTER);

       
    }

 
    public void anime(){
        Timer vibe = new Timer();
		vibe.schedule(new TimerTask() {
			int time = 80;
            int i = 0;
    		public void run() {
                if(time %25==0 && time !=100){
                    
                    JlabelLettres[i].setVisible(true);
                    fenetre.repaint();
                    i++;
                }
                if(time == -6){
					cancel();
                    for(int i = 0;i<button.length;i++){
                        button[i].setVisible(true);
                    }
                    fenetre.repaint();	
				}
				
				time--;
    		}
		},10,15 );
    }


    public void goToMenu(){
        this.removeAll();
        BanqueImage.scaleMarble(50);
        TransitionPane tp = new TransitionPane(1020,fenetre);
        this.add(tp);
        this.repaint();
        Timer vibe = new Timer();
		vibe.schedule(new TimerTask() {
			
            int size = 10;
    		public void run() {
                if(size<200){
                    tp.billeMontante();
                    size+=10;
                }
                else{
                    if(tp.isFinish()){
                        cancel();
                        PanneauDemarrage.this.remove(tp);
                        PanneauDemarrage.this.add(menu);   
                        fenetre.revalidate();
                    } 
                }
    		}
		},0,20 );
    }

    public void affiche(){
        for(int i = 0;i<4;i++){
            JlabelLettres[i].setVisible(true);
        }
        for(int i = 0;i<3;i++){
            button[i].setVisible(true);
        }
    }
}
