package Model;

import java.util.LinkedList;

public class NoeudIA  {
    private Plateau plateau;
    private Joueur joueurAcc;
    private Joueur joueurAdv;
    private int value;
    private Direction dir;
    private Position pos;
    private LinkedList<NoeudIA> fils = new LinkedList<NoeudIA>();

    public NoeudIA (Plateau p) {
        this.plateau = p;
        this.joueurAcc = this.plateau.getJoueur2();
        this.joueurAdv = this.plateau.getJoueur1();
    }

    public NoeudIA (NoeudIA n, boolean replay) {
        this.plateau = n.plateau;
        if (!replay) {//donc si on ne rejoue pas
            Joueur tmp = n.joueurAcc;
            this.joueurAcc = n.joueurAdv;
            this.joueurAdv = tmp;
        }
        else {
            this.joueurAcc = n.joueurAcc;
            this.joueurAdv = n.joueurAdv;
        }
    }

    public static boolean validState(State s) {
        return s == State.SUCCESS || s == State.PUSHOPPMARBLE || s == State.PUSHREDMARBLE;
    }

    public static void createNextNodes (NoeudIA node, int depth) {
        if (depth == 0) {
            return;
        }
        for (int i = 0; i < node.joueurAcc.tabBilles.length ; i++) {
            for (int j = 0; j < 4; j++) {
                Direction dir = Direction.values()[j];
                Position pos = node.joueurAcc.tabBilles[i];
                State state = State.WRONGDIRECTION;
                if (pos.i >= 0) {
                    state = node.plateau.push(pos,dir,node.joueurAcc,node.joueurAdv);
                }
                if (validState(state)) {
                    NoeudIA newNode = null;//initialier uniquement pour prevenir les warnings de creation de la variable
                    newNode = new NoeudIA(node, state == State.PUSHOPPMARBLE || state == State.PUSHREDMARBLE);//si on pousse une bille alors on rejoue
                    if (depth == 1) {
                        newNode.value = rateValue(node);
                    }
                    newNode.dir = dir;
                    newNode.pos = pos;
                    node.fils.add(newNode);
                    createNextNodes(newNode, depth-1);
                    if (depth != 1 && newNode.value != -999999999 && newNode.value != 99999999) {
                        newNode.value = newNode.minimax();
                    }
                    node.plateau.undoLastMove(dir,state,node.joueurAcc,node.joueurAdv,false);
                }
            }
        }
    }

    public static Move determineBestMove (Plateau p, int depth){
        NoeudIA arbre = new NoeudIA(p);
        createNextNodes(arbre, depth);//change en place arbre
        int bestValue = 999999999;
        NoeudIA bestNode = arbre.fils.getFirst();
        for (NoeudIA node : arbre.fils) {
            if (node.value < bestValue) {
                bestValue = node.value;
                bestNode = node;
            }
        }
        Move bestMove = new Move(bestNode.pos,bestNode.dir);
        return bestMove;
    }

    public int minimax () {
        if (this.joueurAcc.getColor() == Colour.BLACK) {
            int m = 999999999;
            for (NoeudIA node : fils) {
                m = Math.min(m,node.value);// anciennement recursif et l'appel etait dans le min et le max a la place de node.value (=node.minimax())
            }
            return m;
        }
        else {
            int m = -999999999;
            for (NoeudIA node : fils) {
                m = Math.max(m, node.value);
            }
            return m;
        }
    }

    private static int rateValue (NoeudIA node) {
        if (node.joueurAcc.getColor() == Colour.WHITE) {//ATTENTION PEUT ETRE A INVERSER
            return (node.joueurAcc.getBilles()-node.joueurAdv.getBilles()) * 2 +
                    (node.joueurAcc.getBillesRougesCapturees() - node.joueurAdv.getBillesRougesCapturees());
        }
        else {
            return (node.joueurAdv.getBilles()-node.joueurAcc.getBilles()) * 2 +
                    (node.joueurAdv.getBillesRougesCapturees() - node.joueurAcc.getBillesRougesCapturees());
        }
    }
}
