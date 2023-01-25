public class Case {
	private boolean empty;
	private Bille bille;

	public Case(Bille bille) {
		this.empty = (bille == null);
		this.bille = bille;
	}

	public String toString() {
		if (empty) {
			return "";
		}
		return bille.toString();
	}

	public boolean isEmpty() {
		return empty;
	}

	public Bille getBille() {
		return this.bille;
	}

	public void setBille(Bille bille) {
		this.bille = bille;
	}

	public void clear() {
		this.bille = null;
	}
}
