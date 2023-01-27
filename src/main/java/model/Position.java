public class Position {
	public int x;
	public int y;
	public Bille currentMarble;

	public Position (int x, int y, Bille b) {
		this.x = x;
		this.y = y;
		this.currentMarble = b;
	}

	public Position goTo(Direction direction, Bille newCurrentMarble) {
		Position pos = new Position(this.x,this.y,newCurrentMarble);
		switch (direction) {
		case UP : pos.x++;break;
		case DOWN : pos.x--;break;
		case LEFT : pos.y--;break;
		case RIGHT : pos.y++;break;
		}
		return pos;
	}
}
