package GUI;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Menu extends JPanel {
    JLabel taille;
    JButton launch;

    JPanel container;
    JPanel containerButton;
    JPanel containerText;

    JLabel[] fleches;
    int n;

    //fleche[0] = haut;
    //fleche[1] = bas;
    
    
    public Menu() {
        containerText = new JPanel();
        containerText.setOpaque(false);
        container = new JPanel();
        container.setLayout(new GridLayout(1,2));
        container.setOpaque(false);
        this.setOpaque(false);
        n=3;
        taille=new JLabel(""+n);
        taille.setFont(new Font("Impact",Font.PLAIN,50));
        container.add(taille);



        containerButton = new JPanel();
        containerButton.setLayout(new GridLayout(2, 1));
        containerButton.setOpaque(false);
        fleches = new JLabel[2];

        for (int i = 0;i<2;i++){
            fleches[i] = new JLabel(new ImageIcon("ressource/fleche"+i+".png"));
            fleches[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    if (n==15){
                        n=0;
                    }
                    n+=1;
                    taille.setText(n+"");
                    container.repaint();
                    
                }
            });
            containerButton.add(fleches[i]);

        }



        container.add(containerText);
        container.add(containerButton);
        this.add(container);

        this.setVisible(true);
        this.repaint();
    }

}
