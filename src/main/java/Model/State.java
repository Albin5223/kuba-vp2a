package Model;

public enum State {
	EMPTYTILE,
	MARBLEOWNERSHIPERROR,
	PUSHINGOWNMARBLE,
	REPEATINGBOARD,
	TILEBEFORENOTEMPTY,
	WRONGDIRECTION,
	SUCCESS,
	REDREPLAY,
	OPPREPLAY;


	private Bille marble;

	public Bille getMarble() {
		return this.marble;
	}

	public void setMarble(Bille b) {
		this.marble = b;
	}
}