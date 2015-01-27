package fonctionnement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
	public static String randomCB() {
		String num�rocb = "";
		for (int i = 0; i < 16; i++) {
			int chiffre = (int) (1 + (Math.random() * (59 - 1)));
			num�rocb = num�rocb + Integer.toString(chiffre);
		}
		return num�rocb;
	}

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