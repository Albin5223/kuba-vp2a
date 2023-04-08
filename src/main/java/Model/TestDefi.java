package Model;

import Controleur.*;
import GUI.View;

public class TestDefi {
	
	public static void main(String[] args) {

		
		Model m = null;
		try {
			m = new Model(3,false,false,true);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		View v = new View(3,null);
		ControleurEditeur ctrl= new ControleurEditeur(m,v.getTaille_case());

		m.addObserveur(v);
        
        m.noticeObserveurs(m);
		v.addCtrlEditeur(ctrl);
		
	}
}