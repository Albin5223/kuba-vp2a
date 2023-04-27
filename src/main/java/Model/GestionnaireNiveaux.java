package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionnaireNiveaux {

    static Model model;
    static File file;
    static int lignes;

    static ArrayList<Defi> tabDefi;

    public static void initialiser(){
        tabDefi = new ArrayList<>();
        file = new File("ressource/Editeur.txt");

        try{
          initialiseDefi();
        }catch(Exception e){
          System.out.println("Probleme avec l'ouverture du fichier Defi");
          System.exit(1);
        }
    }



    public static void addModel(Model m){
      model = m;
    }

    public static int getNbLignes(){
      return lignes;
    }

    private static void initialiseDefi() throws FileNotFoundException{
      Scanner scan = new Scanner(file);
      
      while(scan.hasNext()){
        Defi def = new Defi(scan.next());
        lignes++;
        tabDefi.add(def);
      }

      scan.close();
    }

    public static void ajouteDefi(){
      String plateau = model.getPlateau().toString();
      int taille = model.getN();
      int numero = tabDefi.size()+1;
      String name = "Defi-"+numero;

      Defi nouveau = new Defi(name,taille,plateau);
      String nouvelleLigne = nouveau.getLine();

      tabDefi.add(nouveau);
      lignes++;


      
      
      try {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(nouvelleLigne);

        out.close();
      } catch (IOException e) {
        System.out.println("Erreur dans l'enregistrement");
        System.exit(1);
      }

    }


    public static String getNiveaux(int n){
      return tabDefi.get(n).getPlateau();
    }
    
    public static void lancer(int niveauSelected){
        String plateau = getNiveaux(niveauSelected);
        
        Marble[][] nv = Plateau.stringToList(plateau);
        model.setBoard(nv);
    }
}
