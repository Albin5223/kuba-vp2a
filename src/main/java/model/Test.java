public class Test {
	public static void main(String[] args) throws IncorrectMoveException {
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.affiche();
		p.push(0,0,'e',new Joueur (Color.WHITE,3), new Joueur (Color.BLACK,3));
		p.push(0,3,'s',new Joueur (Color.WHITE,3), new Joueur (Color.BLACK,3));
		p.push(1,3,'w',new Joueur (Color.WHITE,3), new Joueur (Color.BLACK,3));
		p.affiche();
	}
}