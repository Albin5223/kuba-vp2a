package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.ModeJeu;

public class Interrupteur extends JPanel {

    ModeJeu mode;

    JLabel nom;
    public Interrupteur(){
        this.setOpaque(false);
        mode = ModeJeu.NORMAL;
        nom = new JLabel(""+mode.toString());
        nom.setOpaque(false);
        nom.setFont(new Font("Impact",Font.PLAIN,35));
        this.add(nom);

        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                change();
                repaint();
            }
        });
        
    }
    
    public ModeJeu getValue(){
        return mode;
    }

    private void change(){
        mode = mode.next();
        nom.setText(""+mode.toString());
    }

}
