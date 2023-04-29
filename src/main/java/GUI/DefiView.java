package GUI;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.ModeJeu;
import Model.Model;
import SearchFile.GestionnaireNiveaux;

public class DefiView extends JPanel{
    
    int id;
    JLabel nom;
    JLabel taille;
    JButton lancer;
    JFrame fenetre;

    public DefiView(int i,JFrame fenetre){
        this.setLayout(new GridLayout(3, 1));
        this.fenetre = fenetre;
        id=i;
        nom = new JLabel("Nom : "+GestionnaireNiveaux.getName(id));
        taille = new JLabel("Taille : "+GestionnaireNiveaux.getTaille(id));
        lancer = new JButton("JOUER");
        lancer.addActionListener(event -> {
            lancerNiveau();
        });

        this.add(nom);
        this.add(taille);
        this.add(lancer);
    }

    public void lancerNiveau(){
      
        int n = GestionnaireNiveaux.getTaille(id);
        Model m = null;
        try {
                fenetre.setVisible(false);
                m = new Model(n,ModeJeu.DEFI);
                View v = new View(n,fenetre);
                m.addObserveur(v);
                m.noticeObserveurs(m);
                GestionnaireNiveaux.addModel(m);
    
        } catch (CloneNotSupportedException e1) {
            System.out.println("Erreur dans le lancement du model dans Defi");
            System.exit(1);
        }
        
        GestionnaireNiveaux.lancer(id);
  
      }
    
}
