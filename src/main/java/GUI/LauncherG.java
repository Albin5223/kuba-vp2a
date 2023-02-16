	
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;


public class LauncherG extends JFrame{
	
	private JPanel plateauG = new JPanel();
	private JFrame fen=new JFrame("Kuba");
	private JLayeredPane pane;
	
	public LauncherG(int n) {
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		fen.setResizable(false);
	
		JPanel contour = new JPanel ();
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width- Toolkit.getDefaultToolkit().getScreenSize().height)/2;

		contour.setBounds(x,0,Toolkit.getDefaultToolkit().getScreenSize().height-10,Toolkit.getDefaultToolkit().getScreenSize().height);
		contour.setBorder(BorderFactory.createLineBorder(Color.black));
		contour.setBackground(Color.LIGHT_GRAY);
		contour.setLayout(null);
		
		JPanel cases = new JPanel ();
		cases.setLayout(new GridLayout(4*n-1,4*n-1));
		cases.setBounds(40,40,Toolkit.getDefaultToolkit().getScreenSize().height-90,Toolkit.getDefaultToolkit().getScreenSize().height-80);
		for (int i =0; i< (4*n-1)*(4*n-1); i++) {
			if (i==0) cases.add(new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\coin01.png")));
			else {
				if (i==(4*n-2))cases.add( new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\coin02.png")));
				else {
					if (i==(4*n-1)*(4*n-1)-(4*n-1))cases.add( new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\coin03.png")));
					else {
						if (i==(4*n-1)*(4*n-1)-1) cases.add( new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\coin04.png")));	
						else {
							if (i>0) {
								if (i < (4*n-1))cases.add(new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\haut.png")));
								else {
									if (i >(4*n-1)*(4*n-1)-(4*n-1))cases.add(new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\bas.png")));
									else {
										if ( i %(4*n-1)==4*n-2)cases.add(new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\droite.png")));
										else {
											if ( i %(4*n-1)==0)cases.add(new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\gauche.png")));
											else {
												cases.add(new JLabel(new ImageIcon("C:\\uni\\projet\\kuba\\Kuba\\src\\intersection.png")));
											}
										}
									}
								}
							}
					}
				}
			}
		}	
		}
		cases.setBackground(Color.LIGHT_GRAY);
		contour.add(cases);
		plateauG.setLayout(null);
		plateauG.setVisible(true);
		plateauG.add(contour);
		fen.setUndecorated(true);
		fen.setContentPane(plateauG);
		fen.setVisible(true);
		fen.repaint();
	}
	
	public static void main(String[] args) {
		//LauncherG l = new LauncherG(3);
		TestImage yolo = new TestImage(3);
	}

}





