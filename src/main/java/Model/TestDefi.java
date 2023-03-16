package Model;

import Controleur.Controleur;
import GUI.View;
import Model.*;

public class TestDefi {
	
	public static void main(String[] args) {

		Defi m = new Defi(0);
		View v = new View(3);
		Controleur ctrl= new Controleur(m,v.getTaille_case());

		m.setView2(v);
		v.addCtrl(ctrl);

	}
}