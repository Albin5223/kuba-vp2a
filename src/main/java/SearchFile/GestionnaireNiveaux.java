package SearchFile;

import java.io.*;
import java.util.ArrayList;

import Model.Defi;
import Model.Marble;
import Model.Model;
import Model.Move;
import Model.Plateau;


public class GestionnaireNiveaux {

    static Model model;
    static File file;
    static int lignes;
    public static int numSelected;
    static int numCoup;
    static String savedPlat;

    static ArrayList<Defi> tabDefi;

    public static void initialiser(){
        tabDefi = new ArrayList<>();
        file = BanqueImage.fichierDefi;
        numSelected = -1;
        numCoup = 0;

        try{
          initialiseDefi();
        }catch(Exception e){
          System.out.println("Probleme avec l'initialisation du Gestionnaire Defi");
          System.exit(1);
        }
    }



    public static void addModel(Model m){
      model = m;
    }

    public static int getNbLignes(){
      return lignes;
    }

    public static void savePlateau(){
      savedPlat = model.getPlateau().toString();
    }

    private static void initialiseDefi() throws FileNotFoundException{
      LecteurFichier lecfic = new LecteurFichier(file);
      
      while(lecfic.hasNext()){
        Defi def = new Defi(lecfic.next());
        lignes++;
        tabDefi.add(def);
      }

      lecfic.close();
    }

    public static String getName(int n){
      return tabDefi.get(n).getName();
    }

    private static boolean isAlphaNumeric(String s) {
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        boolean b1 = Character.isLetter(c);
        boolean b2 = Character.isDigit(c);
        if (!b1 && !b2) {
          return false;
        }
      }
      return true;
    }

    public static void ajouteDefi(String name,String solution){
      int taille = model.getN();
      int numero = tabDefi.size()+1;
      if(name.length() == 0 || !isAlphaNumeric(name)){
        name = "Defi-"+numero;
      }

      Defi nouveau = new Defi(name,taille,savedPlat);
      String nouvelleLigne = nouveau.getLine();
      nouvelleLigne+=solution;
      nouveau.setLigne(nouvelleLigne);
      tabDefi.add(nouveau);
      lignes++;
      
      try {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(nouvelleLigne);

        out.close();
      } catch (IOException e) {
        System.out.println("Erreur dans l'enregistrement d'un Defi");
        System.exit(1);
      }

    }


    public static String getNiveaux(int n){
      return tabDefi.get(n).getPlateau();
    }

    public static int getTaille(int i){
      return tabDefi.get(i).getTaille();
    }
    
    public static void lancer(int niveauSelected){
        numSelected = niveauSelected;
        numCoup = 0 ;
        String plateau = getNiveaux(niveauSelected);
        Marble[][] nv = Plateau.stringToList(plateau);
        model.setBoard(nv);
    }

    public static Move getNextMove(){
      return tabDefi.get(numSelected).nextMove(numCoup);
    }

    public static boolean hasNextMove(){
      return tabDefi.get(numSelected).hasNextMove(numCoup);
    }

    public static void moveReussi(){
      numCoup++;
    }
}
