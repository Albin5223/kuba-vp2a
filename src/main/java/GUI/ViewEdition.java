package GUI;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Controleur.*;
import SearchFile.*;

public class ViewEdition extends View{

    PopUp popUp;
    public ViewEdition(int nb, JFrame l) {
        super(nb, l);
        
    }

    public void initialisation(){
        popUp = new PopUp("Mode solution activee");
		popUp.setBounds(plateau.getWidth()/2-100,getWidth()/2-50,250,100);
        popUp.setVisible(false);
        conteneur.add(popUp);
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
					ViewEdition.this.plateau.setVisible(false);
					PanneauEnregistrement pe = new PanneauEnregistrement(ViewEdition.this);
					pe.setBounds(ViewEdition.this.getWidth()/2-150, ViewEdition.this.getHeight()/2-100, 300, 200);
					pe.initialise();
					ViewEdition.this.add(pe);
					ViewEdition.this.repaint();

					pe.getEnregistrerButton().addActionListener( event->{
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
