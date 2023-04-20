package GUI;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
    //fleche[0] = gauche;
    //fleche[1] = droite;
    int n;

    JLabel play;
    Interrupteur selectMode;
    JLabel retour;


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
            BufferedImage im=null;
            try {
                im= ImageIO.read(new File("src/ressource/fleche"+i+".png"));
            } catch (IOException e) {
                System.out.println("image des fleche");
            }

            fleches[i] = new JLabel(new ImageIcon (im.getScaledInstance(200,200, Image.SCALE_SMOOTH)));
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

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                BufferedImage im=null;
                try {
                    im= ImageIO.read(new File("src/ressource/flecheHover0.png"));
                } catch (IOException ex) {
                    System.out.println("image des fleche hover");
                }
                fleches[0].setIcon(new ImageIcon(im.getScaledInstance(200,200, Image.SCALE_SMOOTH)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                BufferedImage im=null;
                try {
                    im= ImageIO.read(new File("src/ressource/fleche0.png"));
                } catch (IOException ex) {
                    System.out.println("image des fleche hover");
                }
                fleches[0].setIcon(new ImageIcon(im.getScaledInstance(200,200, Image.SCALE_SMOOTH)));

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

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                BufferedImage im=null;
                try {
                    im= ImageIO.read(new File("src/ressource/flecheHover1.png"));
                } catch (IOException ex) {
                    System.out.println("image des fleche hover");
                    System.exit(1);
                }
                fleches[1].setIcon(new ImageIcon(im.getScaledInstance(200,200, Image.SCALE_SMOOTH)));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                BufferedImage im=null;
                try {
                    im= ImageIO.read(new File("src/ressource/fleche1.png"));
                } catch (IOException ex) {
                    System.out.println("image des fleche hover");
                }
                fleches[1].setIcon(new ImageIcon(im.getScaledInstance(200,200, Image.SCALE_SMOOTH)));
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
