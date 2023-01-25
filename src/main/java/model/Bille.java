public class Bille {
	private Color color;

	public Bille(Color c) {
		this.color = c;
	}

	public String toString() {
		if (color == Color.WHITE) return "W";
		else if (color == Color.BLACK) return "B";
		else return "R";
	}

	public boolean isRed() {
		return color == Color.RED;
	}

	public Color getColor() {
		return color;
	}
}
