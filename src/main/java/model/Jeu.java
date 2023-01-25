import java.util.Scanner;

public class Jeu{

    Plateau plateau;
    Joueur joueurA;
    Joueur joueurB;
    int n;

    public Jeu(int n){
        plateau = new Plateau(n);
        this.n = n;
    }

    public void lancerPartie(){
        initialiseJoueur();
        plateau.initialiseBille();
        jouerPartie();

    }

    public void initialiseJoueur(){
        System.out.println("- Quel est le nom du joueur A ? : -");
        Scanner sc = new Scanner(System.in);
        String nameA = sc.next();

        joueurA = new Joueur(Color.WHITE,n,nameA);
       

        System.out.println("- Quel est le nom du joueur B ? : -");
        Scanner sc1 = new Scanner(System.in);
        String nameB = sc1.next();

        joueurB = new Joueur(Color.BLACK,n,nameB);
        
    }


    public Joueur partieFinie(){
        return plateau.isOver(joueurA, joueurB);
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
            char direction = demanderDirection();

            try{
                plateau.push(y,x,direction,joueurA,joueurB);
                boucle = false;
            }
            catch(IncorrectMoveException e){
                System.out.println(e);
            }
        }

    }

    public int demanderLigne(){
        boolean boucle = true;
        int x = 0;
        while (boucle) {
           
            Scanner sc = new Scanner(System.in);
            try {
                String s = sc.next();
                if (s.length()==1){
                    boucle = false;
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

    public char demanderDirection(){
        boolean boucle = true;
        String d = "";
        while (boucle) {
           
            Scanner sc = new Scanner(System.in);
            
            d = sc.next();
            switch (d.charAt(0)){
                case 'n':boucle = false;break;
                case 's':boucle = false;break;
                case 'e':boucle = false;break;
                case 'w':boucle = false;break;
                default: System.out.println(" - Donner une direction valide :");break;
            }
                
        }
        return d.charAt(0);
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu(2);
        jeu.lancerPartie();
        
    }
}