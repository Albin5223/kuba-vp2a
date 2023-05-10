package GUI;

import java.awt.*;

import javax.swing.*;

import Controleur.Controleur;
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
        this.setOpaque(false);
        this.fenetre = fenetre;
        id=i;
        String name=String.format("%15s",GestionnaireNiveaux.getName(id));
        nom = new JLabel("Nom : "+name, SwingConstants.CENTER);
        nom.setForeground(new Color(255, 255, 255));
        taille = new JLabel("Taille : "+GestionnaireNiveaux.getTaille(id),SwingConstants.CENTER);
        taille.setForeground(new Color(255, 255, 255));
        lancer = new JButton("JOUER");
        lancer.setForeground(Color.WHITE);
        lancer.setFont(new Font("SansSerif",Font.BOLD,20));
        lancer.setOpaque(false);
        lancer.setContentAreaFilled(false);
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
        
        fenetre.setVisible(false);
        m = new Model(n,ModeJeu.DEFI);
        View v = new View(n,fenetre);
        Controleur ctrl = new Controleur(m,v.getTaille_case());
        m.addObserveur(v);
        m.noticeObserveurs(m);
        v.addCtrl(ctrl);
        
        GestionnaireNiveaux.addModel(m);
    
       
        GestionnaireNiveaux.lancer(id);
  
      }
    
}
