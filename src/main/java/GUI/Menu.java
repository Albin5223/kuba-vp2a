package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Controleur.Controleur;
import Model.Model;

public class Menu extends JPanel {

    JLabel selection;
    JLabel taille;
    JLabel retour;
    JPanel container;
    JPanel containerButton;
    JLabel[] fleches;
    int n;

    JLabel play;
    
    JSlider isIA;

    //fleche[0] = gauche;
    //fleche[1] = droite;
    JFrame fenetre;
    
    public Menu(JFrame fen) {
        this.setLayout(new BorderLayout());
        fenetre = fen;
        container = new JPanel();
        container.setLayout(new GridLayout(1,3));
        container.setOpaque(false);
        this.setOpaque(false);
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

        isIA=new JSlider(0,1);
        isIA.setPaintTrack(true);
        isIA.setOpaque(false);
        isIA.setBounds(550, 75+55*1, 300, 100);
       

        play=new JLabel("Play");
        play.setBounds(0, 0, 300, 100);
        play.setFont(new Font("Impact",Font.PLAIN,70));

        play.setHorizontalAlignment(SwingConstants.CENTER);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    fenetre.setVisible(false);
                    int n = getN();
                    Model m = new Model(n,isIA.getValue()==0,false,false);
                    View v = new View(n,fenetre);
                    Controleur ctrl= new Controleur(m,v.getTaille_case());
                    m.addObserveur(v);
                    m.getPlateau().initialiseBille();
                    m.noticeObserveurs(m);
                    v.addCtrl(ctrl);
            }
        });

        retour=new JLabel("Retour");
        retour.setBounds(550, 75+55*2, 300, 100);
        retour.setFont(new Font("Impact",Font.PLAIN,30));

        retour.setHorizontalAlignment(SwingConstants.CENTER);
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

        this.add(container,BorderLayout.NORTH);

        containerButton = new JPanel();
        containerButton.setOpaque(false);
        containerButton.setLayout(null);
        
        containerButton.add(play);
        containerButton.add(isIA);
        containerButton.add(retour);
        this.add(containerButton,BorderLayout.SOUTH);
        
        

        this.setVisible(true);
        this.repaint();
    }

    public int getN(){
        return n;
    }

}
