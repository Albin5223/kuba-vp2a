package Model;

public class Joueur {
	private Color color;//'b' pour noir (= black) et 'w' pour blanc (=white)
	private int nBilles;//pour savoir combien de billes il lui reste
	private int billesRougesCapturees;//si il en a capture la moitie il peut gagner

	public Joueur(Color c, int n, String name) {
		this.nBilles = 2*(n*n);
		this.color = c;
		this.billesRougesCapturees = 0;
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
