package GUI;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        nbBille++;
        Random r = new Random();
        int i = r.nextInt(0,3);
        JLabel image = new JLabel(new ImageIcon(BanqueImage.banqueMarbleImages[i]));
        image.setOpaque(false);
        int x = r.nextInt(debut, fin);

        image.setBounds(x,500,50,50);
        this.add(image);
        this.repaint();
        Timer vibe = new Timer();
        vibe.schedule(new TimerTask() {
            int time = image.getY();
            
            public void run() {
                image.setBounds(x,time,50,50);
                fenetre.revalidate();
                if(time == 0){
                    cancel();
                    nbBille-=1;
                    remove(image);
                }
                time-=5;
        }
        },0,15);
        vibe.purge();
    }

    public boolean isFinish(){
        return nbBille==0;
    }


    public static void main(String[] args) {
        JFrame fenetre = new JFrame();
        fenetre.setSize(1020,600);
        fenetre.setVisible(true);

        TransitionPane tp = new TransitionPane(600,fenetre);
        fenetre.add(tp);
        tp.billeMontante();
    }
}
