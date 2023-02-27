package GUI;

import Model.State;

import java.awt.*;

import javax.swing.*;

public class PopUpError extends JPanel{
    
    State erreur;

    public PopUpError(State state){
        JLabel message = new JLabel("Message");
        this.setOpaque(false);
        message.setBounds(200, 25, 500,500);
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
        this.add(message);
    }


}
