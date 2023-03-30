package Model;

import Controleur.Controleur;
import GUI.View;

public class TestDefi {
	
	public static void main(String[] args) {

		
		Model m = new Model(0,false,true);
		View v = new View(3,null);
		Controleur ctrl= new Controleur(m,v.getTaille_case());

		m.addObserveur(v);
        
        m.noticeObserveurs(m);
		v.addCtrl(ctrl);
		
	}
}