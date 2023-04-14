package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import Model.Colour;

public class PanneauFinDeJeu extends JPanel{

    JButton rejouer;
    JButton quitter;
    JLabel afficheVainqueur;
    JLabel afficheDemande;


    Image background;


    public PanneauFinDeJeu(Colour c,Image img){
        this.setLayout(null);
        background = img;
        afficheDemande = new JLabel("Voulez-vous rejouer?");
        afficheDemande.setForeground(new Color(155,255,155));
        afficheVainqueur = new JLabel("Le joueur "+c.getName()+" a gagné");
        rejouer = new JButton("Rejouer");
        quitter = new JButton("Quitter");
    }


    @Override
    public void paintComponent(Graphics g){
        g.drawImage(background, 0, 0, null);
    }


    public void initialise(){
        this.setBackground(Color.RED);
        afficheVainqueur.setBounds( 80, 0, 200,25);
        afficheDemande.setBounds(90, 50, 200,25);


        quitter.setBounds(175,100,100,30);
        this.add(quitter);

        rejouer.setBounds(30,100,100,30);
        this.add(rejouer);
        this.add(afficheDemande);
        this.add(afficheVainqueur);

        this.repaint();
    }

    public JButton getButtonRejouer(){
        return rejouer;
    }

    public JButton getButtonQuitter(){
        return quitter;
    }
    
}
