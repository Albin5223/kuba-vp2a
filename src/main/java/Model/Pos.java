package Model;

public class Pos {
	public int i;
	public int j;

	public Pos (int i, int j) {
		this.i = i;
		this.j = j;
	}

	public Pos goTo(Direction direction) {
		Pos pos = new Pos(this.i,this.j);
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
}
