package main.java.Model1;

public enum State {
	EMPTYTILE,
	MARBLEOWNERSHIPERROR,
	PUSHINGOWNMARBLE,
	REPEATINGBOARD,
	TILEBEFORENOTEMPTY,
	WRONGDIRECTION,
	SUCCESS;

	private Bille marble;

	public Bille getMarble() {
		return this.marble;
	}

	public void setMarble(Bille b) {
		this.marble = b;
	}
}