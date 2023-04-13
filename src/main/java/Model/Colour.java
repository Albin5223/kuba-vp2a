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

	static String[] name={"NOIR","BLANC","ROUGE"};

	public String getName(){
		return name[this.ordinal()];
	}

	public Colour next(){
		if (this.ordinal() == 0) return WHITE;
		else if (this.ordinal() == 1) return RED;
		else return null;
	}
}
