package GUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Controleur.*;
import SearchFile.*;

public class ViewEdition extends View{

	JTextPane racc;

    PopUp popUp;
    public ViewEdition(int nb, JFrame l) {
        super(nb, l);

    }

    public void initialisation() {

		JPanel bg = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(BanqueImage.images[9], -15, 0, null);
			}
		};
		bg.setOpaque(false);

		racc = new JTextPane();

		StyledDocument doc = racc.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();;
		racc.setForeground(new Color(238, 179, 97));
		racc.setEditable(false);
		racc.setOpaque(false);
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		racc.setDocument(doc);
		racc.setText("Mode résolution: Ctrl+R \n Sauvegarder niveau: Ctrl+S");
		racc.setFont(new Font("Impact", Font.PLAIN, 15));

		racc.setBounds(70,60,150,150);


		bg.setBounds(plateau.getX() + plateau.getWidth(), jv2.getY(), 300, 180);
		popUp = new PopUp("Mode solution activee");
		popUp.setBounds(plateau.getWidth() / 2 - 100, getWidth() / 2 - 50, 250, 100);
		popUp.setVisible(false);
		bg.setVisible(true);
		conteneur.add(popUp);
		bg.add(racc);
		bg.add(new JLabel(""));
		conteneur.add(bg);
		conteneur.repaint();
	}


    public void addCtrlEditeur (ControleurEditeur ctrl){
		plateau.addMouseListener(ctrl);
		plateau.addMouseMotionListener(ctrl);

		this.addKeyListener(new KeyListener() {
			boolean controlPressed;
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					controlPressed = true;
					
				}
				if(e.getKeyCode() == KeyEvent.VK_R && controlPressed && !PanneauEnregistrement.openSave){
					ControleurEditeur.SolutionMod = true;
					GestionnaireNiveaux.savePlateau();
					
                    popUp.setVisible(true);
					
				}
				if (e.getKeyCode() == KeyEvent.VK_S && controlPressed && !PanneauEnregistrement.openSave && ControleurEditeur.SolutionMod){
					
					isOver = true;
					ViewEdition.this.plateau.setVisible(false);
					PanneauEnregistrement pe = new PanneauEnregistrement(ViewEdition.this);
					pe.setBounds(ViewEdition.this.getWidth()/2-150, ViewEdition.this.getHeight()/2-100, 300, 200);
					pe.initialise();
					ViewEdition.this.add(pe);
					ViewEdition.this.repaint();

					pe.getEnregistrerButton().addActionListener( event->{
						isOver = false;
						ViewEdition.this.remove(pe);
						GestionnaireNiveaux.ajouteDefi(pe.getNom(),ControleurEditeur.solutions);
						ViewEdition.this.plateau.setVisible(true);
						ViewEdition.this.requestFocus();
						PanneauEnregistrement.openSave = false;
						afficherPopUp(null,"Sauvegarde réussie");
						ControleurEditeur.SolutionMod = false;
                        popUp.setVisible(false);
					});

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					controlPressed = false;
				}
			}
			
		});
	}
}
