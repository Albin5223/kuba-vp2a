package GUI;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Menu extends JPanel {

    JLabel selection;
    JLabel taille;
    JPanel container;
    JPanel containerButton;

    JLabel[] fleches;
    int n;

    //fleche[0] = gauche;
    //fleche[1] = droite;
    
    
    public Menu() {
        container = new JPanel();
        container.setLayout(new GridLayout(1,3));
        container.setOpaque(false);
        this.setOpaque(false);
        n=3;

        taille=new JLabel(""+n);
        taille.setFont(new Font("Impact",Font.PLAIN,50));
        taille.setHorizontalAlignment(SwingConstants.CENTER);

        fleches = new JLabel[2];

        for(int i = 0;i<2;i++){
            fleches[i] = new JLabel(new ImageIcon("src/ressource/fleche"+i+".png"));
            fleches[i].setOpaque(false);
            fleches[i].setForeground(new Color(0,0,0,150));
        }
        container.add(fleches[0]);
        container.add(taille);
        container.add(fleches[1]);





        fleches[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(n==10){
                    n=0;
                }
                n-=1;
                taille.setText(n+"");
                container.repaint();
                
            }
        });

        fleches[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (n==1){
                    n=11;
                }
                n+=1;
                taille.setText(n+"");
                container.repaint();   
            }
        });

        this.add(container);

        this.setVisible(true);
        this.repaint();
    }

    public int getN(){
        return n;
    }

}
