package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Controleur.*;
import Model.ModeJeu;
import Model.Model;

public class Menu extends JPanel {

    JLabel selection;
    JLabel taille;
    
    JPanel container;
    JPanel containerButton;
    JLabel[] fleches;
    int n;

    JLabel play;
    Interrupteur selectMode;
    JLabel retour;

    //fleche[0] = gauche;
    //fleche[1] = droite;
    JFrame fenetre;
    
    public Menu(JFrame fen) {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        fenetre = fen;
        container = new JPanel();
        container.setLayout(new GridLayout(1,3));
        container.setOpaque(false);
        

        containerButton = new JPanel();
        containerButton.setOpaque(false);
        containerButton.setLayout(null);


        n=3;

        taille=new JLabel(""+n);
        taille.setFont(new Font("Impact",Font.PLAIN,50));
        taille.setHorizontalAlignment(SwingConstants.CENTER);

        fleches = new JLabel[2];

        for(int i = 0;i<2;i++){
            fleches[i] = new JLabel(new ImageIcon("ressource/fleche"+i+".png"));
            fleches[i].setOpaque(false);
            fleches[i].setForeground(new Color(0,0,0,150));
        }
        container.add(fleches[0]);
        container.add(taille);
        container.add(fleches[1]);

        
        fleches[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if (n==1){
                    n=11;
                }
                n-=1;
                taille.setText(n+"");
                container.repaint();

            }
        });

        fleches[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(n==10){
                    n=0;
                }
                n+=1;
                taille.setText(n+"");
                container.repaint();   
            }
        });


        selectMode=new Interrupteur();
        selectMode.setBounds(550, 120+60*1, 300, 50);

        play=new JLabel("Play");
        play.setVisible(true);
        play.setBounds(650, 115, 300, 60);
        play.setFont(new Font("Impact",Font.PLAIN,50));

        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    fenetre.setVisible(false);
                    int n = getN();
                    Model m = null;
                    try {
                        m = new Model(n,selectMode.getValue());
                        System.out.println("Mode : "+selectMode.getValue());
                        View v = new View(n,fenetre);
                        m.addObserveur(v);
                        m.noticeObserveurs(m);
                        if(selectMode.getValue()==ModeJeu.EDITION){
                            ControleurEditeur ctrl= new ControleurEditeur(m,v.getTaille_case());
                            v.addCtrlEditeur(ctrl);
                        }
                        else{
                            Controleur ctrl= new Controleur(m,v.getTaille_case());
                            v.addCtrl(ctrl);
                        }
                    } catch (CloneNotSupportedException e1) {
                        System.out.println("Erreur dans le lancement du model dans Menu");
                    }
                    
            }
        });

        retour=new JLabel("Retour");
        retour.setBounds(650, 100+50*2, 300, 100);
        retour.setFont(new Font("Impact",Font.PLAIN,30));

        retour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeAll();
                PanneauDemarrage pd = new PanneauDemarrage(fenetre);
                add(pd);
                pd.affiche();
                fenetre.revalidate();
            }
        });

        containerButton.add(play);
        containerButton.add(selectMode);
        containerButton.add(retour);

        this.add(container,BorderLayout.NORTH);
        this.add(containerButton,BorderLayout.CENTER);
        
        this.repaint();
    }

    public int getN(){
        return n;
    }

}
