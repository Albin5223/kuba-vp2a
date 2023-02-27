package Model;


public class NoeudIA  {
	private Plateau plateau;
	private NoeudIA parent;
	Joueur joueurAcc;
	Joueur joueurAdv;
	int diffPoint;

	public void predictForDepth () {
		for (int i = 0; i<plateau.getLongueur(); i++) {
			for (int j = 0; j<plateau.getLongueur(); j++) {
				if (plateau.getBoard()[i][j]==joueurAcc.getColor()) {
					
				}
			}
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


