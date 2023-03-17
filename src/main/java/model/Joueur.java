package Model;


public class Joueur {
	private Colour colour;//'b' pour noir (= black) et 'w' pour blanc (=white)
	private int nBilles;//pour savoir combien de billes il lui reste
	private int billesRougesCapturees;//si il en a capture la moitie il peut gagner
	public Pos[] tabBilles;

	public Joueur(Colour c, int n) {
		this.nBilles = 2*(n*n);
		this.colour = c;
		this.billesRougesCapturees = 0;
	}

	public void loseMarble(Pos pos) {
		for (int i = 0; i < tabBilles.length; i++) {
			if (tabBilles[i].i == pos.i && tabBilles[i].j == pos.j) {
				tabBilles[i].i = -1;
			}
		}
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
		Pos[] tab= new Pos[n*n*2];
		int a = 0;
		if (c==Colour.WHITE) {
			for (int i = 0; i<n; i++) {
				for (int j = 0; j<n; j++) {
					tab[a]= new Pos(i,j);
					a++;
				}
			}
			for (int i = n*3-1; i<n + n*3-1; i++) {
				for (int j = n*3-1; j<n + n*3-1; j++) {
					tab[a]= new Pos(i,j);
					a++;
				}
			}
		}

		if (c==Colour.BLACK) {
			for (int i = n*3-1; i<n + n*3-1; i++) {
				for (int j = 0; j<n; j++) {
					tab[a]= new Pos(i,j);
					a++;
				}
			}
			for (int i = 0; i<n; i++) {
				for (int j = n*3-1; j<n + n*3-1; j++) {
					tab[a]= new Pos(i,j);
					a++;
				}
			}
		}

		this.tabBilles = tab;
	}

	public void afficheTab () {
		for (int i = 0; i<tabBilles.length; i++) {
			System.out.println("(pos " + i + " :" + tabBilles[i].i + "," + tabBilles[i].j + ")");
		}
	}
}
