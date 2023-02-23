package GUI;


import javax.swing.*;
import java.awt.*;

public class PlateauG extends JPanel{
    int n;
    int longueur;
    int taille_case;

    public PlateauG(int n){
        this.n = n;
        this.longueur = 4*n -1;
        taille_case = this.getWidth()/longueur;
        paintComponent(getGraphics());
    }

    public void paintComponent(Graphics g){
        for (int i = 0;i<longueur;i++){
            for (int j = 0;j<longueur;j++){
                g.drawRect(10+i*taille_case, 10+j*taille_case,taille_case,taille_case);
            }
        } 
    }
}