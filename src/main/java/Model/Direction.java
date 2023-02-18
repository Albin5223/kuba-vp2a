package Model;

public enum Direction {
	NORTH,
	WEST,
	SOUTH,
	EAST,
	INVALID;

	final static int[] dirX = {0, -1, 0, 1, 0}; // absicces
	final static int[] dirY = {-1, 0, 1, 0, 0}; // ordonnés
	
	
	final static String[] dirName = {"NORTH","WEST","SOUTH","EAST","INVALID"};

	int dirX() {
		return dirX[this.ordinal()];
	}
	int dirY() {
		return dirY[this.ordinal()];
	}

	final static Direction[] dirInverse = {SOUTH,EAST,NORTH,WEST,INVALID};

	Direction dirInverse(){
		return dirInverse[this.ordinal()];
	}
	
	public String dirName() {
		return dirName[this.ordinal()];
	}
}
