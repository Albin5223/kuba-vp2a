import java.util.ArrayList;

public class Plateau {
	private Case[][] board;
	private int lengthN;
	private int billesRouges;
	private ArrayList<String> ancienPlateau = new ArrayList<String>();
	private int longueur;//la longueur du plateau qui est stocke pour ne plus avoir a la calculer par la suite

	private class Pair<A,B> {
		public A arg1;
		public B arg2;

		public Pair (A arg1, B arg2) {
			this.arg1 = arg1;
			this.arg2 = arg2;
		}

		public A getArg1() {
			return this.arg1;
		}

		public B getArg2() {
			return this.arg2;
		}
	}

	public Plateau(int n) {//on admet que n > 0 car nous avons deja fait le test dans la class Jeu
		this.longueur = 4*n-1;
		this.lengthN = n;
		this.board = new Case[longueur][longueur];
		for (int i=0;i<longueur;i++){
			for (int j=0;j<longueur;j++){
				board[i][j]=new Case(null);
			}
		}
		this.billesRouges = 8*(n*n)-12*n+5;
	}

	public int getLongueur() {
		return this.longueur;
	}

	public Case[][] getBoard() {//attention aux effets de bords en utilisant cette fonction car la liste originale est retourne
		return this.board;
	}

	public char inverse(char direction) {//renvoie la direction inverse a celle entre en argument
		if (direction == 'n') {
			return 's';
		}
		else if (direction == 's') {
			return 'n';
		}
		else if (direction == 'w') {
			return 'e';
		}
		else {
			return 'e';
		}
	}

	public void fillUpTo(int ligne, int debut, int fin) {
		for (int i = debut;i<=fin;i++) {
			board[ligne][i] = new Case(new Bille(Color.RED));
		}
	}

	public void initialiseBille() {
		for(int i = 0; i<lengthN; i++) {
			for (int j = 0; j<lengthN ;j++) {
				board[i][j] = new Case(new Bille(Color.WHITE));
				board[i][longueur-1-j] = new Case(new Bille(Color.BLACK));
				board[longueur-1-i][longueur-1-j] = new Case(new Bille(Color.WHITE));
				board[longueur-1-i][j] = new Case(new Bille(Color.BLACK));
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

	public Pair<Case,Pair<Number,Number>> push2 (int x, int y, char direction, Case casePrec, Joueur j) throws IncorrectMoveException {
		if (x >= board.length || y >= board.length || x < 0 || y < 0) {//si on est en dehors du plateau et qu'on vient d'y pousser une bille
			if (casePrec.getBille().isRed()) {
				billesRouges--;
			}
			else {//si c'est une bille noire ou blanche
				if (j.getColor() != casePrec.getBille().getColor()) {
					throw new IncorrectMoveException("Vous avez essayé de pousser en dehors du plateau une bille qui est à vous (P.S. : vous êtes débile) !");//si la derniere case pousse (en dehors du plateau puisque nous avons deja un if qui l'a teste juste au dessus) est de la meme couleur que le joueur qui a pousse la bille
				}
				j.loseMarble();//alors on enleve une bille au joueur
			}
			return new Pair<Case,Pair<Number,Number>>(casePrec, new Pair<Number,Number>(x,y));
		}
		if (board[x][y].isEmpty()) {
			board[x][y] = casePrec;
			return new Pair<Case,Pair<Number,Number>>(new Case(null), new Pair<Number,Number>(x,y));
		}
		if (direction == 'n') {
			Pair<Case,Pair<Number,Number>> tmp = push2(x-1,y,direction,board[x][y],j);
			board[x][y] = casePrec;
			return tmp;
		}
		else if (direction == 's') {
			Pair<Case,Pair<Number,Number>> tmp = push2(x+1,y,direction,board[x][y],j);
			board[x][y] = casePrec;
			return tmp;
		}
		else if (direction == 'w') {
			Pair<Case,Pair<Number,Number>> tmp = push2(x,y-1,direction,board[x][y],j);
			board[x][y] = casePrec;
			return tmp;
		}
		else {
			Pair<Case,Pair<Number,Number>> tmp = push2(x,y+1,direction,board[x][y],j);
			board[x][y] = casePrec;
			return tmp;
		}
	}

	public void push (int x, int y, char direction, Joueur j1, Joueur j2) throws IncorrectMoveException {//le joueur 1 pousse la bille du joueur 2
		if (board[x][y].isEmpty()) {
			throw new IncorrectMoveException("Vous avez essayé de pousser une case vide !");
		}
		if (j1.getColor() != board[x][y].getBille().getColor()) {
			throw new IncorrectMoveException("Vous essayer de pousser une bille qui n'est pas à vous !");
		}
		Pair<Case,Pair<Number,Number>> tmp = push2(x,y,direction,new Case(null),j2);//on push la bille du joueur 2 car c'est le joueur 1 qui pousse
		if (configurationDejaExistante()) {
			push2((int)tmp.getArg2().getArg1(),(int)tmp.getArg2().getArg2(),inverse(direction),tmp.getArg1(),j2);
			throw new IncorrectMoveException("Le plateau résultant a deja été vu auparavant !");
		}

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
		for (String tmp : ancienPlateau) {
			if (s.equals(tmp)) {
				return true;
			}
		}
		ancienPlateau.add(s);
		return false;
	}

	public void affiche() {
		System.out.print((char) 27+"[4m    |");
		for (int m = 1;m<=longueur;m++){
			System.out.print(m);
		}
		System.out.println((char) 27+"[0m");
		for (int i = 0; i < longueur ;i++) {
			int lettre = 65+longueur-i-1;
			char c =  (char) lettre;
			System.out.print(c+"   |");
			for (int j = 0; j < longueur ;j++) {
				if (!board[i][j].isEmpty()) {
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
				ret += board[i][j].toString();
			}
		}
		return ret;
	}
}
