package Model;

import java.util.ArrayList;


public class Plateau implements Cloneable{
	private Colour[][] board;
	private int lengthN;
	protected int billesRouges;
	private ArrayList<String> ancienPlateau = new ArrayList<String>();
	private int longueur;//la longueur du plateau qui est stocke pour ne plus avoir a la calculer par la suite
	protected Joueur j1;
	protected Joueur j2;

	public Plateau(int n, Joueur j1, Joueur j2) {//on admet que n > 0 car nous avons deja fait le test dans la class Jeu
		this.longueur = 4*n-1;
		this.lengthN = n;
		this.board = new Colour[longueur][longueur];
		for (int i=0;i<longueur;i++){
			for (int j=0;j<longueur;j++){
				board[i][j]=null;
			}
		}
		this.billesRouges = 8*(n*n)-12*n+5;
		this.j1 = j1;
		this.j2 = j2;
		this.j1.initTabBilles(n, j1.getColor());
		this.j2.initTabBilles(n, j2.getColor());
	}

	public Plateau(String strPlateau) {
		Colour[][] tmp = stringToList(strPlateau);
		this.longueur = tmp.length;
		this.board = tmp;
		this.lengthN = (this.longueur+1)/4;


	}

	public int getLongueur() {
		return this.longueur;
	}

	public Colour[][] getBoard() {//attention aux effets de bords en utilisant cette fonction car la liste originale est retourne
		return this.board;
	}

	public Colour getTile(Pos pos) {//en admettant bien sur que l'arguement currentMarble de pos n'est pas a jour
		return board[pos.i][pos.j];
	}

	public static void fillUpTo(int ligne, int debut, int fin, Colour[][] board) {
		for (int i = debut;i<=fin;i++) {
			board[ligne][i] = Colour.RED;
		}
	}

	public static void initialiseBille(Plateau plat) {
		for(int i = 0; i<plat.lengthN; i++) {
			for (int j = 0; j<plat.lengthN ;j++) {
				plat.board[i][j] = Colour.WHITE;
				plat.board[i][plat.longueur-1-j] = Colour.BLACK;
				plat.board[plat.longueur-1-i][plat.longueur-1-j] = Colour.WHITE;
				plat.board[plat.longueur-1-i][j] = Colour.BLACK;
			}
		}
		int milieu=plat.longueur/2;
		int l = 0;
		for(int k = 1; k<plat.longueur-1 ;k++) {
			Plateau.fillUpTo(k, milieu-l, milieu+l,plat.board);
			if(k >= milieu) {
				l--;
			}
			else {
				l++;
			}
		}

		String s = plat.toString();
		plat.ancienPlateau.add(s);
	}


	public Colour getColor(int i, int j){
		return board[i][j];
	}

	public Colour getColor(Pos p){
		return board[p.getI()][p.getJ()];
	}

	public void undoLastMove() {//uniquement pour l'IA qui doit calculer toutes les probalités
		this.board = Plateau.stringToList(ancienPlateau.get(ancienPlateau.size()-1));
	}

	private State push_rec (Pos pos, Direction direction, Colour colour, Joueur j1, Joueur j2) {
		if (pos.i >= board.length || pos.j >= board.length || pos.i < 0 || pos.j < 0) {//si on est en dehors du plateau et qu'on vient d'y pousser une bille
			if (colour == Colour.RED) {
				billesRouges--;
				j1.winRedMarble();
				return State.PUSHREDMARBLE;
			}
			else {//si c'est une bille noire ou blanche
				if (j1.getColor() == colour) {
					return null;//si la derniere case pousse (en dehors du plateau puisque nous avons deja un if qui l'a teste juste au dessus) est de la meme couleur que le joueur qui a pousse la bille
				}
				j2.loseMarble(pos);//alors on enleve une bille au joueur
				return State.PUSHOPPMARBLE;
			}
		}
		if (board[pos.i][pos.j] == null) {
			board[pos.i][pos.j] = colour;
			return State.SUCCESS;
		}
		for (int i = 0; i < j1.tabBilles.length; i++) {
			if (pos.i == j1.tabBilles[i].i && pos.j == j1.tabBilles[i].j) {
				j1.tabBilles[i] = j1.tabBilles[i].goTo(direction);
			}
		}
		State state = push_rec(pos.goTo(direction),direction,board[pos.i][pos.j],j1,j2);//et on avance dans la direction direc
		if (state != null) {//si il n'y a eu aucune erreur lors du procede alors nous poussons toutes les billes
			board[pos.i][pos.j] = colour;
		}
		return state;
	}

