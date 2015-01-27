package fonctionnement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * Classe donnant un exemple d'utilisation de JDBC pour l'execution d'une
 * requï¿½te SQL, avec utilisation d'un fichier de configuration et affichage de
 * messages d'erreurs SQL.
 * 
 * @version 1
 * @author <a href="mailto:Sara.Bouchenak@imag.fr">Sara Bouchenak</a>
 * @version 1.0, 29/01/2013
 */
class ExecutionRequetes {

	private static final String configurationFile = "BD.properties";

	public static void main(String args[]) {
		try {
			String jdbcDriver, dbUrl, username, password;

			DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
			jdbcDriver = dap.getJdbcDriver();
			dbUrl = dap.getDatabaseUrl();
			username = dap.getUsername();
			password = dap.getPassword();

			// Load the database driver
			Class.forName(jdbcDriver);

			// Get a connection to the database
			Connection conn = DriverManager.getConnection(dbUrl, username, password);

			// Print information about connection warnings
			SQLWarningsExceptions.printWarnings(conn);
			
			// LES SPECTACLES ===============================================================================================
		//	 LesSpectacles.afficheNumerosEtRepresentations(conn, "Cats"); //Nom valide
			// LesSpectacles.afficheNumerosEtRepresentations(conn, "Pelo"); //Nom non valide
//			 LesSpectacles.afficheNomEtRepresentations(conn , 104); //Numéro valide
//			 LesSpectacles.afficheNomEtRepresentations(conn , 666); //Numéro non valide
//			 LesSpectacles.afficheNomSpectacle(conn, 104); //Numéro valide
//			 LesSpectacles.afficheNomSpectacle(conn, 272); //Numéro non valide
//			 LesSpectacles.afficheNumeroSpectacle(conn, "Manu Chao"); //Nom valide
//			 LesSpectacles.afficheNumeroSpectacle(conn, "Improbable"); //Nom non valide
//			 LesSpectacles.afficherSpectacles(conn); 

			// LES REPRESENTATIONS ===============================================================================================
//			 LesRepresentations.afficheRepresentations(conn); 
//			 LesRepresentations.afficheRepresentationsDesSpectacles(conn); 
			// LesRepresentations.afficheNumeroEtNom(conn, "06/11/2008 20"); //Date valide
			// LesRepresentations.afficheNumeroEtNom(conn, "06/11/2008 18"); //Date non valide
//			 LesRepresentations.affichePlacesDispo(conn, "06/11/2008 20"); //Date valide
//			 LesRepresentations.affichePlacesDispo(conn, "09/11/2008 18"); //Date non valide
			 //LesRepresentations.affichePlacesVendues(conn, "17/11/2014 20"); //Date valide
			// LesRepresentations.affichePlacesVendues(conn, "06/11/2008 19"); //Date non valide

			// LES ZONES ===============================================================================================
			// LesZones.afficheCategoriePlacesZone(conn, 3); //Zone valide
			// LesZones.afficheCategoriePlacesZone(conn, 88); //Zone non valide
			// LesZones.afficheInfosZone(conn, 6); //Zone valide
			// LesZones.afficheInfosZone(conn, 99); //Zone non valide

			// LES TICKETS ===============================================================================================
			// LesTickets.afficheInfosTicket(conn, 3); //Ticket valide
			// LesTickets.afficheInfosTicket(conn, 10837); //Ticket valide
			

			// LES DOSSIERS ===============================================================================================
			//LesDossiers.afficheInfosDossier(conn, 5); //Dossier existant
			// LesDossiers.afficheInfosDossier(conn, 1653); //Dossier inexistant
			// LesDossiers.affichePrixDossier(conn, 3); //Dossier existant
			// LesDossiers.affichePrixDossier(conn, 1563); //Dossier inexistant

			// LES CATEGORIES ===============================================================================================
			// LesCategories.afficheNumerosZones(conn, "Balcon"); //Catégorie valide
			// LesCategories.afficheNumerosZones(conn, "Balco"); //Catégorie valide
			// LesCategories.affichePrixPlaceZone(conn, "Balcon"); //Catégorie valide
			// LesCategories.affichePrixPlaceZone(conn, "Balco"); //Catégorie non valide
			
			//RESERVATIONS ===============================================================================================	    
			// Reservations.AfficherReservations(conn);//OK
			// Reservations.annulerReservation(conn, date, numZ, noPlace, noRang);
			// Reservations.ReserveUnePlace(conn, categorie, date); //PRBLM insert 2
			
			//SUPPLEMENT ===================================================================================================
			//Supplement.ajouteRepresentation(conn, "Cats", date);
			//Supplement.ajouteSpectacle(conn, "Le Grand Bleu");
//			Supplement.comptePlacesDispoCategorie(conn, "Balcon", "06/11/2008 20"); //Date valide
//			Supplement.comptePlacesDispoCategorie(conn, "Balcon", "06/11/2008 19"); //Date non valide
//			Supplement.compteTotalPlacesDispo(conn, "06/11/2008 20"); // Date valide
//			Supplement.compteTotalPlacesDispo(conn, "06/11/2008 10"); // Date non valide
//			Supplement.renvoiNumeroSpectacle(conn, "06/11/2008 20"); // Date Valide
//			Supplement.renvoiNumeroSpectacle(conn, "06/11/2008 10"); // Date non Valide
			
			// Close the result set, statement and the connection
			conn.close();
			
		} catch (SQLException se) {

			// Print information about SQL exceptions
			SQLWarningsExceptions.printExceptions(se);

			return;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}
}
