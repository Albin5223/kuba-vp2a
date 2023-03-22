package Model;

public class Test {
	
	public static void main(String[] args) {
		Joueur j1 = new Joueur(Colour.WHITE,3);
		Joueur j2 = new Joueur(Colour.BLACK,3);
		Plateau p = new Plateau(3,j1,j2);
		try {
			NoeudIA ia = new NoeudIA(p,j1,j2);
			Plateau.initialiseBille(p);
			Move move = NoeudIA.determineBestMove(p,5,j2,j1);
			System.out.println(p.push(move.pos,move.dir,j1,j2));
			System.out.println(move.pos.i + " " + move.pos.j);
			System.out.println(move.dir);
			p.affiche();
		}
		catch (CloneNotSupportedException e) {
			System.out.println(e);
		}
	}
}