	public State push (Pos pos, Direction direction, Joueur j1, Joueur j2) {//le joueur j1 pousse la bille du joueur j2
		if (direction == Direction.INVALID) {
			return State.WRONGDIRECTION;
		}
		if (board[pos.i][pos.j] ==  null) {
			return State.EMPTYTILE;
		}
		if (j1.getColor() != board[pos.i][pos.j]) {
			return State.MARBLEOWNERSHIPERROR;
		}
		if (pos.i+direction.dirInverse().dirY() != -1 && pos.i+direction.dirInverse().dirY() != this.longueur && pos.j+direction.dirInverse().dirX() != -1 && pos.j+direction.dirInverse().dirX() != this.longueur) {
			if (this.board[pos.i+direction.dirInverse().dirY()][pos.j+direction.dirInverse().dirX()] != null) {
				return State.TILEBEFORENOTEMPTY;
			}
		}
		State state = push_rec(pos,direction,null,j1,j2);//on push la bille du joueur 2 car c'est le joueur 1 qui pousse
		if (state == null) {
			return State.PUSHINGOWNMARBLE;
		}
		else if (configurationDejaExistante()) {
			pos.i += direction.dirX();//puisque le plateau a change et les billes ont ete pousses
			pos.j += direction.dirY();

			while (pos.i > -1 && pos.j > -1 && pos.i < longueur && pos.j < longueur && board[pos.i][pos.j] != null) {
				pos.i += direction.dirX();
				pos.j += direction.dirY();
			}
			pos.i -= direction.dirX();
			pos.j -= direction.dirY();
			push_rec(pos,direction.dirInverse(),null,j1,j2);
			return State.REPEATINGBOARD;
		}
		if (state == State.PUSHOPPMARBLE) {
			for (int i = 0; i < j1.tabBilles.length; i++) {
				if (j1.tabBilles[i].i == pos.i && j1.tabBilles[i].j == pos.j) {
					j1.tabBilles[i].i = -1;
				}
			}
		}

		return state;
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
	/*
	 * 
	 * @return toString1 convertit le plateau en une chaine de caractère reduite
	 */
	public String toString(){
		String res = "";
		char car = ' ';
		int nb_apparition=0;
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < longueur; j++) {
				char sub;
				if (board[i][j] == null) {
					sub = '-';
				}
				else {
					sub = board[i][j].toString().charAt(0);
				}
				if(car != sub){
					res+=car+"";
					if (res.length()!=1){
						res+=(nb_apparition+1);
					}
					car = sub;
					nb_apparition = 0;
				}
				else{
					nb_apparition+=1;
				}
			}
		}
		res+=car+"";
	
		res+=(nb_apparition+1);
		res = res.substring(1, res.length());
		
		return res;
	}

	// Cette fonction permet de passer d'une chaine de caractère encodé de manière efficace à un tableau de Bille
	public static Colour[][] stringToList(String s){
		int l = 0; //Ici on détermine la longueur d'une tableau 
		int occ = 0; // On additionne tous les nombres derrière les caractères
		while(occ<s.length()){
			int count = findNumber(s.substring(occ+1));
			l+=count;
			occ+=1+nbChiffre(count);
		}
		int longueur = (int)Math.sqrt(l); //La longueur est la racine de la somme
		Colour[][] res = new Colour[longueur][longueur];

		int i = 0;
		int colonne = 0;
		int ligne = 0; //Puis on remplie le tableau
		while(i<s.length()){ //On parcourt tous les caractères BWR- et on regarde le nombre derrière
			char couleur = s.charAt(i);
			int nb = findNumber(s.substring(i+1));
			

			for (int k = 0;k<nb;k++){
				if (ligne==longueur){ //On fait attention à ne pas dépasser les limites du tableaux
					ligne = 0;
					colonne+=1;
				}
				switch(couleur){
					case 'W' : res[colonne][ligne] = Colour.WHITE;break;
					case 'B' : res[colonne][ligne] = Colour.BLACK;break;
					case 'R' : res[colonne][ligne] = Colour.RED;break;
					default : res[colonne][ligne] = null;
				}
				ligne++;
			}
			i+=nbChiffre(nb)+1;

		}
		return res;
	}


	//Cette fonction permets de trouver le nombre qui se situe en premier sur la chaine de caractère
	//CharAt(0) ne fonction pas car il se peut que le nombre contient 2 chiffres.

	private static int findNumber(String s){
		String nb="";
		for (int i = 0;i<s.length();i++){
			if (s.charAt(i)<='9' && s.charAt(i)>='0'){
				nb+=s.charAt(i)+"";
			}
			else{
				break;
			}
		}
		int nombre = Integer.valueOf(nb);
		return nombre;
	}

	//Cette fonction permet de connaitre le nombre de chiffre d'un nombre.
	//Elle est utile lorsqu'on souhaite avancer sur la chaine de caractère encodé efficacement.

	private static int nbChiffre(int n){
		if (n/10 == 0){
			return 1;
		}
		else{
			return 1 + nbChiffre(n/10);
		}
	}

	@Override
    protected Plateau clone() throws CloneNotSupportedException {
        return (Plateau) super.clone();
    }
}
