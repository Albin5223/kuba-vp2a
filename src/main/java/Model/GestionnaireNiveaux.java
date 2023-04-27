package Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;

public class GestionnaireNiveaux {
    static Model model;
    static File file;

    public static void initialiser(Model m){
        model = m;
        file = new File("ressource/Editeur.txt"); 
    }

    public static int nbLignes(){
        try
        {   
          FileReader fr = new FileReader(file);        
          BufferedReader br = new BufferedReader(fr);  
          int x = 0;
          while(br.readLine() != null) {  
            x++; 
          }
          return x;
          }
        catch(IOException e)
        {
          e.printStackTrace();
          return 0;
        }
      }

    public static void enregistrer(String s){
        try {
            String s2 = Integer.toString(model.n)+";"+model.plat.toString()+"\n";
            Files.write(Paths.get("ressource/Editeur.txt"),s.getBytes(),StandardOpenOption.APPEND);        
        }
        catch (IOException e){
        }
    }

    public static String getNiveaux(int n, boolean b){
        try{   
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
        return "erreur";
      }
    
    public static void lancer(int niveauSelected){
        String s = getNiveaux(niveauSelected,false);
        int count = 0;
        int i = 0;
        while (count < 2){
            if (s.charAt(i) != '!') i++;
            else {
                i++;
                count++;
            }
        }
        String result = "";
        for (int y = i; i<s.length(); i++){
            result += s.charAt(y);
        }
        Marble[][] nv = Plateau.stringToList(result);
        model.setBoard(nv);
    }
}
