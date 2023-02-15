package Model;

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
		pos.x+= direction.dirX();
		pos.y+= direction.dirY();
		return pos;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
