package GUI;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Model.GestionnaireNiveaux;

public class MenuNiveaux extends JPanel {

    int niveauSelected;
    private ArrayList<JButton> listButton = new ArrayList<JButton>();

    private JLabel retour;
    View view;
    JFrame fenetre;


    public MenuNiveaux (int nbLignes,View v,JFrame fen){
      fenetre = fen;
      view = v;
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

        JButton boutton = new JButton();
        boutton.setSize(i, i);
        boutton.putClientProperty("id", i);
        boutton.setName("Defi - "+i);
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
      GestionnaireNiveaux.lancer(niveauSelected);
      view.setVisible(true);
    }
}
