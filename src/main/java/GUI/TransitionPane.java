package GUI;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SearchFile.*;

public class TransitionPane extends JPanel {


    int debut;
    int fin;

    int nbBille;
    //Cet entier correspond au nombre de bille en cours d'aafichage
    JFrame fenetre;
    

    public TransitionPane(int width,JFrame fen){
        fenetre =fen;
        this.setOpaque(false);
        this.setLayout(null);
        this.debut = 50;
        this.fin =  width-50;
        


    }


    public void billeMontante(){
        nbBille+=2;
        Random r = new Random();

        int i = r.nextInt(0,3);
        JLabel imageMarble = new JLabel(new ImageIcon(BanqueImage.banqueMarbleImages[i]));

        imageMarble.setOpaque(false);
        int x = r.nextInt(debut, fin);

        int j = r.nextInt(0,BanqueImage.imagePets.length);
        JLabel imagePets = new JLabel(new ImageIcon(BanqueImage.imagePets[j]));

        imagePets.setOpaque(false);
        int x1 = r.nextInt(debut, fin);

        imageMarble.setBounds(x,500,50,50);
        this.add(imageMarble);

        imagePets.setBounds(x1,500,50,50);
        this.add(imagePets);


        this.repaint();
        Timer vibe = new Timer();
        vibe.schedule(new TimerTask() {
            int time = imageMarble.getY();
            
            public void run() {
                imagePets.setBounds(x1,time,50,50);
                imageMarble.setBounds(x,time,50,50);
                fenetre.revalidate();
                if(time == 0){
                    cancel();
                    nbBille-=2;
                    remove(imageMarble);
                    remove(imagePets);
                }
                time-=5;
        }
        },0,15);
        vibe.purge();
    }

    public boolean isFinish(){
        return nbBille==0;
    }
 
}
