public class Position {
	public int x;
	public int y;
	public Bille currentMarble;//argument pas obligatoire et utilise uniquement dans la fonction push de plateau

	public Position (int x, int y, Bille b) {
		this.x = x;
		this.y = y;
		this.currentMarble = b;
	}

	public Position(int x, int y) {
		this(x,y,null);
	}

	public Position goTo(Direction direction, Bille newCurrentMarble) {
		Position pos = new Position(this.x,this.y,newCurrentMarble);
		switch (direction) {
		case NORTH : pos.x -= 1;break;
		case SOUTH : pos.x += 1;break;
		case WEST : pos.y -= 1;break;
		case EAST : pos.y += 1;break;
		}
		return pos;
	}
}
