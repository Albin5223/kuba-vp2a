package Model;

import java.util.LinkedList;

public class NoeudIA  {
	private Plateau plateau;
	private NoeudIA parent;
	Joueur joueurAcc;
	Joueur joueurAdv;
	int diffPoint;
	LinkedList<NoeudIA> fils;

	public NoeudIA (NoeudIA parent) throws CloneNotSupportedException {
		plateau = parent.plateau.clone();
		this.parent = parent;
		joueurAcc = parent.joueurAdv;
		joueurAdv = parent.joueurAcc;
	}

	public void createNextNode () throws CloneNotSupportedException {
		for (int i = 0; i<plateau.getLongueur(); i++) {
			for (int j = 0; j < plateau.getLongueur(); j++) {
				if (joueurAdv.tabBilles[i][j]!=null && joueurAdv.tabBilles[i][j] == Colour.BLACK ) {//en admettant que l'IA soit toujours les noirs
					if (plateau.push(new Pos(i,j),Direction.NORTH,joueurAdv,joueurAcc)==State.SUCCESS) {
						fils.add(new NoeudIA(this));
						plateau.undoLastMove();
					}
					if (plateau.push(new Pos(i,j),Direction.WEST,joueurAdv,joueurAcc)==State.SUCCESS) {
						fils.add(new NoeudIA(this));
						plateau.undoLastMove();
					}
					if (plateau.push(new Pos(i,j),Direction.EAST,joueurAdv,joueurAcc)==State.SUCCESS) {
						fils.add(new NoeudIA(this));
						plateau.undoLastMove();
					}
					if (plateau.push(new Pos(i,j),Direction.SOUTH,joueurAdv,joueurAcc)==State.SUCCESS) {
						fils.add(new NoeudIA(this));
						plateau.undoLastMove();
					}
				}
			}
		}
	}

	public void updateDiffPoint () {
		for (NoeudIA n : this.fils) {
			n.diffPoint = n.rateDiffMarble();
			n.updateDiffPoint();
		}
	}

	public int rateDiffMarble () {
		if (this.parent==null) {
			return 0;
		}
		return ((-this.joueurAcc.getBilles()+parent.joueurAdv.getBilles())*2+
		this.plateau.billesRouges-parent.plateau.billesRouges);
	}
}
