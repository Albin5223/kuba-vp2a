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
		/*
		Joueur j1 = new Joueur(Colour.WHITE,3);
        Joueur j2 = new Joueur(Colour.BLACK,3);
        Plateau p = new Plateau(3,j1,j2);
        try {
            NoeudIA ia = new NoeudIA(p,j1,j2);
            p.initialiseBille();
            Move move = NoeudIA.determineBestMove(p,5,j2,j1);
            System.out.println(p.push(move.pos,move.dir,j1,j2));
            System.out.println(move.pos.i + " " + move.pos.j);
            System.out.println(move.dir);
            p.affiche();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
		*/

		int n = 1;
		Model m = new Model(n,true,false);
		View v = new View(n,null);
		Controleur ctrl= new Controleur(m,v.getTaille_case());
		v.addCtrl(ctrl);
	}
}