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
    private Move replay;

    public NoeudIA (Plateau p) throws CloneNotSupportedException {
        this.plateau = p.clone();
        this.joueurAcc = this.plateau.getJoueur2();
        this.joueurAdv = this.plateau.getJoueur1();
    }

    public NoeudIA (NoeudIA n, Move replay) throws CloneNotSupportedException {
        this.plateau = n.plateau.clone();
        if (replay == null) {//donc si on ne rejoue pas
            Joueur tmp = n.joueurAcc.clone();
            this.joueurAcc = n.joueurAdv.clone();
            this.joueurAdv = tmp;
        }
        else {
            this.joueurAcc = n.joueurAcc.clone();
            this.joueurAdv = n.joueurAdv.clone();
        }
        this.replay = replay;
    }

    public static boolean validState(State s) {
        return s == State.SUCCESS || s == State.PUSHOPPMARBLE || s == State.PUSHREDMARBLE;
    }

    public void createNextNodes (int depth) throws CloneNotSupportedException {
        for (int i = 0; i < joueurAcc.tabBilles.length ; i++) {
            for (int j = 0; j < 4; j++) {
                Direction dir = Direction.values()[j];
                Position pos = joueurAcc.tabBilles[i];
                State state = State.WRONGDIRECTION;
                if (pos.i != -1) {
                    state = plateau.push(pos,dir,joueurAcc,joueurAdv);
                }
                Joueur isOver = this.plateau.isOver(joueurAcc,joueurAdv);
                if (isOver != null && validState(state)) {
                    System.out.println("ATTENTION FINI !");
                    NoeudIA newNode = null;
                    if (state == State.PUSHOPPMARBLE || state == State.PUSHREDMARBLE) {//si on pousse une bille alors on rejoue
                        newNode = new NoeudIA(this, new Move(pos.goTo(dir),dir));
                    }
                    else {
                        newNode = new NoeudIA(this, null);
                    }
                    //on ne change la valeur de la node pas que en depth 1 car si c'est la fin du jeu qui est atteinte
                    //on doit le savoir avant
                    if (isOver.getColor() == Colour.BLACK) {//L'IA doit toujours etre noir
                        newNode.value = -999999999;//puisque l'IA a gagne c'est forcement le meilleur coup
                    }
                    else {
                        newNode.value = 99999999;//puisque l'IA a perdu c'est forcement le pire coup
                    }
                    newNode.dir = dir;
                    newNode.pos = pos;
                    this.fils.add(newNode);
                    plateau.undoLastMove(dir,state,joueurAcc,joueurAdv,false);
                }
                else if (validState(state)) {
                    NoeudIA newNode = null;//initialier uniquement pour prevenir les warnings de creation de la variable
                    if (state == State.PUSHOPPMARBLE || state == State.PUSHREDMARBLE) {//si on pousse une bille alors on rejoue
                        newNode = new NoeudIA(this, new Move(pos.goTo(dir),dir));
                    }
                    else {
                        newNode = new NoeudIA(this, null);
                    }
                    if (depth == 1) {
                        newNode.value = this.rateValue();//ATTENTION PEUT-ETRE newNode.rateValue
                    }
                    else {
                        //newNode.value = this.minimax(depth);
                    }
                    newNode.dir = dir;
                    newNode.pos = pos;
                    this.fils.add(newNode);
                    plateau.undoLastMove(dir,state,joueurAcc,joueurAdv,false);
                }
            }
        }
    }

    public static Move determineBestMove (Plateau p,Joueur joueurAcc, Joueur joueurAdv, int depth) throws CloneNotSupportedException {
        NoeudIA arbre = createTree(p, depth);
        int bestValue = 999999999;
        NoeudIA bestNode = arbre.fils.getFirst();
        System.out.println();
        for (NoeudIA node : arbre.fils) {
            System.out.println(node.value+" = "+node.dir+"/"+node.pos.i+","+node.pos.j);
            if (node.value < bestValue) {
                bestValue = node.value;
                bestNode = node;
            }
        }
        System.out.println("move choisi : " + bestNode.value+" = "+bestNode.dir+"/"+bestNode.pos.i+","+bestNode.pos.j);
        Move bestMove = new Move(bestNode.pos,bestNode.dir);
        return bestMove;
    }

    public static NoeudIA createTree (Plateau p, int depth) throws CloneNotSupportedException {
        NoeudIA tete = new NoeudIA(p);
        createTreeRec(tete, depth);
        return tete;
    }

    public static void createTreeRec (NoeudIA n,int depth) throws CloneNotSupportedException {
        if (depth == 0) {
            return;
        }
        n.createNextNodes(depth);
        for (NoeudIA node : n.fils) {
            createTreeRec(node, depth - 1);
            if (node.value != -999999999 && node.value != 99999999) {//si le jeu est fini on a pas besoin de remplacer la valeur
                node.value = node.minimax();
            }
        }
    }

    public int minimax () {
        if (this.fils.isEmpty()) {
            return this.value;
        }
        if (this.joueurAcc.getColor() == Colour.BLACK) {
            int m = 999999999;
            for (NoeudIA node : fils) {
                m = Math.min(m, node.minimax());
            }
            return m;
        }
        else {
            int m = -999999999;
            for (NoeudIA node : fils) {
                m = Math.max(m, node.minimax());
            }
            return m;
        }
    }

    public int rateValue () {
        if (this.joueurAcc.getColor() == Colour.WHITE) {//ATTENTION PEUT ETRE A INVERSER
            return (this.joueurAcc.getBilles()-this.joueurAdv.getBilles()) * 2 + 
            (this.joueurAcc.getBillesRougesCapturees() - this.joueurAdv.getBillesRougesCapturees());
        }
        else {
            return (this.joueurAdv.getBilles()-this.joueurAcc.getBilles()) * 2 + 
            (this.joueurAdv.getBillesRougesCapturees() - this.joueurAcc.getBillesRougesCapturees());
        }
    }
}
