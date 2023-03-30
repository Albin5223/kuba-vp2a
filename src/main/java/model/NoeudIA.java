package Model;

import java.util.LinkedList;

public class NoeudIA  {
    private Plateau plateau;
    protected Joueur joueurAcc;
    protected Joueur joueurAdv;
    protected int value;
    protected Direction dir;
    protected Position pos;
    protected LinkedList<NoeudIA> fils = new LinkedList<NoeudIA>();

    public NoeudIA (Plateau p, Joueur joueurAcc, Joueur joueurAdv) throws CloneNotSupportedException {
        this.plateau = p;
        this.joueurAcc = joueurAcc;
        this.joueurAdv = joueurAdv;
    }

    public NoeudIA (NoeudIA n, Plateau p) throws CloneNotSupportedException {
        plateau = n.plateau.clone();
        joueurAcc = n.joueurAdv;//on fait jouer l'adversaire
        joueurAdv = n.joueurAcc;
    }

    public static boolean validState(State s) {
        return s == State.SUCCESS || s == State.PUSHOPPMARBLE || s == State.PUSHREDMARBLE;
    }

    public void createNextNodes () throws CloneNotSupportedException {
        for (int i = 0; i < joueurAdv.tabBilles.length ; i++) {
            for (int j = 0; j < 4; j++) {
                Direction dir = Direction.values()[j];
                if (this.plateau.isOver(joueurAcc,joueurAdv) != null) {
                    return;
                }
                if (joueurAdv.tabBilles[i].i != -1 && validState(plateau.push(joueurAdv.tabBilles[i],dir,joueurAdv,joueurAcc))) {
                    NoeudIA newNode = new NoeudIA(this,this.plateau);
                    newNode.value=newNode.rateValue();
                    newNode.dir=dir;
                    newNode.pos=joueurAdv.tabBilles[i];
                    fils.add(newNode);
                    plateau.undoLastMove();
                }
            }
        }
    }

    public static Move determineBestMove (Plateau p, int depth, Joueur joueurAcc, Joueur joueurAdv) throws CloneNotSupportedException {
        NoeudIA arbre = createTree(p, depth, joueurAcc, joueurAdv);
        int bestValue = -999999999;
        NoeudIA bestNode = arbre.fils.getFirst();
        for (NoeudIA node : arbre.fils) {
            int minm = node.minimax(depth);
            if (bestValue < minm ) {
                bestValue = minm;
                bestNode = node;
            }
        }
        Move bestMove = new Move(bestNode.pos,bestNode.dir);
        return bestMove;
    }

    public static NoeudIA createTree (Plateau p, int depth, Joueur joueurAcc, Joueur joueurAdv) throws CloneNotSupportedException {
        NoeudIA tete = new NoeudIA(p,joueurAcc,joueurAdv);
        createTreeRec(tete, depth);
        return tete;
    }

    public static void createTreeRec (NoeudIA n, int depth) throws CloneNotSupportedException {
        if (depth == 0) { return; }
        n.createNextNodes();
        for (NoeudIA node : n.fils) {
            createTreeRec(node, depth - 1);
        }
    }

    public int minimax (int depth) {
        if (depth == 0 || this.fils.isEmpty()) {
            return this.value;
        }
        if (this.joueurAcc.getColor()==Colour.WHITE) {
            int m = -999999999;
            for (NoeudIA node : fils) {
                m = Math.max(m, node.minimax(depth - 1));
            }
            return m;
        }
        else {
            int m = 999999999;
            for (NoeudIA node : fils) {
                m = Math.min(m, node.minimax(depth - 1));
            }
            return m;
        }
    }

    public int rateValue () { //A FAIRE
        return this.joueurAcc.getBilles()-this.joueurAdv.getBilles()
        + this.joueurAcc.getBillesRougesCapturees() - this.joueurAdv.getBillesRougesCapturees();
    }
}