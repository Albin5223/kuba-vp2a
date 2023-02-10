package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Window extends JFrame {
    JLabel content;

    JLabel play;

    JLabel title;

    public Window(){
        this.setSize(1020,600);
        this.content=new JLabel(new ImageIcon("src/ressource/ezgif.com-gif-maker.gif"));
        setContentPane(content);
        this.setLayout(new BorderLayout());
        play=new JLabel("Play");
        play.setHorizontalAlignment(SwingConstants.CENTER);
        play.setSize(100,100);
        play.setFont(new Font("Impact",Font.PLAIN,50));
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu menu;
                try {
                    menu = new Menu();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                setContentPane(menu);
                repaint();
            }
        });

        title=new JLabel("KUUUBAAAAAA");
        title.setFont(new Font("Sylfaen",Font.BOLD,70));
        title.setForeground(new Color(145, 23, 8));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(play);
        this.add(title,BorderLayout.NORTH);
        this.setVisible(true);


    }

    public static void main(String[] args) {
        new Window();
    }
}


