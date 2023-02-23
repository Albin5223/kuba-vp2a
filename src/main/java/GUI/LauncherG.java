	
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LauncherG extends JFrame{
	
	private JPanel fond = new JPanel();
	private JFrame fen=new JFrame("Kuba");
	private BufferedImage imagePlateau;
	JLabel label;
	JPanel contour;
	JPanel plateau ;
	int size;
	int y;
	
	public LauncherG(int n) {
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		fen.setResizable(false);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width- Toolkit.getDefaultToolkit().getScreenSize().height)/2;
		y = Toolkit.getDefaultToolkit().getScreenSize().height;

		contour = new JPanel ();
		contour.setBounds(x,0,y,y); //(x,0,y-10,y) si bug d'affichage en bas
		contour.setBorder(BorderFactory.createLineBorder(Color.black));
		contour.setBackground(Color.LIGHT_GRAY);
		contour.setLayout(new GridLayout(1,1));

		size = y / (4*n-1);
		//imagePlateau = new BufferedImage(y, y, BufferedImage.TYPE_INT_ARGB);

		label = new JLabel();

		
		label.setVisible(true);

		plateau = new JPanel ();
		plateau.setVisible(true);

		plateau.add(label);
		plateau.setBackground(Color.LIGHT_GRAY);
		plateau.repaint();
		plateau.revalidate();


		contour.add(plateau);


		fond.setLayout(null);
		fond.setVisible(true);
		fond.add(contour);

		fen.setUndecorated(true);
		fen.setContentPane(fond);
		fen.setVisible(true);
		fen.repaint();
	}

	public BufferedImage remplir(Bille [][] tab){
		BufferedImage image1 = null;
		BufferedImage image2 = null;
		BufferedImage image3 = null;
		BufferedImage image4 = new BufferedImage(y, y, BufferedImage.TYPE_INT_ARGB);


		Graphics2D graph;
		for (int i =0; i< (4*n-1)*(4*n-1); i++){
				try {
					
						if (i==0) image1 = ImageIO.read(new File("../../../ressource/coin01.png"));
						else {
							if (i==(4*n-2))image1 = ImageIO.read(new File("../../../ressource/coin02.png"));
							else {
								if (i==(4*n-1)*(4*n-2)-1)image1 = ImageIO.read(new File("../../../ressource/coin03.png"));
								else {
									if (i==(4*n-1)*(4*n-1)-1) image1 = ImageIO.read(new File("../../../ressource/coin04.png"));	
									else {
											if (i < (4*n-1))image1 = ImageIO.read(new File("../../../ressource/haut.png"));
											else {
												if (i >(4*n-1)*(4*n-1)-(4*n-1))image1 = ImageIO.read(new File("../../../ressource/bas.png"));
												else {
													if ( i %(4*n-1)==4*n-2)image1 = ImageIO.read(new File("../../../ressource/droite.png"));
													else {
														if ( i %(4*n-1)==0)image1 = ImageIO.read(new File("../../../ressource/gauche.png"));
														else {
															image1 = ImageIO.read(new File("../../../ressource/intersection.png"));
															}
														}
													}
												}
											
									}
								}
							}
						}	
					int a =i/(4*n-1) ; // nb colonne
					int b = i %(4*n-1); // nb ligne
					if (tab[i/a][i %b ] != null && tab[i/a][i %b ].toString( ).equals("W")) image2 = ImageIO.read(new File("../../../ressource/BalleBlanche.png"));
					if (tab[i/a][i %b ] != null && tab[i/a][i %b ].toString().equals("B")) image2 = ImageIO.read(new File("../../../ressource/BalleNoire.png"));
					if (tab[i/a][i %b ] != null && tab[i/a][i % b].toString().equals("R")) image2 = ImageIO.read(new File("../../../ressource/BalleRouge2.png"));
					graph = image4.createGraphics();
					image3 = assemblage(image1,image2);
					graph.drawImage(image3,a*size,b*size,null);
			}
			 catch (IOException e) {			
				e.printStackTrace();
			}
			
		}
		label.setIcon(new ImageIcon(image4));
	}

	  	  public BufferedImage assemblage(BufferedImage img1, BufferedImage img2) {
	  		if (img2 == null) return img1;
	        BufferedImage buf = null;

            buf = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buf.createGraphics();
            g2.drawImage(img1, 0, 0,size,size, null);
            g2.drawImage(img2, 0, 0,size,size, null);
	        return buf;
	    }




	
	public static void main(String[] args) {
		LauncherG l = new LauncherG(4);

	}



}





