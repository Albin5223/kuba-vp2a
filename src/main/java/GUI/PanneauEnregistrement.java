package GUI;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanneauEnregistrement extends JPanel{
    
    static boolean openSave;
    
    JButton enregistrer;
    JButton annuler;
    JLabel titre;
    JTextField nom;
    JFrame fenetre;

    public PanneauEnregistrement(JFrame fen){
        openSave = true;
        this.setLayout(null);
        nom = new JTextField();
        titre = new JLabel("Choisissez un nom");
        enregistrer = new JButton("Enregistrer");
        annuler = new JButton("Annuler");
    }

    public void initialise(){
        titre.setBounds( 100, 20, 200,25);
        nom.setFont(new Font("Impact",Font.PLAIN,30));

        nom.setBounds(50, 60, 200, 45);
        this.add(nom);

        enregistrer.setBounds(180,130,100,30);
        this.add(enregistrer);

        annuler.setBounds(30,130,100,30);
        this.add(annuler);
        this.add(titre);

        this.repaint();
    }

    public JButton getEnregistrerButton(){
        return enregistrer;
    }

    public JButton getAnnulerButton(){
        return annuler;
    }

    public String getNom(){
        return nom.getText();
    }
}
