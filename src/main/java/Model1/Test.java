package main.java.Model1;
import main.java.MVC1.*;

public class Test {
	public static void main(String[] args) {
		Joueur j1 = new Joueur(Color.WHITE,3,"Nicolas");
		Joueur j2 = new Joueur(Color.BLACK,3,"Albin");
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.push(new Position(0,0),Direction.NORTH,j1,j2);
		p.push(new Position(2,0),Direction.EAST,j1,j2);
		p.push(new Position(2,1),Direction.EAST,j1,j2);
		p.push(new Position(3,0),Direction.WEST,j1,j2);
		p.affiche();
		IA ia = new IA(Color.BLACK,3);
		
		new View(null);
		
	}
}