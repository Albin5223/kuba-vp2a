package GUI;


import javax.swing.*;

public class Menu extends JPanel {
    JTextField taille;
    JButton launch;
    
    public Menu() {


        this.setOpaque(false);

        JPanel grid=new JPanel();
        grid.setLayout(new BoxLayout(grid,BoxLayout.Y_AXIS));
        this.setSize(1020,600);
        taille=new JTextField("8");

        launch=new JButton("Play");



        grid.add(taille);
        grid.add(launch);
        this.add(grid);
        this.setVisible(true);

        this.repaint();
    }

}
