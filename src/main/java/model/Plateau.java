import java.util.ArrayList;

public class Plateau {
	private Bille[][] board;
	private int lengthN;
	private int billesRouges;
	private ArrayList<String> ancienPlateau = new ArrayList<String>();
	private int longueur;//la longueur du plateau qui est stocke pour ne plus avoir a la calculer par la suite

	public Plateau(int n) {//on admet que n > 0 car nous avons deja fait le test dans la class Jeu
		this.longueur = 4*n-1;
		this.lengthN = n;
		this.board = new Bille[longueur][longueur];
		for (int i=0;i<longueur;i++){
			for (int j=0;j<longueur;j++){
				board[i][j]=null;
			}
		}
		this.billesRouges = 8*(n*n)-12*n+5;
	}

	public int getLongueur() {
		return this.longueur;
	}

	public Bille[][] getBoard() {//attention aux effets de bords en utilisant cette fonction car la liste originale est retourne
		return this.board;
	}

	public void fillUpTo(int ligne, int debut, int fin) {
		for (int i = debut;i<=fin;i++) {
			board[ligne][i] = new Bille(Color.RED);
		}
	}

	public Direction inverse(Direction direct) {
		switch (direct) {
		case UP : return Direction.DOWN;
		case DOWN : return Direction.UP;
		case RIGHT : return Direction.LEFT;
		case LEFT : return Direction.RIGHT;
		}
		return Direction.LEFT;//pour enlever les warnings
	}

	public void initialiseBille() {
		for(int i = 0; i<lengthN; i++) {
			for (int j = 0; j<lengthN ;j++) {
				board[i][j] = new Bille(Color.WHITE);
				board[i][longueur-1-j] = new Bille(Color.BLACK);
				board[longueur-1-i][longueur-1-j] = new Bille(Color.WHITE);
				board[longueur-1-i][j] = new Bille(Color.BLACK);
			}
		}
		int milieu=longueur/2;
		int l = 0;
		for(int k = 1; k<longueur-1 ;k++) {
			fillUpTo(k, milieu-l, milieu+l);
			if(k >= milieu) {
				l--;
			}
			else {
				l++;
			}
		}

		String s = this.toString();
		ancienPlateau.add(s);
	}

	private Position push2 (Position pos, Direction direction, Joueur j) {
		if (pos.x >= board.length || pos.y >= board.length || pos.x < 0 || pos.y < 0) {//si on est en dehors du plateau et qu'on vient d'y pousser une bille
			if (pos.currentMarble.isRed()) {
				billesRouges--;
			}
			else {//si c'est une bille noire ou blanche
				if (j.getColor() != pos.currentMarble.getColor()) {
					return null;//si la derniere case pousse (en dehors du plateau puisque nous avons deja un if qui l'a teste juste au dessus) est de la meme couleur que le joueur qui a pousse la bille
				}
				j.loseMarble();//alors on enleve une bille au joueur
			}
			return pos;
		}
		if (board[pos.x][pos.y] == null) {
			board[pos.x][pos.y] = pos.currentMarble;
			return new Position(pos.x, pos.y, null);
		}
		Position pos2 = push2(pos.goTo(direction,board[pos.x][pos.y]),direction,j);//et on avance dans la direction direc
		board[pos.x][pos.y] = pos.currentMarble;
		return pos2;
	}

	public State push (int x, int y, Direction direction, Joueur j1, Joueur j2) {//le joueur 1 pousse la bille du joueur 2
		if (board[x][y] ==  null) {
			return State.TILEEMPTY;
		}
		if (j1.getColor() != board[x][y].getColor()) {
			return State.BADMARBLE;
		}
		Position tmp = push2(new Position(x,y,null),direction,j2);//on push la bille du joueur 2 car c'est le joueur 1 qui pousse
		if (tmp == null) {
			return State.PUSHOWNMARBLE;
		}
		else if (configurationDejaExistante()) {
			push2(tmp,inverse(direction),j2);
			return State.BOARDEXIST;
		}
		return State.SUCCESS;
	}

	public Joueur isOver(Joueur j1, Joueur j2) {//fonction qui teste si le jeu se termine et si tel est le cas alors il renvoie le joueur gagnant sinon il renvoie null
		if (j1.getBilles() == 0 || (lengthN != 1 && j2.getBillesRougesCapturees() == (8*(lengthN*lengthN)-12*lengthN+5)/2)) {
			return j2;
		}
		if (j2.getBilles() == 0 || (lengthN != 1 && j1.getBillesRougesCapturees() == (8*(lengthN*lengthN)-12*lengthN+5)/2)) {
			return j1;
		}
		return null;
	}

	public boolean configurationDejaExistante() {
		String s = this.toString();
		System.out.println("Plateau courant : "+s);
		for (String tmp : ancienPlateau) {
			System.out.println("Plateau existant : "+tmp);
			if (s.equals(tmp)) {
				return true;
			}
		}
		ancienPlateau.add(s);
		return false;
	}

	public void affiche() {
		System.out.print((char) 27+"[4m    |");
		for (int m = 1; m<=longueur; m++){
			System.out.print(m);
		}
		System.out.println((char) 27+"[0m");
		for (int i = 0; i < longueur ;i++) {
			int lettre = 65+longueur-i-1;
			char c =  (char) lettre;
			System.out.print(c+"   |");
			for (int j = 0; j < longueur ;j++) {
				if (board[i][j] != null) {
					System.out.print(board[i][j].toString());
				}
				else {
					System.out.print("-");		
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < longueur; j++) {
				if (board[i][j] == null) {
					ret += "-";
				}
				else {
					ret += board[i][j].toString();
				}
			}
		}
		return ret;
	}
}
