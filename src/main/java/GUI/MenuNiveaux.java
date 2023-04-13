package GUI;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class MenuNiveaux {

    private int nbniveaux = 0;

    public String ouvreDefi(int n) {
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
          return sb.toString();
        }
        catch(IOException e)
        {
          e.printStackTrace();
        }
        return "marche pas";
      }
    
    
    
}
