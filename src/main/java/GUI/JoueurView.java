package GUI;

import java.awt.Color;

import javax.swing.*;
import Model.*;

public class JoueurView extends JPanel{
    
    JLabel titre;
    JPanel barre;

    public JoueurView(Joueur j){
        titre = new JLabel("");

        barre = new JPanel();
        mettreBarre();
        switch(j.getColor()){
            case BLACK : titre.setText("Joueur NOIR");break;
            case WHITE : titre.setText("Joueur BLANC");break;
            default : titre.setText("Erreur");break;
        }
        this.add(titre);
        this.add(barre);
        this.setBackground(Color.lightGray);
    }

    public void addRedMarble(){

    }

    public void addOpponentMarble(){

    }

    public void mettreBarre(){
        barre.setBounds(0,0,20,this.getHeight());
        barre.setBackground(Color.RED);
    }
    public void enleverBarre(){
        barre.setBackground(Color.lightGray);
    }






    
}
