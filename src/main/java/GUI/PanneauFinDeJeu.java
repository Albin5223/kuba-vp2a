package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import Model.Colour;
import SearchFile.*;

public class PanneauFinDeJeu extends JPanel{

    JButton rejouer;
    JButton quitter;
    JLabel afficheVainqueur;

    public PanneauFinDeJeu(Colour c){
        this.setLayout(null);

        afficheVainqueur = new JLabel("Le joueur "+c.getName()+" a gagné");
        rejouer = new JButton("Rejouer");
        quitter = new JButton("Quitter");
    }


    @Override
    public void paintComponent(Graphics g){
        g.drawImage(BanqueImage.images[6], 0, 0, null);
    }


    public void initialise(){
        this.setBackground(Color.RED);
        afficheVainqueur.setBounds( 80, 130, 200,25);


        quitter.setBounds(200,230,100,30);
        this.add(quitter);

        rejouer.setBounds(30,230,100,30);
        this.add(rejouer);
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
