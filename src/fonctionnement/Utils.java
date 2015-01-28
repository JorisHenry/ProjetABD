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
	 * Cré un numéro de CD à 16 chiffres aléatoires
	 * 
	 * @return
	 */
	public static String randomCB() {
		String numérocb = "";
		for (int i = 0; i < 16; i++) {
			int chiffre = (int) (1 + (Math.random() * (59 - 1)));
			numérocb = numérocb + Integer.toString(chiffre);
		}
		return numérocb;
	}
	public static String randomCode() {
		String numérocb = "";
		for (int i = 0; i < 8; i++) {
			int chiffre = (int) (1 + (Math.random() * (59 - 1)));
			numérocb = numérocb + Integer.toString(chiffre);
		}
		return numérocb;
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
			System.out.println("Fichier vidé");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
	}

	/**
	 * Ecrit un texte dans le fichier des tables puis fait un retour à la ligne
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
			System.out.println("Ligne ajoutée");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}
	}
}
