package GUI;

import java.awt.*;
import javax.swing.*;
import Model.*;

public class JoueurView extends JPanel{
    
    JLabel titre;
    JPanel barre;
    Colour couleur;
    

    int[] nbMarble;
    //Indice 0 : red
    //Indice 1 : Opp
    JPanel[] paneMarble;



    public JoueurView(Colour c){

        paneMarble = new JPanel[2]; 
        nbMarble = new int[2];
        this.setLayout(null);
        titre = new JLabel("");
        barre = new JPanel();
        mettreBarre();
        couleur = c;
        switch(couleur){
            case BLACK : titre.setText("Joueur NOIR");break;
            case WHITE : titre.setText("Joueur BLANC");break;
            default : titre.setText("Erreur");break;
        }
        titre.setForeground(Color.GREEN);
       
        
        this.add(barre);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(BanqueImage.imageBackgroundJoueurView,0,0,null);
        this.repaint();
        
    }

    public void initialisePaneMarbleCaptured(){
        titre.setBounds(this.getWidth()/3, 10, 100, 20);
        this.add(titre);
        BanqueImage.imageBackgroundJoueurView = BanqueImage.scaleImage(getWidth(), getHeight(),BanqueImage.imageBackgroundJoueurView);
    
        for(int i = 0;i<2;i++){
            int j =i;
            paneMarble[i] = new JPanel(){
                public void paintComponent(Graphics g){
                    paintMarble(g,j);
                }
            
            };
            
        }
        paneMarble[0].setBounds(25,this.getHeight()/4,this.getWidth()*7/8,this.getHeight()*2/7);
        paneMarble[1].setBounds(25,this.getHeight()*3/5,this.getWidth()*7/8,this.getHeight()*2/7);
        
        initDetailPanneau();
        
    }

    private void initDetailPanneau(){
        for (int i =0;i<2;i++){
            paneMarble[i].setBackground(Color.lightGray);
            paneMarble[i].setBorder(new MyBorder());
            this.add(paneMarble[i]);
        }
        
    }
    
    public void updateBille(int [][] nv){
        int i = (couleur.ordinal()+1)%2;
        nbMarble[0] = nv[i][0];
        nbMarble[1] = nv[i][1];
    }

    public void paintMarble(Graphics g ,int i){
        if (i==0){
            g.setColor(Color.red);
        }
        else{
            if (couleur == Colour.BLACK){
                g.setColor(Color.white);
            } 
            else{
                g.setColor(Color.black);
            }
        }
        int MarbleY = 5;
        int MarbleX = 5;
        for (int j = 0;j<nbMarble[i];j++){
            if(MarbleX>=paneMarble[i].getWidth()-25){
                MarbleY+=25;
                MarbleX = 5;
            }
            g.fillOval(MarbleX,MarbleY,15,15);
            MarbleX+=25;
        }
    }    

    public void mettreBarre(){
        barre.setBounds(0,0,20,this.getHeight());
        barre.setBackground(Color.RED);
    }

    public void enleverBarre(){
        barre.setBackground(Color.lightGray);
    }

    public void resetData(){
        nbMarble[0]=0;
        nbMarble[1]=0;
        this.repaint();
    }
    
}
