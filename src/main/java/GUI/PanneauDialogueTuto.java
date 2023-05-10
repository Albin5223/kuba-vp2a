package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.text.*;

import SearchFile.LecteurFichier;



public class PanneauDialogueTuto extends JPanel{

    String message;
    LecteurFichier lecfic;
    JLabel Jmessage;
    JTextPane textArea;
    View view;
    

    public PanneauDialogueTuto(File f,View v) throws FileNotFoundException{
        view = v;
        lecfic = new LecteurFichier(f);
        Jmessage = new JLabel("*Cliquez ici pour continuer*");
        Jmessage.setFont(new Font("Impact",Font.PLAIN,10));
        textArea = new JTextPane();
        textArea.setEditable(false);
        //textArea.setLineWrap(true);
        textArea.setOpaque(false);


        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        textArea.setDocument(doc);

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.setText(nextMessage());
                StyledDocument doc = textArea.getStyledDocument();
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

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
        this.setLayout(new GridBagLayout());
        GridBagConstraints c=new GridBagConstraints();
        textArea.setBorder(BorderFactory.createEmptyBorder());
        c.fill=GridBagConstraints.VERTICAL;
        c.gridx=0;
        c.gridy=0;
        c.weighty=0.4;
        this.add(new JLabel(" "),c);
        c.gridx=0;
        c.gridy=1;
        c.weighty=0.6;
        this.add(textArea,c);
        c.fill=GridBagConstraints.VERTICAL;
        c.gridx=0;
        c.gridy=2;
        c.weighty=0.4;
        this.add(Jmessage,c);
        c.fill=GridBagConstraints.VERTICAL;
        c.gridx=0;
        c.gridy=3;
        c.weighty=0.4;
        this.add(new JLabel(" "),c);
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
