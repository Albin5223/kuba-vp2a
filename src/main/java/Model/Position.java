package  Model;

public class Position implements Cloneable {
	public int i;
	public int j;

	public Position (int i, int j) {
		this.i = i;
		this.j = j;
	}

	public Position goTo(Direction direction) {
		Position pos = new Position(this.i,this.j);
		pos.i+= direction.dirX();
		pos.j+= direction.dirY();
		return pos; 
	}

	public int getI(){
		return this.i;
	}

	public int getJ(){
		return this.j;
	}

	@Override
	public Position clone() throws CloneNotSupportedException {
		Position posCloned = new Position(this.i,this.j);
		return posCloned;
	}
}
