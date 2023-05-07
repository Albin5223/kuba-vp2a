package GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import Model.ModeJeu;

public class Interrupteur extends JPanel {

    ModeJeu prev;
    ModeJeu mode;
    ModeJeu next;


    JLabel precedent;
    JLabel nom;
    JLabel suivant;

    public Interrupteur(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill=GridBagConstraints.HORIZONTAL;



        this.setOpaque(false);
        mode = ModeJeu.NORMAL;

        prev = mode.prev();
        next = mode.next();

        precedent = new JLabel(""+prev.toString(),SwingConstants.CENTER);

        precedent.setOpaque(false);
        precedent.setFont(new Font("Impact",Font.PLAIN,20));
        precedent.setForeground(new Color(0, 0, 0, 121));
        c.gridx=0;
        c.gridy=0;
        c.weightx=0.3;
        this.add(precedent,c);

        nom = new JLabel(""+mode.toString(),SwingConstants.CENTER);
        nom.setSize(200,50);
        nom.setOpaque(false);
        nom.setFont(new Font("Impact",Font.PLAIN,35));

        c.gridx=1;
        c.gridy=0;
        c.weightx=1.0;
        c.gridwidth=2;
        this.add(nom,c);

        suivant = new JLabel(""+next.toString(),SwingConstants.RIGHT);
        suivant.setOpaque(false);
        suivant.setFont(new Font("Impact",Font.PLAIN,20));
        suivant.setForeground(new Color(0, 0, 0, 121));
        c.gridy=0;
        c.gridx=3;
        c.weightx=0.5;

        this.add(suivant,c);

        
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
