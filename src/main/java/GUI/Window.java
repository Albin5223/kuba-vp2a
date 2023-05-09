package GUI;

import javax.swing.*;
import SearchFile.*;

import java.awt.*;

public class Window extends JFrame {
    JLabel content;


    PanneauDemarrage pd;

    public Window(){
        BanqueImage.charger();
        BanqueImage.scaleMarble(50);
        GestionnaireNiveaux.initialiser();
        
        this.setTitle("Launcher KUBA");
        pd = new PanneauDemarrage(this);
        this.setSize(1020,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setIconImage(BanqueImage.images[8]);

        
        Image iconImage = BanqueImage.images[3].getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST);

        ImageIcon imic = new ImageIcon(iconImage);

        this.content=new JLabel(imic);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);
        this.setLayout(new BorderLayout());
        this.add(pd);
        pd.anime();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					new Window();
				}
			}
		);
    }
}