package Model;

public enum Colour {
	BLACK,
	WHITE,
	RED;

	public String toString() {
		switch (this.ordinal()){
			case 0 : return "B";
			case 1 : return "W";
			default : return "R";
		}
	}

	static String[] name={"NOIR","BLANC","ROUGE"};

	public String getName(){
		return name[this.ordinal()];
	}

	public Colour next(){
		switch (this.ordinal()){
			case 0 : return WHITE;
			case 1 : return RED;
			default : return null; 
		}
	}
}
