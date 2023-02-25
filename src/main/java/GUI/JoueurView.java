package GUI;

import java.awt.*;

import javax.swing.*;
import Model.*;

public class JoueurView extends JPanel{
    
    JLabel titre;
    JPanel barre;
    Colour couleur;
    int redMarbleX = 5;
    int redMarbleY = 5;

    int oppMarbleX = 5;
    int oppMarbleY = 5;
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
                //TODO : regler l'affichage pour afficher toutes les lignes
                for (int i = 0;i<nbRedMarble;i++){
                    g.fillOval((i*25)+5,redMarbleY,15, 15);
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
                for (int i = 0;i<nbOppMarble;i++){
                    //TODO : regler l'affichage pour afficher toutes les lignes
                    g.fillOval((i*25)+5,oppMarbleY,15, 15);
                }
                
            }
        };

        paneOppMarble.setBackground(Color.lightGray);
        paneOppMarble.setBounds(25,this.getHeight()*3/5,this.getWidth()*7/8,this.getHeight()*2/7);
        paneOppMarble.setBorder(new MyBorder());
        this.add(paneOppMarble);



    }


    public void addRedMarble(){
        System.out.println("Add Marble Red : "+ redMarbleX+" , "+redMarbleY);
        nbRedMarble++;
        paneRedMarble.repaint();
        redMarbleX+=25;
        System.out.println("Taille : "+(paneRedMarble.getWidth()-25));
        if (redMarbleX>=paneRedMarble.getWidth()-25){
            redMarbleX=25;
            redMarbleY+=25;
            System.out.println("Augementation du redY");
        }
        
        
    }

    public void addOpponentMarble(){
        System.out.println("Add Marble OPP : "+ oppMarbleX+" , "+oppMarbleY);
        nbOppMarble++;
        paneOppMarble.repaint();
        oppMarbleX+=25;
        System.out.println("Taille : "+(paneOppMarble.getWidth()-25));
        if (oppMarbleX>=paneOppMarble.getWidth()-25){
            oppMarbleX=25;
            oppMarbleY+=25;
        }
        
    }

    public void mettreBarre(){
        barre.setBounds(0,0,20,this.getHeight());
        barre.setBackground(Color.RED);
    }

    public void enleverBarre(){
        barre.setBackground(Color.lightGray);
    }
    
}
