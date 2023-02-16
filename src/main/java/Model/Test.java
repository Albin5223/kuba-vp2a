package Model;
import MVC.*;

public class Test {
	
	public static void main(String[] args) {
		Joueur j1 = new Joueur(Color.WHITE,3,"Nicolas");
		Joueur j2 = new Joueur(Color.BLACK,3,"Albin");
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.push(new Position(0,0),Direction.SOUTH,j1,j2);
		p.affiche();
		//IA ia = new IA(Color.BLACK,3);
		new View(null);

		System.out.println(p.toString());
		System.out.println(p.toString());



		System.out.println(p.push(new Position(3,0),Direction.NORTH,j1,j2));
		p.affiche();

		System.out.println(p.push(new Position(1,0),Direction.SOUTH,j1,j2));
		p.affiche();
		System.out.println(p.toString());


		String chaine= "-1W2-5B3-1W2-2R1-2B3W3-1R3-1B3W1-2R5-3W1-1R7-3R9-3R7-5R5-3B3-1R3-1W3B3-2R1-2W3B3-5W3";
		Bille[][] res = Plateau.stringToList(chaine);

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res.length; j++) {
				if (res[i][j] == null) {
					System.out.print("-");
				}
				else {
					System.out.print(res[i][j].toString());
				}
			}
			System.out.println();
		}
	}
}