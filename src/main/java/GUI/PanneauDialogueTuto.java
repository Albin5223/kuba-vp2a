package GUI;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import SearchFile.LecteurFichier;



public class PanneauDialogueTuto extends JPanel{

    String message;
    LecteurFichier lecfic;
    JLabel Jmessage;
    JTextArea textArea;
    View view;
    

    public PanneauDialogueTuto(File f,View v) throws FileNotFoundException{
        view = v;
        lecfic = new LecteurFichier(f);
        Jmessage = new JLabel("*Cliquez ici pour continuer*");
        Jmessage.setFont(new Font("Impact",Font.PLAIN,10));
        textArea = new JTextArea("Bonjour la famille");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.setText(nextMessage());
                PanneauDialogueTuto.this.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            
            
        });
    }

    

    public void initialise(){
        textArea.setBounds(0,0,this.getWidth(),this.getHeight() - 50);
        this.add(textArea,CENTER_ALIGNMENT);

        this.add(Jmessage);
    }

    public String nextMessage(){
        if(lecfic.hasNext()){
            
            String nextLine = lecfic.next();
            if(nextLine.length() != 0 && nextLine.charAt(0) == '&'){
                requestVisibility(nextLine.substring(1));
                if(lecfic.hasNext()){
                    nextLine = lecfic.next();
                }
            }
            String nextMessage = nextLine;
            while(lecfic.hasNext() && nextLine.length() != 0){
                nextLine = lecfic.next();
                if(nextLine.length() != 0 && nextLine.charAt(0) == '&'){
                    requestVisibility(nextLine.substring(1));
                    if(lecfic.hasNext()){
                        nextLine = lecfic.next();
                    }
                }
                nextMessage+= " \n"+nextLine;
            }
            return nextMessage;
        }
        else{
            view.dispose();
            view.launcher.setVisible(true);
            return Jmessage.getText();
        }
        
    }
    public void requestVisibility(String element){
        switch(element){
            case "plateau" : view.plateau.setVisible(true);break;
            case "joueur-0" : view.joueurs[0].setVisible(true);break;
            case "joueur-1" : view.joueurs[1].setVisible(true);break;
            default : break;
        }
    }
    
}
