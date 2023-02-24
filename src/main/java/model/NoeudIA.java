package Model;

import java.util.LinkedList;

public class NoeudIA  {
	private Plateau plateau;
	private NoeudIA parent;
	private LinkedList<NoeudIA> fils;
	Joueur joueurAcc;
	Joueur joueurAdv;
	int diffPoint;

public int rateDiffMarble () {
	if (this.parent==null) {
		return 0;
	}
	return ((-this.joueurAcc.getBilles()+parent.joueurAdv.getBilles())*2+
	this.plateau.billesRouges-parent.plateau.billesRouges);
}

}


