package Model;

public enum Color {
	BLACK,WHITE,RED;

	final static String[] name = {"B","W","R"};

	public String toString(){
		return name[this.ordinal()];
	}
}
