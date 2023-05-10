package Model;

public class Move {
    public Position pos;
    public Direction dir;

    public Move (Position pos, Direction dir) {
        this.pos=pos;
        this.dir=dir;
    }

    public String toString(){
        return pos.toString()+"/"+dir.toString();
    }
}


