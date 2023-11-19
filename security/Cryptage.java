import java.util.Scanner;

public class Cryptage {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        // Message présentant le logiciel
        System.out.println("Bienvenue dans le logiciel de cryptage et de décryptage de messages\n");

        System.out.println("Taper <Entrée> pour continuer...");
        sc.nextLine();

        System.out.println("\n====================================================================\n");
        
        System.out.println("Ce logiciel permet de crypter et décrypter des messages en utilisant des techniques de cryptographie");
        System.out.println("Il permet de crypter et décrypter des messages en utilisant les techniques suivantes :");
        System.out.println("1. Rotation de la chaîne");
        System.out.println("2. Permutation de la chaîne");
        System.out.println("3. Inversion de la chaîne");

        System.out.println("Taper <Entrée> pour continuer...");
        sc.nextLine();
                
        String choix = "";
        do {
            // On efface l'écran
            System.out.print("\033[H\033[2J");
            System.out.println("\n====================================================================\n");

        // On présente le menu(Cryptage ou décryptage ou quitter)
        System.out.println("-------------------------");
        System.out.println("Menu");
        System.out.println("-------------------------\n");
        System.out.println("1. Crypter un message");
        System.out.println("2. Décrypter un message");
        System.out.println("3. Quitter\n");

        // On demande à l'utilisateur de choisir une option
        System.out.print("Votre choix : ");

        // On récupère le choix de l'utilisateur
        choix = sc.nextLine();

        // On vérifie que l'utilisateur a bien choisi une option
        if (!choix.matches("[1-3]")) {
            System.out.println("Veuillez choisir une option valide(1, 2 ou 3)");
            System.out.println("-------------------------");
            System.out.println("Menu");
            System.out.println("-------------------------");
            System.out.println("1. Crypter un message");
            System.out.println("2. Décrypter un message");
            System.out.println("3. Quitter");

            System.out.print("Votre choix : ");
            choix = sc.nextLine();
        }

        // Si l'utilisateur n'a pas choisi de quitter
        if(!choix.equals("3")){

            String typeOperation = "cryptage";
            if(choix.equals("2")){
                typeOperation = "décryptage";
            }
            // On demande à l'utilisateur de saisir le message à crypter ou décrypter
            System.out.print("Saisir le message : ");
            String message = sc.nextLine();


            // On demande à l'utilisateur d'entrer la clé de cryptage ou de décryptage
            System.out.print("Veuillez saisir une clé de " + typeOperation + " valide\n");
            // On indique le format de la clé (2 lettres représentant le type de cryptage et un nombre représentant le nombre de fois où on effectue l'opération)
            System.out.print("(Format : <type de cryptage><nombre de fois où on effectue l'opération><type de cryptage><nombre de fois où on effectue l'opération>...) : ");
            System.out.println("Exemple : RG2RD3PE1");
            
            // Liste des types de cryptage ou de décryptage disponibles
            System.out.println("Liste des types de " + typeOperation + " disponibles :");
            System.out.println("1. Rotation Gauche (RG)");
            System.out.println("2. Rotation Droite (RD)");
            System.out.println("3. Permutation Exterieure (PE)");
            System.out.println("4. Permutation Interieure (PI)");
            System.out.println("5. Inversion (IV)\n");

            System.out.print("Veuillez saisir la clé de " + typeOperation + " : ");

            // On récupère la clé de cryptage ou de décryptage
            String cle = sc.nextLine();

            // On vérifie que la clé est bien au bon format
            while (!cleValide(cle)) {
                // On efface l'écran
                System.out.print("\033[H\033[2J");
                System.out.println("Veuillez saisir une clé valide");
                System.out.print("(Format : <type de cryptage><nombre de fois où on effectue l'opération><type de cryptage><nombre de fois où on effectue l'opération>...) : ");
                System.out.println("Exemple : RG2RD3PE1");
                
                // Liste des types de cryptage ou de décryptage disponibles
                System.out.println("Liste des types de cryptage ou de décryptage disponibles :");
                System.out.println("1. Rotation Gauche (RG)");
                System.out.println("2. Rotation Droite (RD)");
                System.out.println("3. Permutation Exterieure (PE)");
                System.out.println("4. Permutation Interieure (PI)");
                System.out.println("5. Inversion (IV)\n");
                System.out.print("Veuillez saisir la clé de " + typeOperation + " : ");
                cle = sc.nextLine();
            }

            // On efface l'écran
            System.out.print("\033[H\033[2J");


            // On affiche le message crypté ou décrypté
            String messageCrypte = "";
            if(choix.equals("1")){
                messageCrypte = crypter(cle, message);
            }else{
                messageCrypte = decrypter(cle, message);
            }

            System.out.println("Message " + (choix.equals("1") ? "crypté" : "décrypté") + " : " + messageCrypte);

            System.out.println("\n====================================================================\n");

            System.out.println("Taper <Entrée> pour continuer...");
            sc.nextLine();

            // On présente le menu(Cryptage ou décryptage ou quitter)
            System.out.println("\n====================================================================\n");

        }else{
            System.out.println("Au revoir !");
        }

    } while (!choix.equals("3"));

