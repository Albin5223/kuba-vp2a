import javax.imageio.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestImage extends JFrame {
	
	BufferedImage image ;
	int n ;
	Graphics2D graph;
	int size;
	JPanel panel;
	

	public TestImage(int n) {
		this.n = 4*n-1;
		//graph = image.createGraphics();
		panel = new JPanel();
		size = panel.getSize().height/n;
		JFrame fen = new JFrame();
		fen.setDefaultCloseOperation(EXIT_ON_CLOSE);
		BufferedImage image2 = null;
		try {
			image = ImageIO.read(new File("../../../ressource/BallesNoires.png"));
			image2 = ImageIO.read(new File("../../../ressource/intersection.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		image = assemblage(image,image2);
		label.setIcon(new ImageIcon(image));
		fen.setContentPane(label);
		fen.setVisible(true);
		}
	
	
	  public BufferedImage assemblage(BufferedImage img1, BufferedImage img2) {

	        BufferedImage buf = null;
	        if(img1 != null && img2 != null) {
	            int w1 = img1.getWidth(null);
	            int h1 = img1.getHeight(null);
	            int w2 = img2.getWidth(null);
	            int h2 = img2.getHeight(null);
	            int hMax = 0;
	            int wMax = 0;
	           
	            hMax = Math.max( h1 , h2);
	            wMax = w1+w2;
	            buf = new BufferedImage(wMax, hMax, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2 = buf.createGraphics();
	            g2.drawImage(img1, 150, 0, null);
	            g2.drawImage(img2, 150, 0, null);
	        }
	        return buf;   
	    }
	
	public int getPosition(int nbCase) {
		return panel.getSize().height/n;
	}
	
	public void main (String[] args) {
		

	}
}
