package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interrupteur extends JPanel {

    Image[] banqueImage;
    int actived = 0;

    JLabel nom;
    public Interrupteur(){
        this.setOpaque(false);
        banqueImage = new Image[2];
        nom = new JLabel("IA");
        nom.setOpaque(false);
        nom.setFont(new Font("Impact",Font.PLAIN,35));
        this.add(nom);

        try {
			for (int i = 0;i<banqueImage.length;i++){
				String s="ressource/IA"+i+".png";
				banqueImage[i] = ImageIO.read(new File(s));
			}
		}catch (IOException e) {
			System.out.println("Image des billes non touve");
		}
        nom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                change();
                repaint();
            }
        });
        
    }
    
    public void initialise(){
        for (int i = 0;i<banqueImage.length;i++){
            banqueImage[i] = banqueImage[i].getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST);
        }

        this.repaint();
    }  

    public int getValue(){
        return actived;
    }

    private void change(){
        actived +=1;
        actived = actived%2;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(banqueImage[actived],0,0,null);
        this.repaint();
    }
}
