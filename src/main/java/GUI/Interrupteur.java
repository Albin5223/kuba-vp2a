package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.ModeJeu;

public class Interrupteur extends JPanel {

    ModeJeu prev;
    ModeJeu mode;
    ModeJeu next;


    JLabel precedent;
    JLabel nom;
    JLabel suivant;

    public Interrupteur(){
        this.setLayout(new GridLayout(1,3,20,50));
        this.setOpaque(false);
        mode = ModeJeu.NORMAL;

        prev = mode.prev();
        next = mode.next();

        precedent = new JLabel(""+prev.toString());
        precedent.setOpaque(false);
        precedent.setFont(new Font("Impact",Font.PLAIN,20));
        this.add(precedent);

        nom = new JLabel(""+mode.toString());
        nom.setOpaque(false);
        nom.setFont(new Font("Impact",Font.PLAIN,35));
        this.add(nom);

        suivant = new JLabel(""+next.toString());
        suivant.setOpaque(false);
        suivant.setFont(new Font("Impact",Font.PLAIN,20));
        this.add(suivant);

        
        suivant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                suivant();
                repaint();
            }
        });

        precedent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                precedent();
                repaint();
            }
        });
        
    }
    
    public ModeJeu getValue(){
        return mode;
    }

    private void updateLabel(){
        nom.setText(""+mode.toString());
        suivant.setText(""+next.toString());
        precedent.setText(""+prev.toString());
    }

    private void suivant(){
        prev = mode;
        mode = next;
        next = next.next();
        updateLabel();
    }

    private void precedent(){
        next = mode;
        mode = prev;
        prev = prev.prev();
        updateLabel();
    }

}
