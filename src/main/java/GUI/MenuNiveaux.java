package GUI;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuNiveaux extends JPanel {

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
        DefiView dv = new DefiView(i, fen);
        this.add(dv);
      }
      
    }

}
