package SearchFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurFichier {
    
    File file;
    Scanner scan;

    public LecteurFichier(File f) throws FileNotFoundException{
        file = f;
        scan = new Scanner(file, "UTF-8");
    }

    public boolean hasNext(){
        return scan.hasNext();
    }

    public String next(){
        return scan.nextLine();
    }

    public void close(){
        scan.close();
    }
}
