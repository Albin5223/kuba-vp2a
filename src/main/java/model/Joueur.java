public class Joueur {
	private String name; //Nom pour l'esthétisme pour le graphic
	private Color color;//'b' pour noir (= black) et 'w' pour blanc (=white)
	private int nBilles;//pour savoir combien de billes il lui reste
	private int billesRougesCapturees;//si il en a capture la moitie il peut gagner

	public Joueur(Color c, int n, String name) {
		this.nBilles = 2*(n*n);
		this.color = c;
		this.name = name;
		this.billesRougesCapturees = 0;
	}

	public String gagne(){
		return "BRAVO, " + name+" ,Tu as gagné !!!!!";
	}

	public String getName(){
		return name;
	}

	public void loseMarble() {
		nBilles --;
	}

	public Color getColor() {
		return color;
	}

	public int getBilles() {
		return nBilles;
	}

	public void winRedMarble() {
		this.billesRougesCapturees ++;
	}

	public int getBillesRougesCapturees() {
		return billesRougesCapturees;
	}
}
