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
        GridBagConstraints c= new GridBagConstraints();
        this.setOpaque(false);
        mode = ModeJeu.NORMAL;

        prev = mode.prev();
        next = mode.next();

        precedent = new JLabel(""+prev.toString(), SwingConstants.CENTER);
        precedent.setOpaque(false);
        precedent.setFont(new Font("Impact",Font.PLAIN,20));

        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=0;
        c.weightx=0.4;
        this.add(precedent,c);


        nom = new JLabel(""+mode.toString(),SwingConstants.CENTER);
        nom.setOpaque(false);
        nom.setFont(new Font("Impact",Font.PLAIN,35));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=1;
        c.weightx=0.8;
        this.add(nom,c);

        suivant = new JLabel(""+next.toString(),SwingConstants.CENTER);
        suivant.setOpaque(false);
        suivant.setFont(new Font("Impact",Font.PLAIN,20));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=2;
        c.weightx=0.4;
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
