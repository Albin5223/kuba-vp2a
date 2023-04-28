package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BanqueImage {
    static Image[] banqueMarbleImages;
    /* 
    0 : BLACK,
	1 : WHITE,
	2 : RED;
    */

    static Image imageBackgroundPlateau;
	static Image imagePanneauFinDeJeu;
    static Image imageBackgroundJoueurView;
    static Image imageBackgroundMenu;
    static Image imageIcon;
    static Image imageEtoile;

    static Image[] fleches;
    static Image[] flechesHover;

    static Image[] imagePets;


	static Image[] banquePowerImages;

    public static void scaleMarble(int taille_case){
        for (int i = 0;i<3;i++){
            banqueMarbleImages[i] = banqueMarbleImages[i].getScaledInstance(taille_case,taille_case,Image.SCALE_FAST);
            banquePowerImages[i] = banquePowerImages[i].getScaledInstance(taille_case,taille_case,Image.SCALE_FAST);
        }
    }

    public static Image scaleImage(int width,int height,Image img){
        img = img.getScaledInstance(width,height,Image.SCALE_FAST);

        return img;
    }

    public static void scaleFleches(){
        for (int i = 0;i<2;i++){
            fleches[i] = fleches[i].getScaledInstance(200,200, Image.SCALE_SMOOTH);
            flechesHover[i] = flechesHover[i].getScaledInstance(200,200, Image.SCALE_SMOOTH);
        }
    }

    public static void scalePets(){
        for (int i = 0;i<imagePets.length;i++){
            imagePets[i] = imagePets[i].getScaledInstance(60,60, Image.SCALE_SMOOTH);
        }
    }

    public static void charger(){
        String path = "ressource/Images/";
        imagePets = new Image[1];
        fleches = new Image[2];
        flechesHover = new Image[2];
        banqueMarbleImages = new Image[3];
        banquePowerImages = new Image[3];
        try {
            imageEtoile = ImageIO.read(new File(path+"etoile.png"));
            imageIcon = ImageIO.read(new File(path+"iconDerouler.png"));
            imageBackgroundMenu = ImageIO.read(new File(path+"background4.jpg"));
            imageBackgroundJoueurView = ImageIO.read(new File(path+"panneau.jpg"));
			imageBackgroundPlateau = ImageIO.read(new File(path+"background3.jpg"));
			imagePanneauFinDeJeu = ImageIO.read(new File(path+"end_screen.png"));
			for (int i = 0;i<3;i++){
				String s=path+"Balle"+i+".png";
				Image marble = ImageIO.read(new File(s));
				banqueMarbleImages[i] = marble;
				s=path+"Power"+i+".png";
				marble = ImageIO.read(new File(s));
				banquePowerImages[i] = marble;
			}
            for (int i = 0;i<2;i++){
                fleches[i]= ImageIO.read(new File(path+"fleche"+i+".png"));
                flechesHover[i]= ImageIO.read(new File(path+"flecheHover"+i+".png"));

            }

            for(int i = 0;i<imagePets.length;i++){
                imagePets[i] = ImageIO.read(new File(path+"pets"+i+".png"));
            }

		}catch (IOException e) {
			System.out.println("Erreur lors de la recherche des Images");
            System.exit(1);
		}
        
        imageEtoile = imageEtoile.getScaledInstance(100,100,Image.SCALE_FAST);
        imagePanneauFinDeJeu = imagePanneauFinDeJeu.getScaledInstance(400,300,Image.SCALE_FAST);
        int icWidth = BanqueImage.imageIcon.getWidth(null)/3;
		int icHeight = BanqueImage.imageIcon.getHeight(null)/3;
		BanqueImage.imageIcon = BanqueImage.scaleImage(icWidth, icHeight, BanqueImage.imageIcon);
        scaleFleches();
        scalePets();
    }
}
