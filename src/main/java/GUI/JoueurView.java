package GUI;

import java.awt.*;

import javax.swing.*;
import Model.*;

public class JoueurView extends JPanel{
    
    JLabel titre;
    JPanel barre;
    Colour couleur;
    int nbRedMarble;
    int nbOppMarble;
    JPanel paneRedMarble;
    JPanel paneOppMarble;

    public JoueurView(Joueur j){
        this.setLayout(null);
        titre = new JLabel("");
        barre = new JPanel();
        mettreBarre();
        couleur = j.getColor();
        switch(couleur){
            case BLACK : titre.setText("Joueur NOIR");break;
            case WHITE : titre.setText("Joueur BLANC");break;
            default : titre.setText("Erreur");break;
        }
        this.add(titre);
        this.add(barre);
        this.setBackground(Color.lightGray);
    }

    public void initialisePaneMarbleCaptured(){
        paneRedMarble = new JPanel();
        
        paneRedMarble = new JPanel(){
            public void paintComponent(Graphics g){
                g.setColor(Color.red);
                int MarbleY = 5;
                int MarbleX = 5;
                for (int i = 0;i<nbRedMarble;i++){
                    if(MarbleX>=paneRedMarble.getWidth()-25){
                        MarbleY+=25;
                        MarbleX = 5;
                    }
                    g.fillOval(MarbleX,MarbleY,15, 15);
                    MarbleX+=25;
                }
            }
        };

        paneRedMarble.setBackground(Color.lightGray);
        paneRedMarble.setBounds(25,this.getHeight()/4,this.getWidth()*7/8,this.getHeight()*2/7);
        paneRedMarble.setBorder(new MyBorder());
        this.add(paneRedMarble);
        
        paneOppMarble = new JPanel(){
            public void paintComponent(Graphics g){
                if (couleur == Colour.BLACK){
                    g.setColor(Color.white);
                }
                else{
                    g.setColor(Color.black);
                }
                int MarbleY = 5;
                int MarbleX = 5;
                for (int i = 0;i<nbOppMarble;i++){
                    if(MarbleX>=paneRedMarble.getWidth()-25){
                        MarbleY+=25;
                        MarbleX = 5;
                    }
                    g.fillOval(MarbleX,MarbleY,15, 15);
                    MarbleX+=25;
                }
                
            }
        };

        paneOppMarble.setBackground(Color.lightGray);
        paneOppMarble.setBounds(25,this.getHeight()*3/5,this.getWidth()*7/8,this.getHeight()*2/7);
        paneOppMarble.setBorder(new MyBorder());
        this.add(paneOppMarble);
    }


    public void addRedMarble(){
        nbRedMarble++;
        paneRedMarble.repaint();
    }

    public void addOpponentMarble(){
        nbOppMarble++;
        paneOppMarble.repaint();
    }

    public void mettreBarre(){
        barre.setBounds(0,0,20,this.getHeight());
        barre.setBackground(Color.RED);
    }

    public void enleverBarre(){
        barre.setBackground(Color.lightGray);
    }
    
}
