package Model;

public class Test {
	
	public static void main(String[] args) {
		Joueur j1 = new Joueur(Colour.WHITE,3);
		Joueur j2 = new Joueur(Colour.BLACK,3);
		Plateau p = new Plateau(3,j1,j2);
		try {
			NoeudIA ia = new NoeudIA(p,j1,j2);
			Plateau.initialiseBille(p);
			Move move = NoeudIA.determineBestMove(p,3,j1,j2);
			//p.push(move.pos,move.dir,j1,j2);
			p.affiche();
		}
		catch (CloneNotSupportedException e) {
			System.out.println(e);
		}
	}
}