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

	public Bille getTile(Position pos) {//en admettant bien sur que l'arguement currentMarble de pos n'est pas a jour
		return board[pos.x][pos.y];
	}

	public void fillUpTo(int ligne, int debut, int fin) {
		for (int i = debut;i<=fin;i++) {
			board[ligne][i] = new Bille(Color.RED);
		}
	}

	public Direction inverse(Direction direct) {
		switch (direct) {
		case NORTH : return Direction.SOUTH;
		case SOUTH : return Direction.NORTH;
		case WEST : return Direction.EAST;
		case EAST : return Direction.WEST;
		}
		return Direction.INVALID;
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

	public void undoLastMove() {//uniquement pour l'IA qui doit calculer toutes les probalités
		this.board = Plateau.stringToList(ancienPlateau.get(ancienPlateau.size()-1));
	}

	private Position push2 (Position pos, Direction direction, Joueur j1, Joueur j2) {
		if (pos.x >= board.length || pos.y >= board.length || pos.x < 0 || pos.y < 0) {//si on est en dehors du plateau et qu'on vient d'y pousser une bille
			if (pos.currentMarble.isRed()) {
				billesRouges--;
				j1.winRedMarble();
			}
			else {//si c'est une bille noire ou blanche
				if (j1.getColor() != pos.currentMarble.getColor()) {
					return null;//si la derniere case pousse (en dehors du plateau puisque nous avons deja un if qui l'a teste juste au dessus) est de la meme couleur que le joueur qui a pousse la bille
				}
				j2.loseMarble();//alors on enleve une bille au joueur
			}
			return pos;
		}
		if (board[pos.x][pos.y] == null) {
			board[pos.x][pos.y] = pos.currentMarble;
			return new Position(pos.x, pos.y, null);
		}
		Position pos2 = push2(pos.goTo(direction,board[pos.x][pos.y]),direction,j1,j2);//et on avance dans la direction direc
		if (pos2 != null) {//si il n'y a eu aucune erreur lors du procede alors nous poussons toutes les billes
			board[pos.x][pos.y] = pos.currentMarble;
		}
		return pos2;
	}

	public State push (Position pos, Direction direction, Joueur j1, Joueur j2) {//le joueur j1 pousse la bille du joueur j2
		if (board[pos.x][pos.y] ==  null) {
			return State.TILEEMPTY;
		}
		if (j1.getColor() != board[pos.x][pos.y].getColor()) {
			return State.BADMARBLE;
		}
		if (pos.x+inverse(direction).dirX() != -1 && pos.x+inverse(direction).dirX() != this.longueur && pos.y+inverse(direction).dirY() != -1 && pos.y+inverse(direction).dirY() != this.longueur) {
			if (this.board[pos.x+inverse(direction).dirX()][pos.y+inverse(direction).dirY()] != null) {
				return State.TILEBEFORENOTEMPTY;
			}
		}
		if (direction == Direction.INVALID) {
			return State.WRONGDIRECTION;
		}
		pos.currentMarble = null;//pour la premiere recursion ce sera utile car la premiere bille que l'on veut pousser va toujours devenir null car la case va se liberer
		Position tmp = push2(pos,direction,j1,j2);//on push la bille du joueur 2 car c'est le joueur 1 qui pousse
		if (tmp == null) {
			return State.PUSHOWNMARBLE;
		}
		else if (configurationDejaExistante()) {
			push2(tmp,inverse(direction),j1,j2);
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

	private static Bille[][] stringToList(String s) {
		int longueur = s.length()/s.length();
		Bille[][] board2 = new Bille[longueur][longueur];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				board2[i%longueur][i/longueur] = null;
			}
			else if (s.charAt(i) == 'W') {
				board2[i%longueur][i/longueur] = new Bille(Color.WHITE);
			}
			else if (s.charAt(i) == 'B') {
				board2[i%longueur][i/longueur] = new Bille(Color.BLACK);
			}
			else {
				board2[i%longueur][i/longueur] = new Bille(Color.RED);
			}
		}
		return board2;
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
