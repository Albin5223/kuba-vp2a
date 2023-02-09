public class Test {
	public static void main(String[] args) {
		Joueur j1 = new Joueur(Color.WHITE,3,"Nicolas");
		Joueur j2 = new Joueur(Color.BLACK,3,"Albin");
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.push(new Position(0,1),Direction.EAST,j1,j2);
		p.affiche();
		IA ia = new IA(Color.BLACK,3);
	}
}