package GUI;


import javax.swing.*;

import Controleur.Controleur;
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
    String[] lettres = {"K","U","B","A"};
    JFrame fenetre;
    Menu menu;

    JLabel play;
    JLabel option;

    boolean menuActivated;


    public PanneauDemarrage(JFrame fen){
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
       
        container = new JPanel();
        fenetre = fen;
        JlabelLettres =new JLabel[4];
        container.setOpaque(false);
        for(int i = 0;i<4;i++){
            JlabelLettres[i] = new JLabel(lettres[i]);
            JlabelLettres[i].setFont(new Font("Impact",Font.PLAIN,200));
            JlabelLettres[i].setVisible(false);
            container.add(JlabelLettres[i]);
        }
        menu = new Menu();
        

        play=new JLabel("Play");
        play.setSize(100,100);
        play.setFont(new Font("Impact",Font.PLAIN,50));
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!menuActivated){
                    container.removeAll();
                    container.add(menu);
                    fenetre.revalidate();
                    menuActivated=!menuActivated;
                }
                else{
                    fenetre.setVisible(false);
                    int n = menu.getN();
                    Model m = new Model(n,false);
                    View v = new View(n,fenetre);
                    Controleur ctrl= new Controleur(m,v.getTaille_case());
                    m.setView(v);
                    v.addCtrl(ctrl);
                }
                
            }
        });
        play.setVisible(false);


        option=new JLabel("Options");
        option.setSize(100,100);
        option.setFont(new Font("Impact",Font.PLAIN,30));
        option.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!menuActivated){
                    container.removeAll();
                    container.add(menu);
                    fenetre.revalidate();
                    menuActivated=!menuActivated;
                }
                else{
                    fenetre.setVisible(false);
                    int n = menu.getN();
                    Model m = new Model(n,false);
                    View v = new View(n,fenetre);
                    Controleur ctrl= new Controleur(m,v.getTaille_case());
                    m.setView(v);
                    v.addCtrl(ctrl);
                }
                
            }
        });
        option.setVisible(false);


        this.add(container,BorderLayout.NORTH);

        containerButton = new JPanel();
        containerButton.setOpaque(false);
        containerButton.setLayout(new BorderLayout(100,200));
        containerButton.add(play,BorderLayout.CENTER);
        containerButton.add(option,BorderLayout.CENTER);
        this.add(containerButton);

       
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
                    play.setVisible(true);
                    option.setVisible(true);
                    fenetre.repaint();	
				}
				
				time--;
    		}
		},0,15 );
    }
}
