package GUI;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import SearchFile.BanqueImage;

public class ViewTuto extends View{

    public ViewTuto(int nb, JFrame l) {
        super(nb, l);
    }

    public void ajouterTextPanel(){
        PanneauMessage pm;
        try {
            pm = new PanneauMessage(BanqueImage.fichierTuto,this);
            pm.setBounds(this.plateau.getX()+plateau.getWidth()+30,this.joueurs[0].getY(), 300, 500);

            pm.initialise();

            this.add(pm);
            this.repaint();
        
        } catch (FileNotFoundException e) {
            System.out.println("Probleme d'ouverture avec le fichier texte tuto");
            System.exit(1);
        }
    }


    public void MiseEnPlaceTuto(){
        for(int i = 0;i<2;i++){
            joueurs[i].setVisible(false);
        }
        plateau.setVisible(false);
    }
    
}