    sc.close();
    
    }


    public static String rotateLeft(String s, int n) {
        n = n % s.length(); // si n est supérieur à la longueur de la chaîne, on prend le reste de la division
        return s.substring(n) + s.substring(0, n); // on déplace les n premiers caractères à la fin de la chaîne et on renvoie le résultat
    }

    public static String rotateRight(String s, int n) {
        n = n % s.length(); // si n est supérieur à la longueur de la chaîne, on prend le reste de la division
        return s.substring(s.length() - n) + s.substring(0, s.length() - n); // on déplace le dernier caractère n fois au début de la chaîne et on renvoie le résultat
    }

    public static String permutationExterieure(String chaine, int nbPermutations) {
        // On recupere la longueur de la chaine
        int longueur = chaine.length();
        if (longueur < 2 || nbPermutations < 1) {
            return chaine;
        }

        // Les caracteres de la chaine
        char[] caracteres = chaine.toCharArray();
        // Le debut de la chaine
        int debut = 0;
        // La fin de la chaine
        int fin = longueur - 1;
        // Le nombre de permutations à effectuer
        int nbPermutationsRestantes = nbPermutations % longueur;
        // On effectue les permutations
        for (int i = 0; i < nbPermutationsRestantes; i++) {
            // On sauvegarde le premier caractere
            char temp = caracteres[debut];
            
            // On deplace le dernier caractere à la place du premier
            caracteres[debut] = caracteres[fin];

            // On deplace le caractere sauvegardé à la fin
            caracteres[fin] = temp;

            // On deplace le debut de la chaine
            debut++;
            // On deplace la fin de la chaine
            fin--;

            // Si on arrive au milieu de la chaine, on recommence
            if (debut > fin) {
                debut = 0;
                fin = longueur - 1;
            }
        }

        // On renvoie la chaine modifiée
        return new String(caracteres);

    }


    public static String permutationInterieure(String chaine, int nbPermutations) {
        // On recupere la longueur de la chaine
        int longueur = chaine.length();
        if (longueur < 2 || nbPermutations < 1) {
            return chaine;
        }

        // Les caracteres de la chaine
        char[] caracteres = chaine.toCharArray();
        // Le debut de la chaine c'est le caractere du milieu
        int debut = (longueur % 2 == 0) ? longueur / 2 - 1 : longueur / 2;
        // La fin de la chaine c'est le caractere du milieu
        int fin = longueur / 2;

        // Le nombre de permutations à effectuer
        int nbPermutationsRestantes = nbPermutations % longueur;
        // On effectue les permutations  de la chaine de l'interieur vers l'exterieur
        for (int i = 0; i < nbPermutationsRestantes; i++) {
            // On sauvegarde le premier caractere
            char temp = caracteres[debut];
            
            // On deplace le dernier caractere à la place du premier
            caracteres[debut] = caracteres[fin];

            // On deplace le caractere sauvegardé à la fin
            caracteres[fin] = temp;

            // On deplace le debut de la chaine
            debut++;
            // On deplace la fin de la chaine
            fin--;

            // Si on arrive au milieu de la chaine, on recommence
            if (debut > fin) {
                debut = (longueur % 2 == 0) ? longueur / 2 - 1 : longueur / 2;
                fin = longueur / 2;
            }
        }

        // On renvoie la chaine modifiée
        return new String(caracteres);
    }


    public static String inversion(String s, int n) {
        // On recupere la longueur de la chaine
        int longueur = s.length();
        if (n <= 1 || n >= longueur) {
            return s;
        }

        // Les caracteres de la chaine
        char[] caracteres = s.toCharArray();
        
        // On inverse les n premiers caracteres de la chaine
        // On deplace le 1er caractere à la position n, 2eme à la position n-1, etc...
        for (int i = 0; i < n / 2; i++) {
            // On sauvegarde le caractere
            char temp = caracteres[i];
            // On deplace le caractere de la fin à la place du caractere de la position i
            caracteres[i] = caracteres[n - i - 1];
            // On deplace le caractere sauvegardé à la position n - i - 1
            caracteres[n - i - 1] = temp;
        }

        // On inverse les n derniers caracteres de la chaine
        // Les caracteres compris entre longueur - n - 1 et longueur - 1
        // On deplace le caractere de la position longueur - n à la position longueur - 1, etc...
        for (int i = longueur - n; i < longueur - n / 2; i++) {
            // On sauvegarde le caractere
            char temp = caracteres[i];
            // On deplace le caractere de la fin à la place du caractere de la position i
            caracteres[i] = caracteres[longueur - i + longueur - n - 1];
            // On deplace le caractere sauvegardé à la position n - i - 1
            caracteres[longueur - i + longueur - n - 1] = temp;
        }

        return new String(caracteres);
    }

    public static String inversionI(String s, int n) {

        // On recupere la longueur de la chaine
        int longueur = s.length();
        if (n <= 1 || n >= longueur) {
            return s;
        }

        // Les caracteres de la chaine
        char[] caracteres = s.toCharArray();

        // On inverse les n derniers caracteres de la chaine
        // Les caracteres compris entre longueur - n - 1 et longueur - 1
        // On deplace le caractere de la position longueur - n à la position longueur - 1, etc...
        for (int i = longueur - n; i < longueur - n / 2; i++) {
            // On sauvegarde le caractere
            char temp = caracteres[i];
            // On deplace le caractere de la fin à la place du caractere de la position i
            caracteres[i] = caracteres[longueur - i + longueur - n - 1];
            // On deplace le caractere sauvegardé à la position n - i - 1
            caracteres[longueur - i + longueur - n - 1] = temp;
        }

        // On inverse les n premiers caracteres de la chaine
        // On deplace le 1er caractere à la position n, 2eme à la position n-1, etc...
        for (int i = 0; i < n / 2; i++) {
            // On sauvegarde le caractere
            char temp = caracteres[i];
            // On deplace le caractere de la fin à la place du caractere de la position i
            caracteres[i] = caracteres[n - i - 1];
            // On deplace le caractere sauvegardé à la position n - i - 1
            caracteres[n - i - 1] = temp;
        }

        return new String(caracteres);
    }

    

    /**
    * Crypte le msg donne avec la cle de cryptage donnee, et retourne
    * le message crypte.
    *
    * ANTECEDENT : la cle et le msg doivent etre valides.
    *
    * @param cle la cle de cryptage
    * @param msg le message a cripter avec la cle donnee
    * @return le message crypte
    */
    public static String crypter(String cle, String msg){

        // On verifie si la cle est valide
        if (!cleValide(cle)) {
            return null;
        }

        // On effectue les operations de cryptage dans l'ordre
        // dans lequel elles sont donnees dans la cle
        for (int i = 0; i < cle.length(); i += 4) {
            // On recupere le type d'operation
            char typeOperation = cle.charAt(i);
            // On met le type d'operation en majuscule
            typeOperation = Character.toUpperCase(typeOperation);
            // On recupere le nombre d'operations a effectuer
            int nbOperations = Integer.parseInt(cle.substring(i + 2, i + 4));
            // On effectue l'operation
            switch (typeOperation) {
                case 'R':
                    // Rotation
                    if (Character.toUpperCase(cle.charAt(i + 1)) == 'G') {
                        // Rotation gauche
                        msg = rotateLeft(msg, nbOperations);
                    } else {
                        // Rotation droite
                        msg = rotateRight(msg, nbOperations);
                    }
                    break;
                case 'P':
                    // Permutation
                    if (Character.toUpperCase(cle.charAt(i + 1)) == 'E') {
                        // Permutation exterieure
                        msg = permutationExterieure(msg, nbOperations);
                    } else {
                        // Permutation interieure
                        msg = permutationInterieure(msg, nbOperations);
                    }
                    break;
                case 'I':
                    // Inversion
                    msg = inversion(msg, nbOperations);
                    break;
                default:
                    break;
            }
        }

        return msg;
    }

    /**
    * Decrypte le msg donne avec la cle de cryptage donnee, et retourne
    * le message decrypte.
    *
    * ANTECEDENT : la cle et le msg doivent etre valides.
    *
    * @param cle la cle de cryptage
    * @param msg le message a decripter avec la cle donnee
    * @return le message decrypte
    */
    public static String decrypter(String cle, String msg){

        // On verifie si la cle est valide
        if (!cleValide(cle)) {
            return null;
        }

        // On inverse l'ordre des operations
        String cleInverse = "";
        for (int i = cle.length() - 4; i >= 0; i -= 4) {
            cleInverse += cle.substring(i, i + 4);
        }

        cle = cleInverse;
        System.out.println(cle);

        // On effectue les operations de cryptage dans l'ordre
        // dans lequel elles sont donnees dans la cle
        for (int i = 0; i < cle.length(); i += 4) {
            // On recupere le type d'operation
            char typeOperation = cle.charAt(i);
            // On met le type d'operation en majuscule
            typeOperation = Character.toUpperCase(typeOperation);
            // On recupere le nombre d'operations a effectuer
            int nbOperations = Integer.parseInt(cle.substring(i + 2, i + 4));
            // On effectue l'operation
            switch (typeOperation) {
                case 'R':
                    // Rotation
                    if (Character.toUpperCase(cle.charAt(i + 1)) == 'G') {
                        // Rotation gauche
                        msg = rotateRight(msg, nbOperations);
                    } else {
                        // Rotation droite
                        msg = rotateLeft(msg, nbOperations);
                    }
                    break;
                case 'P':
                    // Permutation
                    if (Character.toUpperCase(cle.charAt(i + 1)) == 'E') {
                        // Permutation exterieure
                        msg = permutationInterieure(msg, nbOperations);
                    } else {
                        // Permutation interieure
                        msg = permutationExterieure(msg, nbOperations);
                    }
                    break;
                case 'I':
                    // Inversion
                    msg = inversionI(msg, nbOperations);
                    break;
                default:
                    break;
            }
        }

        return msg;
    }


    /**
     * Verifie si la cle est valide.
     * Une cle est valide si elle contient un multiple de 4 caracteres(2 lettres et 2 chiffres)
     * @param cle la cle a verifier
     * @return true si la cle est valide, false sinon
     */
    public static boolean cleValide(String cle){
    
        // Longueur de la cle
        int longueur = cle.length();

        // Si la longueur de la cle n'est pas un multiple de 4, la cle n'est pas valide
        if (longueur % 4 != 0) {
            return false;
        }

        // On analyse la cle par paquet de 4 caracteres et on verifie si on a
        // une rotation gauche(RG) ou une rotation droite(RD), une permutation exterieure(PE),
        // une permutation interieure(PI) ou une inversion(I) suivie d'un nombre compris entre 00 et 99
        for (int i = 0; i < longueur; i += 4) {
            // On recupere le type de transformation
            String typeTransformation = cle.substring(i, i + 2);
            // On recupere le nombre de transformation

            int nbTransformation = 0;
            try {
                nbTransformation = Integer.parseInt(cle.substring(i + 2, i + 4));
            } catch (NumberFormatException e) {
                return false;
            }

            // Si le type de transformation n'est pas valide, la cle n'est pas valide
            if (!typeTransformation.equalsIgnoreCase("RG") && !typeTransformation.equalsIgnoreCase("RD") && !typeTransformation.equalsIgnoreCase("PE") && !typeTransformation.equalsIgnoreCase("PI") && !typeTransformation.equalsIgnoreCase("IV")) {
                return false;
            }

            // Si le nombre de transformation n'est pas valide, la cle n'est pas valide
            if (nbTransformation < 0 || nbTransformation > 99) {
                return false;
            }
        }

        // Si on arrive ici, la cle est valide
        return true;
    }
        







}
