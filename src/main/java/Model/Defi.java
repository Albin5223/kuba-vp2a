package Model;

import java.util.Scanner;

public class Defi{

	String name;
	int taille;
	String plateau;
	String line;

	public Defi(String ligne){
		line = ligne;
		Scanner scan = new Scanner(ligne);
		scan.useDelimiter(";");
		name = scan.next();
		String sub = scan.next();
		plateau = scan.next();
		taille = Integer.parseInt(sub);
		scan.close();
	}

	public Defi(String n, int nb, String plateau){
		name = n;
		taille = nb;
		this.plateau = plateau;
		line = n+";"+taille+";"+plateau;
	}


	public void affiche(){
		System.out.println(name+" "+taille+" "+plateau);
	}


	public String getName(){
		return name;
	}

	public int getTaille(){
		return taille;
	}

	public String getPlateau(){
		return plateau;
	}

	public String getLine(){
		return line;
	}
}

