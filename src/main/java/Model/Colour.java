package Model;

public enum Colour {
	BLACK,
	WHITE,
	RED;

	public String toString() {
		if (this.ordinal() == 1) return "W";
		else if (this.ordinal() == 0) return "B";
		else return "R";
	}
}
