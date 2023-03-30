package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JLabel content;


    PanneauDemarrage pd;

    public Window(){
        this.setTitle("Launcher KUBA");
        pd = new PanneauDemarrage(this);
        this.setSize(1020,600);
        this.setVisible(true);
        this.content=new JLabel(new ImageIcon("ressource/ezgif.com-gif-maker.gif"));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);
        this.setLayout(new BorderLayout());
        this.add(pd);
        pd.anime();
    }

    public static void main(String[] args) {
        new Window();
    }
}