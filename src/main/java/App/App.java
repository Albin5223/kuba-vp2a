package App;

import GUI.Window;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    new Window();
                }   
            }
		);
    }
}
