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


    public PanneauDemarrage(JFrame fen) {
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
        play.setSize(100,200);
        play.setFont(new Font("Impact",Font.PLAIN,70));

        play.setHorizontalAlignment(SwingConstants.CENTER);
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
                    Model m;
                    try {
                        m = new Model(n,false);
                    } catch (CloneNotSupportedException except) {
                        System.out.println(except);
                        return;
                    }
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
        option.setHorizontalAlignment(SwingConstants.CENTER);

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
                    Model m;
                    try {
                        m = new Model(n,true);
                    } catch (CloneNotSupportedException except) {
                        System.out.println(except);
                        return;
                    }
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
        containerButton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(0,0,60,0);
        containerButton.add(play,gbc);
        containerButton.add(option,gbc);
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
                    play.setVisible(true);
                    option.setVisible(true);
                    fenetre.repaint();	
				}
				
				time--;
    		}
		},0,15 );
    }
}
