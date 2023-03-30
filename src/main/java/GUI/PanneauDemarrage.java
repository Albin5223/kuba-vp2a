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
    JCheckBox isIA;

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
        isIA=new JCheckBox("IA");

        isIA.setSize(100,100);
        isIA.setFont(new Font("Impact",Font.PLAIN,30));
        isIA.setHorizontalAlignment(SwingConstants.CENTER);

       
        isIA.setVisible(false);

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
                    isIA.setVisible(true);
                }
                else{
                    fenetre.setVisible(false);
                    int n = menu.getN();
                    Model m = new Model(n,isIA.isSelected(),false);
                    View v = new View(n,fenetre);
                    Controleur ctrl= new Controleur(m,v.getTaille_case());
                    m.addObserveur(v);
                    m.getPlateau().initialiseBille();
                    m.noticeObserveurs(m);
                    v.addCtrl(ctrl);
                }
                
            }
        });
        play.setVisible(false);


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
        containerButton.add(isIA,gbc);
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
                    fenetre.repaint();	
				}
				
				time--;
    		}
		},0,15 );
    }
}
