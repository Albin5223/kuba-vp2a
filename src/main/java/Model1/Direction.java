package main.java.Model1;

public enum Direction {
	NORTH,
	WEST,
	SOUTH,
	EAST,
	INVALID;

	final static int[] dirX = {1, 0, -1, 0, 0}; // ordonnés
	final static int[] dirY = {0, 1, 0, -1, 0}; // absicces
	
	
	final static String[] dirName = {"NORTH","WEST","SOUTH","EAST","INVALID"};

	int dirX() {
		return dirX[this.ordinal()];
	}
	int dirY() {
		return dirY[this.ordinal()];
	}
	
	public String dirName() {
		return dirName[this.ordinal()];
	}
}
