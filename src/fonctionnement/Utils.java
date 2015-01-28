package fonctionnement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe des fonctions utiles
 * 
 * @author Thomas
 *
 */
public class Utils {
	/**
	 * Cr� un num�ro de CD � 16 chiffres al�atoires
	 * 
	 * @return
	 */
	public static String randomCB() {
		String numerocb = "";
		for (int i = 0; i < 16; i++) {
			int chiffre = (int) (1 + (Math.random() * (59 - 1)));
			numerocb = numerocb + Integer.toString(chiffre);
		}
		return numerocb;
	}

	public static String randomJour() {
		int num = (int) (01 + (Math.random() * (28 - 01)));
		String jour = "";
		jour = jour + Integer.toString(num);
		if (jour.length() == 1) {
			jour = "0" + jour;
		}

		return jour;
	}

	public static String randomMois() {
		String Mois = "";
		int num = (int) (1 + (Math.random() * (12 - 1)));
		switch (num) {
		case 1:
			Mois = "JAN";
			break;
		case 2:
			Mois = "FEB";
			break;
		case 3:
			Mois = "MAR";
			break;
		case 4:
			Mois = "APR";
			break;
		case 5:
			Mois = "MAY";
			break;
		case 6:
			Mois = "JUN";
			break;
		case 7:
			Mois = "JUL";
			break;
		case 8:
			Mois = "AUG";
			break;
		case 9:
			Mois = "SEP";
			break;
		case 10:
			Mois = "OCT";
			break;
		case 11:
			Mois = "NOV";
			break;
		case 12:
			Mois = "DEC";
			break;
		default:
			break;
		}
		return Mois;
	}

	public static String randomCode() {
		String code = "";
		for (int i = 0; i < 8; i++) {
			int chiffre = (int) (1 + (Math.random() * (59 - 1)));
			code = code + Integer.toString(chiffre);
		}
		return code;
	}

	/**
	 * Vide le fichier de remplissage des tables
	 */
	public static void viderfichier() {
		String adressedufichier = System.getProperty("user.dir") + "/" + "Tables/Datas.sql";
		try {
			FileWriter fw = new FileWriter(adressedufichier, false);
			BufferedWriter output = new BufferedWriter(fw);
			output.write("");
			output.flush();
			output.close();
			System.out.println("Fichier vid�");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
	}

	/**
	 * Ecrit un texte dans le fichier des tables puis fait un retour � la ligne
	 * 
	 * @param texte
	 */
	public static void ecrire(String texte) {
		String adressedufichier = System.getProperty("user.dir") + "/" + "Tables/Datas.sql";

		try {
			/**
			 * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
			 * donne comme argument le nom du fichier true signifie qu on ajoute
			 * dans le fichier (append), on ne marque pas par dessus
			 */
			FileWriter fw = new FileWriter(adressedufichier, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(texte + "\n");
			output.flush();
			output.close();
			System.out.println("Ligne ajout�e");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
	}
}
