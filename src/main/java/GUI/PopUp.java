package GUI;

import Model.State;

import java.awt.*;

import javax.swing.*;

public class PopUp extends JPanel{

    JLabel message;

    public PopUp(State state){
        message = new JLabel("Message");
        message.setBounds(0, 0, 250,100);
        message.setFont(new Font("Serif", Font.ROMAN_BASELINE, 25));
        message.setForeground(Color.black);
        switch(state){
            case EMPTYTILE : message.setText("Case vide");break;
            case MARBLEOWNERSHIPERROR : message.setText("Bille invalide");break;
            case PUSHINGOWNMARBLE :  message.setText("Mouvement invalide");break;
	        case REPEATINGBOARD :  message.setText("Plateau deja existant");break;
	        case TILEBEFORENOTEMPTY :  message.setText("Mouvement invalide");break;
	        case WRONGDIRECTION :  message.setText("Direction invalide");break;
            default : break;
        }
        this.setBackground(new Color(150,150,150,150));
        this.add(message);
    }

    public PopUp(String msg) {
        message = new JLabel(msg);
        message.setBounds(0, 0, 250,100);
        message.setFont(new Font("Serif", Font.ROMAN_BASELINE, 25));
        message.setForeground(Color.black);
        this.setBackground(new Color(150,150,150,150));
        this.add(message);
    }
}
