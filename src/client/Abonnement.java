package client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Abonnement {
	
	private String nom,prenom,dateNais,adresse;
	private int codeSecret, numCB;
	Scanner scan = new Scanner(System.in);
	
	public void formulaireAbonnementClient(Connection conn) throws SQLException{
		System.out.println("Création d'un abonnement client\n");
		System.out.println("Nom : ");
			nom = scan.next();
		System.out.println("Prenom : ");
			prenom = scan.next();
		System.out.println("Adresse : ");
			adresse = scan.next();
		System.out.println("Date de naissance : ");
			dateNais = scan.next();
		System.out.println("Numero de CB : ");
			numCB = scan.nextInt();
		System.out.println("Entrez votre code secret : ");
			codeSecret = scan.nextInt();
		
		//Tester la syntaxe de certaine variable (numCB date etc...) Trigger ou regex ?
		//Et surtout tester s'il existe dans la bdd (Trigger)
		
		String query = "INSERT INTO (Cb,code) VALUES (" + numCB + "," + codeSecret + ")"; 	
			
		Statement state = conn.createStatement();
		
		state.executeUpdate(query);
		query = "SELECT TO_CHAR (SYSDATE, 'MM-DD-YYYY HH24:MI:SS') FROM DUAL";
		ResultSet result = state.executeQuery(query);
		String dateSys = result.getString(0);
		
		query = "INSERT INTO (nom,prenom,dateNais,adr,remise,dateAbo) VALUES (" + nom + "," 
																				+ prenom + "," 
																				+ "to_date('" + dateNais + "',' DD-Mon-YYYY')" + "," 
																				+ adresse + ", false, to_date('" + dateSys + "', 'DD-MM-YYYY'))"; 
		
		result.close();
		state.close();
	}
}
