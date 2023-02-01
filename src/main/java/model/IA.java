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
		return 0;//en attendant la suite de la fonction
	}

	private int testMove (Position pos, Direction direct) {
		int proba = 0;//au debut la proba de gagner est a zero et augmentera en fonction de ce qu'il voit dans le plateau
		if (plateau.push(pos,direct) == State.SUCCESS) {
			proba = getProba();
			plateau.undoLastMove();
		}
		return proba;
	}

	public Bille[][] bestSolution() {
		int max = 0;//c'est un pourcentage alors on commence a 0
		Position posMax = null;
		Direction directMax = Direction.UP;
		int proba1 = 0;
		int proba2 = 0;
		int proba3 = 0;
		int proba4 = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != null) {
					proba1 = testMove(new Position(i,j,board[i][j]),Direction.UP);
					proba2 = testMove(new Position(i,j,board[i][j]),Direction.DOWN);
					proba3 = testMove(new Position(i,j,board[i][j]),Direction.LEFT);
					proba4 = testMove(new Position(i,j,board[i][j]),Direction.RIGHT);
					if (Math.max(Math.max(proba1,proba2),Math.max(proba3,proba4)) > max) {
						max = Math.max(Math.max(proba1,proba2),Math.max(proba3,proba4));
						posMax = new Position(i,j,board[i][j]);
						if (proba1 == max) {
							directMax = Direction.UP;
						}
						else if (proba2 == max) {
							directMax = Direction.DOWN;
						}
						else if (proba3 == max) {
							directMax = Direction.LEFT;
						}
						else {
							directMax = Direction.RIGHT;
						}
					}
				}
			}
		}
		this.plateau.push(posMax,directMax);
		return this.board;
	}
}
