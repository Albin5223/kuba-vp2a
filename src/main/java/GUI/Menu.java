package GUI;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import SearchFile.*;

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


        n=2;

        taille=new JLabel(""+n);
        taille.setFont(new Font("Impact",Font.PLAIN,50));
        taille.setHorizontalAlignment(SwingConstants.CENTER);

        fleches = new JLabel[2];

        for(int i = 0;i<2;i++){
            fleches[i] = new JLabel(new ImageIcon(BanqueImage.fleches[i]));
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
                fleches[0].setIcon(new ImageIcon(BanqueImage.flechesHover[0]));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fleches[0].setIcon(new ImageIcon(BanqueImage.fleches[0]));

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
                fleches[1].setIcon(new ImageIcon(BanqueImage.flechesHover[1]));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fleches[1].setIcon(new ImageIcon(BanqueImage.fleches[1]));
            }
        });


        selectMode=new Interrupteur();
        selectMode.setBounds(550, 90+60, 300, 50);

        play=new JLabel("Play");
        play.setVisible(true);
        play.setBounds(650, 90, 300, 60);
        play.setFont(new Font("Impact",Font.PLAIN,50));

        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    
                    int n = getN();
                    Model m = null;
                    
                    if(selectMode.getValue() != ModeJeu.DEFI){

                        fenetre.setVisible(false);
                        m = new Model(n,selectMode.getValue());
                        if(selectMode.getValue() == ModeJeu.EDITION){
                            ViewEdition v = new ViewEdition(n,fenetre);
                            m.addObserveur(v);
                            m.noticeObserveurs(m);
                            v.initialisation();
                            ControleurEditeur ctrlEd= new ControleurEditeur(m,v.getTaille_case());
                            GestionnaireNiveaux.addModel(m);
                            v.addCtrlEditeur(ctrlEd);
                        }
                        else{
                            View v = new View(n,fenetre);
                            m.addObserveur(v);
                            m.noticeObserveurs(m);
                            Controleur ctrl = new Controleur(m,v.getTaille_case());
                            v.addCtrl(ctrl);
                        }
                        
                        
                    }
                    else{
                        ouvrirDefi();
                    }
                        
                        
                   
                    
            }
        });

        retour=new JLabel("Retour");
        retour.setBounds(650, 90+45*2, 300, 100);
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

    public void ouvrirDefi(){
        removeAll();
        MenuNiveaux menuNiveaux = new MenuNiveaux(GestionnaireNiveaux.getNbLignes(),fenetre);
        add(menuNiveaux);
        fenetre.revalidate();
    }

    public void setAllVisible(boolean b){
        containerButton.setVisible(b);
        for(int i = 0;i<fleches.length;i++){
            fleches[i].setVisible(b);
        }

        taille.setVisible(b);
    }

}
