public class Test {
	
	public static void main(String[] args) throws IncorrectMoveException {
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.push(0,0,Direction.RIGHT,new Joueur(Color.WHITE,3,"Nicolas"),new Joueur(Color.BLACK,3,"Albin"));
		p.affiche();
	}
}