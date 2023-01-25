import java.util.ArrayList;

public class Plateau {
	private Case[][] board;
	private int lengthN;
	private int billesRouges;
	private ArrayList<Plateau> ancienPlateau = new ArrayList<Plateau>();
	private int longueur;//la longueur du plateau qui est stocke pour ne plus avoir a la calculer par la suite

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
	}

	public void push2 (int x, int y, char direction, Case casePrec, Joueur j) throws IncorrectMoveException {
		if (x >= board.length || y >= board.length || x < 0 || y < 0) {//si on est en dehors du plateau et qu'on vient d'y pousser une bille
			if (casePrec.getBille().isRed()) {
				billesRouges--;
			}
			else {//si c'est une bille noire ou blanche
				if (j.getColor() != casePrec.getBille().getColor()) {
					throw new IncorrectMoveException("Vous avez essayé de pousser en dehors du plateau une bille qui est à vous (P.S. : vous êtes débile !)");//si la derniere case pousse (en dehors du plateau puisque nous avons deja un if qui l'a teste juste au dessus) est de la meme couleur que le joueur qui a pousse la bille
				}
				j.loseMarble();//alors on enleve une bille au joueur
			}
			return;
		}
		if (board[x][y].isEmpty()) {
			board[x][y] = casePrec;
			return;
		}
		if (direction == 'n') {
			push2(x-1,y,direction,board[x][y],j);
			board[x][y] = casePrec;
		}
		else if (direction == 's') {
			push2(x+1,y,direction,board[x][y],j);
			board[x][y] = casePrec;
		}
		else if (direction == 'w') {
			push2(x,y-1,direction,board[x][y],j);
			board[x][y] = casePrec;
		}
		else {
			push2(x,y+1,direction,board[x][y],j);
			board[x][y] = casePrec;
		}
	}

	public void push (int x, int y, char direction, Joueur j1, Joueur j2) throws IncorrectMoveException {//le joueur 1 pousse la bille du joueur 2
		if (board[x][y].isEmpty()) {
			throw new IncorrectMoveException("Vous avez essayé de pousser une case vide");
		}
		if (j1.getColor() != board[x][y].getBille().getColor()) {
			throw new IncorrectMoveException("Vous essayer de pousser une bille qui n'est pas à vous");
		}
		push2(x,y,direction,new Case(null),j2);//on push la bille du joueur 2 car c'est le joueur 1 qui pousse
	}

	public Joueur isOver(Joueur j1, Joueur j2) {//fonction qui teste si le jeu se termine et si tel est le cas alors il renvoie le joueur gagnant sinon il renvoie null
		if (j1.getBilles() == 0 || j2.getBillesRougesCapturees() == (8*(lengthN*lengthN)-12*lengthN+5)/2) {
			return j2;
		}
		if (j2.getBilles() == 0 || j1.getBillesRougesCapturees() == (8*(lengthN*lengthN)-12*lengthN+5)/2) {
			return j1;
		}
		return null;
	}

	/*public boolean configurationJamaisExistante() {
		for (Plateau p : ancienPlateau) {
			if(this.equals(p)) {
				return false;
			}
		}
		//ancienPlateau.add(this);
		return true;
	}*/

	public void affiche() {
		for (int i = 0; i < longueur ;i++) {
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
	}
}
