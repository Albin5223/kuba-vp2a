package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class MenuNiveaux extends JFrame {

    private int nbniveaux ;
    private int numI;
    public int result;

    public MenuNiveaux(){
      try
      {
        File file = new File("ressource/Editeur.txt");    
        FileReader fr = new FileReader(file);        
        BufferedReader br = new BufferedReader(fr);  
        int x = 0;
        while(br.readLine() != null) {  
          x++; 
        }
        nbniveaux = x;
        System.out.println(x);
        }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }

    public String getDefi(int n,boolean b) {
        try
        {
          File file = new File("ressource/Editeur.txt");    
          FileReader fr = new FileReader(file);        
          BufferedReader br = new BufferedReader(fr);  
          StringBuffer sb = new StringBuffer();    
          String line = "";
          int x = 0;
          while(x != n) {
            line = br.readLine();   
            x++; 
          }
          sb.append(line);      
          sb.append("\n"); 
          fr.close();    
          if (b){
            String s = "";
            int i = 0;
            while (sb.toString().charAt(i) != ';'){
              s+= sb.toString().charAt(i);
              i++;
            }
            return s;
          }
          return sb.toString();
        }
        catch(IOException e)
        {
          e.printStackTrace();
        }
        return "marche pas";
      }

    public void panneauOuverture (){
      JFrame fenêtre = new JFrame();
      fenêtre.setBounds(300, 100, 800, 500);
      fenêtre.setVisible(true);
      fenêtre.setLayout(new GridLayout(1,nbniveaux));
      JPanel panel = new JPanel();
      fenêtre.setContentPane(panel);
      fenêtre.setTitle("gestionnaire de Niveaux");
      for (int i = 0; i < nbniveaux; i++){
          JButton boutton = new JButton();
          boutton.setText(getDefi(i+1,true));
          numI = i;
          boutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
              System.out.println(getI());
             changeResult(getI());
          }} );

          panel.add(boutton);
      }
    }

    private void changeResult (int x){
      result = x;
    }
    private int getI(){ return numI;}

    
    
    
}
