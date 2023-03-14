package Model;

import Controleur.Controleur;
import GUI.View;
import Model.*;

public class TestDefi {
	
	public static void main(String[] args) {

		Defi m = new Defi(0);
		View v = new View(4);
		Controleur ctrl= new Controleur(m,v.getTaille_case());
		//ctrl.defi = true;
		m.setView(v);
		v.addCtrl(ctrl);

	}
}