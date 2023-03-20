package Model;

import java.util.LinkedList;

public class NoeudIA  {
	private Plateau plateau;
	Joueur joueurAcc;
	Joueur joueurAdv;
	int value;
	Direction dir;
	Pos pos;
	LinkedList<NoeudIA> fils;

	public NoeudIA (Plateau p, Joueur joueurAcc, Joueur joueurAdv) throws CloneNotSupportedException {
		plateau = p.clone();
		this.joueurAcc=joueurAcc;
		this.joueurAdv=joueurAdv;
	}

	public NoeudIA (NoeudIA n) throws CloneNotSupportedException {
		plateau = n.plateau.clone();
		joueurAcc = n.joueurAdv;
		joueurAdv = n.joueurAcc;
	}

	public void createNextNodes () throws CloneNotSupportedException {
		for (int i = 0; i<joueurAdv.tabBilles.length ; i++) {
			if (joueurAdv.tabBilles[i].i!=-1) {
				if (plateau.push(joueurAdv.tabBilles[i],Direction.NORTH,joueurAdv,joueurAcc)==State.SUCCESS) {
					NoeudIA newNode = new NoeudIA(this);
					newNode.value=newNode.rateValue(this);
					newNode.dir=Direction.NORTH;
					fils.add(newNode);
					plateau.undoLastMove();
				}
				if (plateau.push(joueurAdv.tabBilles[i],Direction.WEST,joueurAdv,joueurAcc)==State.SUCCESS) {
					NoeudIA newNode = new NoeudIA(this);
					newNode.value=newNode.rateValue(this);
					newNode.dir=Direction.WEST;
					fils.add(newNode);
					plateau.undoLastMove();
				}
				if (plateau.push(joueurAdv.tabBilles[i],Direction.EAST,joueurAdv,joueurAcc)==State.SUCCESS) {
					NoeudIA newNode = new NoeudIA(this);
					newNode.value=newNode.rateValue(this);
					newNode.dir=Direction.EAST;
					fils.add(newNode);
					plateau.undoLastMove();
				}
				if (plateau.push(joueurAdv.tabBilles[i],Direction.SOUTH,joueurAdv,joueurAcc)==State.SUCCESS) {
					NoeudIA newNode = new NoeudIA(this);
					newNode.value=newNode.rateValue(this);
					newNode.dir=Direction.SOUTH;
					fils.add(newNode);
					plateau.undoLastMove();
				}
			}
			
		}
	}

	public static Move determineBestMove (Plateau p, int depth, Joueur joueurAcc, Joueur joueurAdv) throws CloneNotSupportedException {
		NoeudIA arbre = createTree(p, depth, joueurAcc, joueurAdv);
		int bestValue = -999999999;
		NoeudIA bestNode = arbre.fils.getFirst(); // Faire un cas de fin de partie si erreur
		for (NoeudIA node : arbre.fils) {
			int mm = node.minimax(depth);
			if (bestValue < mm ) {
				bestValue = mm;
				bestNode = node;
			}
		}
		Move bestMove = new Move(bestNode.pos,bestNode.dir);
		return bestMove;
	}

	public static NoeudIA createTree (Plateau p, int depth, Joueur joueurAcc, Joueur joueurAdv) throws CloneNotSupportedException {
		NoeudIA tete = new NoeudIA(p,joueurAcc,joueurAdv);
		createTreeRec(tete, depth);
		return tete;
	}

	public static void createTreeRec (NoeudIA n, int depth) throws CloneNotSupportedException {
		if (depth == 0) { return; }
		n.createNextNodes();
		for (NoeudIA node : n.fils) {
			createTreeRec(node, depth - 1);
		}
	}

	public int minimax (int depth) {
		if (depth == 0 || this.fils.isEmpty()) {
			return this.value;
		}
		if (this.joueurAcc.getColor()==Colour.WHITE) {
			int m = -999999999;
			for (NoeudIA node : fils) {
				m = Math.max(m, node.minimax(depth - 1));
			}
			return m;
		}
		else {
			int m = 999999999;
			for (NoeudIA node : fils) {
				m = Math.min(m, node.minimax(depth - 1));;
			}
			return m;
		}
	}

	public int rateValue (NoeudIA parent) { //A FAIRE
		return 0;
	}
}
