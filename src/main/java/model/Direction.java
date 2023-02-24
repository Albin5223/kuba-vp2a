package Model;

public enum Direction {
	NORTH,
	WEST,
	SOUTH,
	EAST,
	INVALID;

	final static int[] dirX = {-1, 0, 1, 0, 0}; // absicces
	final static int[] dirY = {0, -1, 0, 1, 0}; // ordonnés


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

	public Direction dirInverse() {
		if (this.ordinal() == 4) {
			return Direction.INVALID;
		}
		if (this.ordinal() <= 1) {
			return Direction.values()[this.ordinal()+2];
		}
		return Direction.values()[this.ordinal()-2];
	}
}
