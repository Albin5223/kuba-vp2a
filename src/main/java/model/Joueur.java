package Model;


public class Joueur {
	private Colour colour;//'b' pour noir (= black) et 'w' pour blanc (=white)
	private int nBilles;//pour savoir combien de billes il lui reste
	private int billesRougesCapturees;//si il en a capture la moitie il peut gagner
	protected Colour[][] tabBilles;

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

	public void initTabBilles (int n, Colour c) {
		for(int i = 0; i<n; i++) {
			for (int j = 0; j<n ;j++) {
				this.tabBilles[i][j] = Colour.WHITE;
				this.tabBilles[i][n-1-j] = Colour.BLACK;
				this.tabBilles[n-1-i][n-1-j] = Colour.WHITE;
				this.tabBilles[n-1-i][j] = Colour.BLACK;
			}
		}
		int milieu=n/2;
		int l = 0;
		for(int k = 1; k<n-1 ;k++) {
			Plateau.fillUpTo(k, milieu-l, milieu+l,this.tabBilles);
			if(k >= milieu) {
				l--;
			}
			else {
				l++;
			}
		}
	}

	public void afficheTab () {
		for (int i = 0; i<tabBilles.length; i++) {
			System.out.println("(pos " + i + " :" + tabBilles[i].i + "," + tabBilles[i].j + ")");
		}
	}
}
