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

	public Move nextMove(int nbMove){
		Move result = new Move(null, null);
		Scanner scan1 = new Scanner(this.line);
		scan1.useDelimiter(";");
		String nextMove = "" ;
		for (int i = 0; i < 3+nbMove; i++){
			scan1.next();
		}
		nextMove+=scan1.next();
		scan1.close();

		Scanner scan2 = new Scanner(nextMove);
		scan2.useDelimiter(",");

		String position = scan2.next();
		position = position.substring(1);
		int j = Integer.parseInt(position);

		position = scan2.next();
		position = position.substring(0,position.length()-1);
		int i = Integer.parseInt(position);
		result.pos = new Position(i, j);

		String direction = scan2.next();
		scan2.close();
		if(direction.equals("EAST")) {
				result.dir = Direction.EAST;
			}
		if(direction.equals("SOUTH")) {
				result.dir = Direction.SOUTH;
			}
		if(direction.equals("NORTH")) {
				result.dir = Direction.NORTH;
			}
		if(direction.equals("WEST")) {
				result.dir = Direction.WEST;
			}
		
		return result;	

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

