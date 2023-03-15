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
		int n = 5;
		Model m = new Model(n);
		View v = new View(n);
		Controleur ctrl= new Controleur(m,v.getTaille_case());
		m.setView(v);
		v.addCtrl(ctrl);

	}
}