import java.util.Scanner;

public class Jeu{

	Plateau plateau;
	Joueur joueurA;
	Joueur joueurB;
	int n;

	public Jeu(int n,String nameA, String nameB){
		this.joueurA = new Joueur(Color.WHITE,n,nameA);
		this.joueurB = new Joueur(Color.BLACK,n,nameB);
		this.plateau = new Plateau(n,this.joueurA,this.joueurB);
		this.n = n;
	}

	public void lancerPartie(){
		plateau.initialiseBille();
		jouerPartie();
	}

	@SuppressWarnings("resource")
	public static String[] initialiseJoueur(){
		System.out.println("- Quel est le nom du joueur A ? : -");
		Scanner sc = new Scanner(System.in);
		String nameA = sc.nextLine();

		System.out.println("- Quel est le nom du joueur B ? : -");
		Scanner sc1 = new Scanner(System.in);
		String nameB = sc1.nextLine();

		String[] out = {nameA,nameB};
		return out;
	}

	public Joueur partieFinie(){
		return plateau.isOver();
	}

	public void jouerPartie(){
		while(partieFinie()==null){
			System.out.println("Cest à ton tour, "+joueurA.getName());
			jouerTour();
			Joueur tmp = joueurA;
			joueurA = joueurB;
			joueurB = tmp;          //On inverse les joueurs A et B car c'est joueur la variable joueurA qui représente le joueur Courant
		}
		Joueur joueurGagnant = partieFinie();
		System.out.println(joueurGagnant.gagne());
	}

	public void jouerTour(){
		boolean boucle = true;
		while(boucle){
			plateau.affiche();
			System.out.println("- Donnez la lettre de la ligne :"); //Y
			int y = demanderLigne();
			System.out.println("- Donnez le numero de la colone :"); //x
			int x = demanderColonne();

			System.out.println(" - Donner la direction :");
			String direct = demanderDirection();
			Direction direction;
			boucle = false;

			if (direct.equals("UP")) {
				direction = Direction.UP;
				plateau.push(new Position(x,y),direction);
			}
			else if (direct.equals("DOWN")) {
				direction = Direction.DOWN;
				plateau.push(new Position(x,y),direction);
			}
			else if (direct.equals("RIGHT")) {
				direction = Direction.RIGHT;
				plateau.push(new Position(x,y),direction);
			}
			else if (direct.equals("LEFT")){
				direction = Direction.LEFT;
				plateau.push(new Position(x,y),direction);
			}
			else {
				System.out.println("Veuillez entrer une direction parmi celles proposées ci-dessous :\n\t- UP\n\t- DOWN\n\t- RIGHT\n\t- LEFT\n\n");
				boucle = true;
			}
		}

	}

	@SuppressWarnings("resource")
	public int demanderLigne(){
		boolean boucle = true;
		int x = 0;
		while (boucle) {
		   
			Scanner sc = new Scanner(System.in);
			try {
				String s = sc.next();
				if (s.length()==1){
					boucle = false;
					s = s.toUpperCase();
					char c = s.charAt(0);
					x = 65 + plateau.getLongueur()-1-c;
					System.out.println("X = "+x);
				}
				else{
					System.out.println(" - Rentrer une lettre valide. - ");
				}

			   
				
			} catch (Exception e){
				System.out.println(" - Rentrer une lettre valide. - ");
				boucle = true;
			}
		}
		return x;
	}

	@SuppressWarnings("resource")
	public int demanderColonne(){
		boolean boucle = true;
		int x = 0;
		while (boucle) {
		   
			Scanner sc = new Scanner(System.in);
			try {
				x = sc.nextInt();
				boucle = false;
				x=x-1;
				System.out.println("Y : "+x);
			} catch (Exception e){
				System.err.println("- Rentrez un entier valide. -");
				boucle = true;
			}
		}
		return x;
	}

	@SuppressWarnings("resource")
	public String demanderDirection(){
		String d = "";
		System.out.println("'DOWN' -> pousser en bas");
		System.out.println("'RIGHT' -> pousser à droite");
		System.out.println("'UP' -> pousser en haut");
		System.out.println("'LEFT' -> pousser à gauche");

		Scanner sc = new Scanner(System.in);
		d = sc.next();
		return d;
	}

<<<<<<< HEAD
	public static void main(String[] args) {
		String[] tmp = initialiseJoueur();
		Jeu jeu = new Jeu(4,tmp[0],tmp[1]);
		jeu.lancerPartie();
		
	}
=======
    public static void main(String[] args) {
                
        Jeu jeu = new Jeu(1);
        jeu.lancerPartie();
        
    }
>>>>>>> 865db4807435e978497828385c23b6ed0c1c18a5
}