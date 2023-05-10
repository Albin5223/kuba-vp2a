package GUI;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import SearchFile.BanqueImage;

public class AnimationVicory extends JPanel {

    //x = x0 + r*cos(t)
    //y = y0 + r*sin(t)

    //où (x0,y0) sont les coord du centre, r est le rayon, et t l'angle.
    //L'angle est en radian

    int centreX;
    int centreY;
    int rayon;
    double angle;

    JPanel conteneur;

    int posX;
    int posY;

    
    

    public AnimationVicory(int x,int y, int r, double a,JPanel conteneur){
        this.setOpaque(false);
        centreX = x;
        centreY = y;
        rayon =r;
        angle = a;
        miseAJourPosition();
        this.conteneur = conteneur;

        
        repaint();
    }

    public AnimationVicory(AnimationVicory av){
        this(av.centreX, av.centreY, av.rayon, av.angle, av.conteneur);
    }

    public void setAngle(double a){
        angle = a;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(BanqueImage.images[1],0,0,null);
        
    }

    public void miseAJourPosition(){
        double angleR = angle * Math.PI/180;
        posX = (int) (centreX + rayon*Math.cos(angleR));
        posY = (int) (centreY + rayon*Math.sin(angleR));
    }

    public void next(){
        angle +=1;
        miseAJourPosition();
    }

    public void anime(){
        Timer vibe = new Timer();
		
		vibe.schedule(new TimerTask() {
			int count = 0;
			public void run() {
                setBounds(posX, posY,AnimationVicory.this.getWidth(), AnimationVicory.this.getHeight());
                conteneur.repaint();
				if(count == 2*360){
					cancel();
					conteneur.remove(AnimationVicory.this);
				}
				count++;
                next();
			}
		},0,5);
    }
}
