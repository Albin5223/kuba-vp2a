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

		Joueur j1 = new Joueur(Colour.WHITE,1,"Test1");
        Joueur j2 = new Joueur(Colour.BLACK,1,"Test2");
        Plateau p = new Plateau(1,j1,j2);
		p.initialiseBille();
		State state1 = p.push(new Position(0, 0),Direction.SOUTH,j1,j2);
		State state2 = p.push(new Position(1, 0),Direction.SOUTH,j1,j2);
		p.affiche();
		j1.afficheTab();
		j2.afficheTab();
		p.undoLastMove(Direction.SOUTH, state2, j1, j2, false);
		p.undoLastMove(Direction.SOUTH, state1, j1, j2, false);
		p.affiche();
		j1.afficheTab();
		j2.afficheTab();
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