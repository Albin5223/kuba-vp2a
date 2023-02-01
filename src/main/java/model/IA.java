import java.util.Random;

public class IA extends Joueur {
	private Plateau plateau;
	private Bille[][] board;

	public IA(Color c, int n) {
		super(c,n,"Jack");
		this.plateau = new Plateau(n, new Joueur(c,n,"Joueur1"),this);
		this.board = plateau.getBoard();//etant donne que c'est un return et non une copie les effets de bord sur this.board feront changer this.plateau
	}

	private int getProba() {
		return 0;
	}

	private int testMove (Position pos, Direction direct) {
		int proba = 0;//au debut la proba de gagner est a zero et augmentera en fonction de ce qu'il voit dans le plateau
		if (plateau.push(pos,direct) == State.SUCCESS) {
			proba = getProba();
			plateau.undoLastMove();
		}
		return proba;
	}

	public void bestSolution() {
		int max = 0;//c'est un pourcentage alors on commence a 0
		Position posMax = null;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != null) {
					int proba1 = testMove(new Position(i,j,board[i][j]),Direction.UP);
					int proba2 = testMove(new Position(i,j,board[i][j]),Direction.DOWN);
					int proba3 = testMove(new Position(i,j,board[i][j]),Direction.LEFT);
					int proba4 = testMove(new Position(i,j,board[i][j]),Direction.RIGHT);
					if (Math.max(Math.max(proba1,proba2),Math.max(proba3,proba4)) > max) {
						max = Math.max(Math.max(proba1,proba2),Math.max(proba3,proba4));
						//il manque plus qu'a faire le moove
					}
				}
			}
		}
		return this.board;
	}
}
