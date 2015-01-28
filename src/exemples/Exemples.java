package exemples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exemples {
	
	/**
	 * Etant donné un achat, affiche les informations concernant l’ensemble des places achetées.
	 * 
	 * @param conn : Connexion à la BDD
	 * @param noDossier : le numero du dossier
	 * @throws SQLException : En cas d'erreur d'accès à la BDD
	 */
	public static void afficheInfosDossier(Connection conn, int noDossier) throws SQLException {
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt
				.executeQuery("SELECT numT, numS, dateEmission, dateRep, noDossier, numZ, noRang, noPlace "
						+ "FROM LesTickets natural join LesPlaces natural join lesDossiers "
						+ "WHERE noDossier = " + noDossier);

		// Loop through the result set
		if (rs.next()) {
			System.out.println("Informations sur le Dossier " + noDossier + " :"+"\n");
			do {	
				System.out.println("Numéro de Ticket : " + rs.getString("numT")+ "\n"
						+"Numéro de spéctacle : "+ rs.getString("numS")+ "\n"
						+"Date d'émission : "+ rs.getString("dateEmission")+ "\n"
						+"Date Reception : " + rs.getString("dateRep")+ "\n"
						+"Numéro de dossier : "+ rs.getString("noDossier")+ "\n"
						+"Numéro de zone : " + rs.getString("numZ")+ "\n"
						+"noRang : " + rs.getString("noRang")+ "\n"
						+"no Place : " + rs.getString("noPlace")+"\n");
			} while (rs.next());
		} else {
			System.out.println("Aucun dossier ne correspond au numéro " + noDossier + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
	}
	/**
     * Effectue la réservation d’une place d’une catégorie donnée pour une représentation de spectacle donnée.
     * @param conn : Connexion à la BDD
     * @param categorie : Categorie de la zone de reservation
     * @param date : Date de la représentation
     * @throws SQLException : En cas d'erreur d'accès à la BDD
     */
	public static void ReserveUnePlace(Connection conn, String categorie, String date) throws SQLException {
	      	// Get a statement from the connection
	      	Statement stmt = conn.createStatement() ;
	      	
		// Il doit rester 70 places à vendre au guichet
		if (!(Supplement.compteTotalPlacesDispo(conn, date) < 70)) {
			// Il doit y avoir au moins une place pour faire la reservation
			if (!(Supplement.comptePlacesDispoCategorie(conn, categorie, date) < 1)) {

				// calcul du prix de la place
				int prix = LesCategories.affichePrixPlaceZone(conn, categorie);
				
				//Recherche du numero de spectacle
				int numS = Supplement.renvoiNumeroSpectacle(conn, date);
				
				// Recherche du premier numéro de dossier libre
				int noDossier = 1;
				ResultSet rsNoDossier = stmt.executeQuery("Select noDossier from LesDossiers");
				if (rsNoDossier.next()) {
					do {
						noDossier = rsNoDossier.getInt("noDossier") + 1;
					} while (rsNoDossier.next());
				} else {
					noDossier = 1;
				}
				System.out.println("noDossier = " + noDossier);

				// Recherche du premier numéro de ticket libre
				int numT = 1555;
				ResultSet rsNoTicket = stmt.executeQuery("Select numT from LesTickets");
				if (rsNoTicket.next()) {
					do {
						numT = rsNoTicket.getInt("numT") + 1;
					} while (rsNoTicket.next());
				} else {
					numT = 1;
				}
				System.out.println("numT = " + numT);
				
				//Recherche de la première place libre
				//On recupére la première ligne d'affichage des palces dispo
				String str = LesRepresentations.affichePlacesDispo(conn, date);
				//On la transforme en tableau de char qu'on converti en int.
				str.toCharArray();
				int[] ints = new int[str.length()];
				    for (int i=0; i < str.length(); i++) {
				    	ints[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
				 }
				 int numZ = ints[0];System.out.println("numZ = " + numZ);
				 int noRang = ints[1];System.out.println("noRang = " + noRang);
				 int noPlace = ints[2];System.out.println("noPlace = " + noPlace);

				// Date de la reservation à la seconde
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date aDate = new Date();
				System.out.println("" + dateFormat.format(aDate));
				System.out.println("INSERT INTO LesDossiers VALUES ('" + noDossier + "','" + prix + "')");
				System.out.println("INSERT INTO LesTickets VALUES ('" + numT 
						+ "', '" + numS 
						+ "', to_date('" + dateFormat.format(aDate) + "','DD/MM/YYYY HH24:MI:SS'), " 
						+ ", to_date('"	+ date + "','DD/MM/YYYY HH24'), '" 
						+ noDossier 
						+ "', '" + numZ 
						+ "', '" + noRang 
						+ "', '" + noPlace + "')");
				 //Execute the query
				int res1 = stmt.executeUpdate("INSERT INTO LesDossiers VALUES ('" + noDossier + "','" + prix + "')");
				int res2 = stmt.executeUpdate("INSERT INTO LesTickets VALUES ('" + numT 
						+ "', '" + numS 
						+ "', to_date('" + dateFormat.format(aDate) + "','DD/MM/YYYY HH24:MI:SS')" 
						+ ", to_date('"	+ date + "','DD/MM/YYYY HH24'), '" 
						+ noDossier 
						+ "', '" + numZ 
						+ "', '" + noRang 
						+ "', '" + noPlace + "')");

				System.out.println("Reservation éffectuée, "+res1+res2 +"lignes modifiées.");

				stmt.close();

			}else{
				System.out.println("Plus de places disponibles");
			}
		}else{
			System.out.println("Il doit rester 70 places au guichet dsl..");
		}
	      	
	      
	      	
	   }	
}
