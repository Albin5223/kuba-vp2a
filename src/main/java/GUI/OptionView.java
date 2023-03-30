package GUI;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionView extends JPanel {

    JCheckBox isIA;
    public OptionView(){
        this.setOpaque(false);
        isIA = new JCheckBox("IA ?");

        this.add(isIA);
    }




    public static void main(String[] args) {
        OptionView option = new OptionView();
        JFrame fen = new JFrame();
        fen.setVisible(true);
        fen.setSize(1020,600);
        fen.add(option);
    }
    
}
