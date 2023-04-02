package Model;


import Controleur.Controleur;
import GUI.View;

public class Test {
	
	public static void main(String[] args) {
		//----------------------TEST PLATEAU--------------------
		//Joueur j1 = new Joueur(Colour.BLACK,3);
		//Joueur j2 = new Joueur(Colour.WHITE,3);
		/* 
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.push(new Position(0,0),Direction.SOUTH,j1,j2);
		p.affiche();
		State test = p.push(new Position(3,0),Direction.NORTH,j1,j2);
		p.affiche();
		System.out.println(test);
		//p.push(new Position(0,0),Direction.SOUTH,j1,j2)
		//IA ia = new IA(Color.BLACK,3);
		*/


		//--------------------------TEST MVC-----------------
		//Plateau p = new Plateau(3);
		//p.initialiseBille();
		//System.out.println("State : "+p.push(new Position(0,9),Direction.SOUTH,j1,j2));
		
		//p.affiche();


		//----------------------TEST GRAPHIQUE-----------------------

		Joueur j1 = new Joueur(Colour.WHITE,3,"Test1");
        Joueur j2 = new Joueur(Colour.BLACK,3,"Test2");
        Plateau p = new Plateau(3,j1,j2);
		p.initialiseBille();
		State state1 = p.push(new Position(0, 1),Direction.SOUTH,j1,j2);
		State state2 = p.push(new Position(1, 1),Direction.SOUTH,j1,j2);
		State state3 = p.push(new Position(2, 1),Direction.SOUTH,j1,j2);
		State state4 = p.push(new Position(3, 1),Direction.SOUTH,j1,j2);
		State state5 = p.push(new Position(4, 1),Direction.SOUTH,j1,j2);
		State state6 = p.push(new Position(5, 1),Direction.SOUTH,j1,j2);
		State state7 = p.push(new Position(6, 1),Direction.SOUTH,j1,j2);
		State state8 = p.push(new Position(7, 1),Direction.SOUTH,j1,j2);
		State state9 = p.push(new Position(8, 1),Direction.SOUTH,j1,j2);
		p.affiche();
		p.undoLastMove(Direction.SOUTH, state9, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state8, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state7, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state6, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state5, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state4, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state3, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state2, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state1, j1, j2, false);
		p.affiche();
		/*
		int n = 1;
		Model m = new Model(n,true);
		View v = new View(n,null);
		Controleur ctrl= new Controleur(m,v.getTaille_case());
		m.setView(v);
		v.addCtrl(ctrl);
		*/
	}
}