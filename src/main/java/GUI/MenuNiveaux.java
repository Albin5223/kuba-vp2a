package GUI;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Model.GestionnaireNiveaux;
import Model.ModeJeu;
import Model.Model;

public class MenuNiveaux extends JPanel {

    int niveauSelected;
    private ArrayList<JButton> listButton = new ArrayList<JButton>();

    private JLabel retour;
    JFrame fenetre;


    public MenuNiveaux (int nbLignes,JFrame fen){
      fenetre = fen;

      this.setOpaque(false);
      retour = new JLabel("retour");
      retour.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            removeAll();
            Menu menu = new Menu(fenetre);
            fenetre.add(menu);
            fenetre.revalidate();
        }
      });
      this.add(retour);

      
      for(int i = 0; i < nbLignes; i++){
        String name = GestionnaireNiveaux.getName(i);
        JButton boutton = new JButton(name);
        boutton.setSize(i, i);
        boutton.putClientProperty("id", i);
        boutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            Object obj = boutton.getClientProperty("id"); 
            if (obj instanceof Integer) {
              niveauSelected = ((Integer)obj);
              lancerNiveau();
           }   
        }} );

      listButton.add(boutton);
      this.add(boutton);
      }
      
    }


    public void lancerNiveau(){
      
      int n = GestionnaireNiveaux.getTaille(niveauSelected);
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
      
      GestionnaireNiveaux.lancer(niveauSelected);

    }
}
