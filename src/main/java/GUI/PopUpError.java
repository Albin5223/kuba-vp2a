package GUI;

import Model.State;

import java.awt.*;

import javax.swing.*;

public class PopUpError extends JPanel{
    
    State erreur;

    public PopUpError(State state){
        JLabel message = new JLabel("Message");
        message.setBounds(0, 0, 250,100);
        message.setFont(new Font("Serif", Font.ROMAN_BASELINE, 25));
        message.setForeground(Color.black);
        erreur = state;
        switch(erreur){
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


}
