package Model;

import Controleur.Controleur;
import GUI.View;

public class TestDefi {
	
	public static void main(String[] args) {

		
		Model m = new Model(0,true);
		View v = new View(3);
		Controleur ctrl= new Controleur(m,v.getTaille_case());

		m.setView2(v);
		v.addCtrl(ctrl);
		
	}
}