package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {
    JTextField taille;

    JButton launch;

    Image background;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(background, 0, 0, this);
    }

    public Menu() throws IOException {
        super();
        background =ImageIO.read(new File("src/ressource/ezgif.com-gif-maker.gif"));

        JPanel grid=new JPanel();
        grid.setLayout(new BoxLayout(grid,BoxLayout.Y_AXIS));
        this.setSize(1020,600);
        taille=new JTextField("8");

        launch=new JButton("Play");



        grid.add(taille);
        grid.add(launch);
        this.add(grid);
        this.setVisible(true);
        revalidate();
    }

}
