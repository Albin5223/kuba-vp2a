package Model;

public class Joueur {
	private Colour colour;//'b' pour noir (= black) et 'w' pour blanc (=white)
	private int nBilles;//pour savoir combien de billes il lui reste
	private int billesRougesCapturees;//si il en a capture la moitie il peut gagner

	public Joueur(Colour c, int n) {
		this.nBilles = 2*(n*n);
		this.colour = c;
		this.billesRougesCapturees = 0;
	}

	public void loseMarble() {
		nBilles --;
	}

	public Colour getColor() {
		return colour;
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
