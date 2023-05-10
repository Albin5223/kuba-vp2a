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
    JLabel titre;
    JTextField nom;
    JFrame fenetre;

    public PanneauEnregistrement(JFrame fen){
        openSave = true;
        this.setLayout(null);
        nom = new JTextField();
        titre = new JLabel("Choisissez un nom");
        enregistrer = new JButton("Enregistrer");
    }

    public void initialise(){
        titre.setBounds( 100, 20, 200,25);
        nom.setFont(new Font("Impact",Font.PLAIN,30));

        nom.setBounds(50, 60, 200, 45);
        this.add(nom);

        enregistrer.setBounds(140,130,100,30);
        this.add(enregistrer);

        this.add(titre);

        this.repaint();
    }

    public JButton getEnregistrerButton(){
        return enregistrer;
    }


    public String getNom(){
        return nom.getText();
    }
}
