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

    public NoeudIA (NoeudIA n, Joueur joueurAcc, Joueur joueurAdv, Move replay) throws CloneNotSupportedException {
        this.plateau = n.plateau;
        if (replay == null) {//donc si on ne rejoue pas
            Joueur tmp = joueurAcc;
            this.joueurAcc = joueurAdv;
            this.joueurAdv = tmp;
        }
        else {
            this.joueurAcc = joueurAcc;
            this.joueurAdv = joueurAdv;
        }
        this.replay = replay;
    }

    public static boolean validState(State s) {
        return s == State.SUCCESS || s == State.PUSHOPPMARBLE || s == State.PUSHREDMARBLE;
    }

    public void createNextNodes (int depth) throws CloneNotSupportedException {
        if (this.replay != null) {
            State state = plateau.push(replay.pos,replay.dir,joueurAcc,joueurAdv);
            Joueur isOver = this.plateau.isOver(joueurAcc,joueurAdv);
            if (isOver != null) {
                NoeudIA newNode = new NoeudIA(this,joueurAcc,joueurAdv, null);
                if (isOver.getColor() == Colour.BLACK) {//L'IA doit toujours etre noir
                    newNode.value = -999999999;//puisque l'IA a gagne c'est forcement le meilleur coup
                }
                else {
                    newNode.value = 99999999;//puisque l'IA a perdu c'est forcement le pire coup
                }
                newNode.dir = replay.dir;
                newNode.pos = replay.pos;
                this.fils.add(newNode);
                plateau.undoLastMove(replay.dir,state,joueurAcc,joueurAdv,false);
            }
            else if (replay.pos.i != -1 && validState(state)) {
                NoeudIA newNode = null;//initialier uniquement pour prevenir les warnings de creation de la variable
                if (state == State.PUSHOPPMARBLE || state == State.PUSHREDMARBLE) {//si on pousse une bille alors on rejoue
                    newNode = new NoeudIA(this,joueurAcc,joueurAdv, new Move(replay.pos.goTo(dir),replay.dir));
                }
                else {
                    newNode = new NoeudIA(this,joueurAcc,joueurAdv, null);
                }
                newNode.value=newNode.rateValue();
                newNode.dir = replay.dir;
                newNode.pos = replay.pos;
                this.fils.add(newNode);
                plateau.undoLastMove(replay.dir,state,joueurAcc,joueurAdv,false);
            }
            return;
        }
        for (int i = 0; i < joueurAcc.tabBilles.length ; i++) {
            for (int j = 0; j < 4; j++) {
                Direction dir = Direction.values()[j];
                Position pos = joueurAcc.tabBilles[i];
                State state = State.WRONGDIRECTION;
                if (pos.i != -1) {
                    state = plateau.push(pos,dir,joueurAcc,joueurAdv);
                }
                Joueur isOver = this.plateau.isOver(joueurAcc,joueurAdv);
                if (isOver != null) {
                    plateau.affiche();
                    NoeudIA newNode = new NoeudIA(this,joueurAcc,joueurAdv, null);
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
                        newNode = new NoeudIA(this,joueurAcc,joueurAdv, new Move(pos.goTo(dir),dir));
                    }
                    else {
                        newNode = new NoeudIA(this,joueurAcc,joueurAdv, null);
                    }
                    newNode.value = this.rateValue();//anciennement newNode.rateValue
                    newNode.dir = dir;
                    newNode.pos = pos;
                    this.fils.add(newNode);
                    if (state != State.SUCCESS) {
                        System.out.println("joueurAcc ("+this.joueurAcc.name+") : "+this.joueurAcc.getBilles());
                        System.out.println("joueurAdv ("+this.joueurAdv.name+") : "+this.joueurAdv.getBilles());
                        System.out.println(state);
                        System.out.println(newNode.value);
                    }
                    /*
                    if (depth == 1) {
                        System.out.println("joueurAcc :"+this.joueurAcc.getBilles());
                        System.out.println("joueurAdv :"+this.joueurAdv.getBilles());
                        System.out.println("joueurAccRouges :"+this.joueurAcc.getBillesRougesCapturees());
                        System.out.println("joueurAdvRouges :"+this.joueurAcc.getBillesRougesCapturees());
                        System.out.println("value= "+newNode.value+" dir= "+dir+" pos= "+pos.i+","+pos.j);
                    }
                    */
                    plateau.undoLastMove(dir,state,joueurAcc,joueurAdv,false);
                }
            }
        }
    }

    public static Move determineBestMove (Plateau p,Joueur joueurAcc, Joueur joueurAdv, int depth) throws CloneNotSupportedException {
        NoeudIA arbre = createTree(p, depth);
        int bestValue = -999999999;
        NoeudIA bestNode = arbre.fils.getFirst();
        for (NoeudIA node : arbre.fils) {
            int minm = node.minimax(depth);
            //System.out.println(node.value);
            if (bestValue < minm) {
                //System.out.println("bestNode for now :"+node.dir+"/"+node.pos.i+","+node.pos.j);
                bestValue = minm;
                bestNode = node;
            }
        }
        Move bestMove = new Move(bestNode.pos,bestNode.dir);
        return bestMove;
    }

    public static NoeudIA createTree (Plateau p, int depth) throws CloneNotSupportedException {
        NoeudIA tete = new NoeudIA(p);
        createTreeRec(tete, depth);
        return tete;
    }

    public static void createTreeRec (NoeudIA n,int depth) throws CloneNotSupportedException {
        if (depth == 0) { return; }
        n.createNextNodes(depth);
        for (NoeudIA node : n.fils) {
            if (node.replay != null) {//si on ne rejoue pas
                createTreeRec(node, depth);
            }
            else {
                createTreeRec(node, depth - 1);
            }
        }
    }

    public int minimax (int depth) {
        if (depth == 0 || this.fils.isEmpty()) {
            return this.value;
        }
        if (this.joueurAcc.getColor()==Colour.BLACK) {
            int m = 999999999;
            for (NoeudIA node : fils) {
                m = Math.max(m, node.minimax(depth - 1));
            }
            return m;
        }
        else {
            int m = -999999999;
            for (NoeudIA node : fils) {
                m = Math.min(m, node.minimax(depth - 1));
            }
            return m;
        }
    }

    public int rateValue () {
        if (this.joueurAcc.getColor() == Colour.WHITE) {//ATTENTION PEUT ETRE A INVERSER
            return this.joueurAcc.getBilles()-this.joueurAdv.getBilles() + 
            (this.joueurAcc.getBillesRougesCapturees() - this.joueurAdv.getBillesRougesCapturees()) * 2;
        }
        else {
            return this.joueurAdv.getBilles()-this.joueurAcc.getBilles() + 
            (this.joueurAdv.getBillesRougesCapturees() - this.joueurAcc.getBillesRougesCapturees()) * 2;
        }
    }
}
