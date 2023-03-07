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
		for (int i = 0; i<joueurAdv.tabBilles.length ; i++) {
			if (joueurAdv.tabBilles[i].i!=-1) {
				if (plateau.push(joueurAdv.tabBilles[i],Direction.NORTH,joueurAdv,joueurAcc)==State.SUCCESS) {
					fils.add(new NoeudIA(this));
					plateau.undoLastMove();
				}
				if (plateau.push(joueurAdv.tabBilles[i],Direction.WEST,joueurAdv,joueurAcc)==State.SUCCESS) {
					fils.add(new NoeudIA(this));
					plateau.undoLastMove();
				}
				if (plateau.push(joueurAdv.tabBilles[i],Direction.EAST,joueurAdv,joueurAcc)==State.SUCCESS) {
					fils.add(new NoeudIA(this));
					plateau.undoLastMove();
				}
				if (plateau.push(joueurAdv.tabBilles[i],Direction.SOUTH,joueurAdv,joueurAcc)==State.SUCCESS) {
					fils.add(new NoeudIA(this));
					plateau.undoLastMove();
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


