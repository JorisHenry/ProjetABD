package interfaces;

import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		System.out.println("Bienvenue dans l'interface client, veuillez saisir l'action à éxecuter (exit pour quitter)");
		Scanner scan;
		int choix;
		String str;
		do {
			System.out
					.println("1 : S'abonner au service \n"
							+ "2 : Louer un vélo \n"
							+ "3 : Réserver un vélo \n"
							+ "4 : Rendre un vélo \n"
							+ "5 : Afficher les stations Vplus/Vmoins");

			scan = new Scanner(System.in);
			str = scan.nextLine();
			choix = Integer.parseInt(str);
		} while (!(str.equals("1") || str.equals("2") || str.equals("3")));

		switch (choix) {
		case 1:
			System.out.println("Vous avez saisi : client. Redirection ...");
			break;
		case 2:
			System.out.println("Vous avez saisi : conducteur. Redirection ...");
			break;
		case 3:
			System.out
					.println("Vous avez saisi : superviseur. Redirection ...");
			break;
		default:
			break;
		}

	}

}
