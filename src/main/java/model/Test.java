package main.java.Model1;
import main.java.MVC1.*;

public class Test {
	public static void main(String[] args) {
		Joueur j1 = new Joueur(Color.WHITE,3,"Nicolas");
		Joueur j2 = new Joueur(Color.BLACK,3,"Albin");
		Plateau p = new Plateau(3);
		p.initialiseBille();
		System.out.println(p.push(new Position(2,2),Direction.NORTH,j1,j2));
		//System.out.println(p.push(new Position(1,2),Direction.SOUTH,j1,j2));
		//System.out.println(p.push(new Position(2,2),Direction.SOUTH,j1,j2));
		p.affiche();
		IA ia = new IA(Color.BLACK,3);
		//new View(null);
		
	}
}