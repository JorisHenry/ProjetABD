package Utiles;

import java.util.Scanner;

public class Lancement {

	public static void main(String[] args) {
		Scanner scan;
		int choix;
		String str;
		do {
			System.out
					.println("Veuillez sélectionner l'interface que vous souhaitez utiliser : \n"
							+ "client : 1\n"
							+ "conducteur : 2\n"
							+ "superviseur : 3");

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
