public class Test {
	public static void main(String[] args) {
		Plateau p = new Plateau(3,new Joueur(Color.WHITE,3,"Nicolas"),new Joueur(Color.BLACK,3,"Albin"));
		p.initialiseBille();
		p.push(new Position(0,0),Direction.DOWN);
		p.push(new Position(2,0),Direction.RIGHT);
		p.push(new Position(2,1),Direction.RIGHT);
		p.push(new Position(3,0),Direction.LEFT);
		p.affiche();
		IA ia = new IA(Color.BLACK,3);
	}
}