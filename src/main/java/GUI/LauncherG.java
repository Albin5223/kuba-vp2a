package GUI;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Controleur.*;
import Model.*;

public class LauncherG extends JFrame{
	
	private JPanel fond = new JPanel();
	private JFrame fen=new JFrame("Kuba");
	private BufferedImage imagePlateau;
	JLabel label;
	JPanel contour;
	JPanel plateau ;
	int size;
	int y;
	int n;
	
	public LauncherG(int n,Colour [][] tab) {
		this.n = n;
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		fen.setResizable(false);

		int x = (Toolkit.getDefaultToolkit().getScreenSize().width- Toolkit.getDefaultToolkit().getScreenSize().height)/2;
		y = Toolkit.getDefaultToolkit().getScreenSize().height;

		contour = new JPanel ();
		contour.setBounds(x,0,y,y); //(x,0,y-10,y) si bug d'affichage en bas
		contour.setBorder(BorderFactory.createLineBorder(Color.black));
		contour.setBackground(Color.LIGHT_GRAY);


		size = y / (4*n-1);
		//imagePlateau = new BufferedImage(y, y, BufferedImage.TYPE_INT_ARGB);

		Controleur ctrl = new Controleur(null,size);
    	contour.addMouseListener(ctrl);
    	contour.addMouseMotionListener(ctrl);
		contour.setLayout(new GridLayout(1,1));



		label = new JLabel();
		label.setIcon(new ImageIcon(remplir(tab)));
		
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

	public BufferedImage remplir( Colour[][] tab){
		BufferedImage image1 = null;
		BufferedImage image2 = null;
		BufferedImage image3 = null;
		BufferedImage image4 = new BufferedImage(y, y, BufferedImage.TYPE_INT_ARGB);


		Graphics2D graph;
		for (int i =0; i< (4*n-1)*(4*n-1); i++){
				try {
					image1 = null;
					image2 = null;
						if (i==0) image1 = ImageIO.read(new File("src/ressource/coin01.png"));
						else {
							if (i==(4*n-2))image1 = ImageIO.read(new File("src/ressource/coin02.png"));
							else {
								if (i==(4*n-1)*(4*n-2)-1)image1 = ImageIO.read(new File("src/ressource/coin03.png"));
								else {
									if (i==(4*n-1)*(4*n-1)-1) image1 = ImageIO.read(new File("src/ressource/coin04.png"));
									else {
											if (i < (4*n-1))image1 = ImageIO.read(new File("src/ressource/haut.png"));
											else {
												if (i >(4*n-1)*(4*n-1)-(4*n-1))image1 = ImageIO.read(new File("src/ressource/bas.png"));
												else {
													if ( i %(4*n-1)==4*n-2)image1 = ImageIO.read(new File("src/ressource/droite.png"));
													else {
														if ( i %(4*n-1)==0)image1 = ImageIO.read(new File("src/ressource/gauche.png"));
														else {
															image1 = ImageIO.read(new File("src/ressource/intersection.png"));
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
					if (tab[a][b] != null && tab[a][b].toString().equals("W")) image2 = ImageIO.read(new File("src/ressource/BalleBlanche.png"));
					if (tab[a][b ] != null && tab[a][b].toString().equals("B")) image2 = ImageIO.read(new File("src/ressource/BalleNoire.png"));
					if (tab[a][b ] != null && tab[a][b].toString().equals("R")) image2 = ImageIO.read(new File("src/ressource/BalleRouge2.png"));
					graph = image4.createGraphics();
					image3 = assemblage(image1,image2);
					graph.drawImage(image3,a*size,b*size,null);
			}
			 catch (IOException e) {			
				e.printStackTrace();
			}
			
		}
		return image4;
		
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
		
		Plateau p = new Plateau(4);
		p.initialiseBille();
		LauncherG l = new LauncherG(4,(p.getBoard()));

	}



}





