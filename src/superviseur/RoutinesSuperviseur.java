package superviseur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RoutinesSuperviseur {
	/**
	 * Etant donné un numéro de station montre les vélos qui y sont garés
	 * 
	 * @param conn : Connexion à la BDD
	 * @param IdStation : Identifiant de la station
	 * @throws SQLException : En cas d'erreur d'accès à la BDD
	 */
	public static void consulterVelosStation(Connection conn, int IdStation) throws SQLException {
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt
				.executeQuery("SELECT IdS, IdVelo "
						+ "FROM possede natural join accueil "
						+ "WHERE IdS = " + IdStation);

		// Loop through the result set
		if (rs.next()) {
			System.out.println("Informations sur les Velos de la station " + IdStation + " :"+"\n");
			do {	
				System.out.println("Numero du velo : "+ rs.getString("IdVelo")+ "\n");
			} while (rs.next());
		} else {
			System.out.println("Aucun velo n'est enregistré dans la station " + IdStation + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
	}
	
	/**
	 * Etant donné un jour, consulte les routines
	 * 
	 * @param conn : Connexion à la BDD
	 * @param Jour : Date données 
	 * @throws SQLException : En cas d'erreur d'acc�s � la BDD
	/*public static void ConsulterVelosStation(Connection conn, String Jour) throws SQLException {
		// Get a statement from the connection
		Statement stmt = conn.createStatement();
		
		// Date de la journée
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date aDate = new Date();
		System.out.println("" + dateFormat.format(aDate));

		// Execute the query
		ResultSet rs = stmt
				.executeQuery("SELECT IdS, IdVelo "
						+ "FROM possede natural join accueil "
						+ "WHERE  = " + IdStation);

		// Loop through the result set
		if (rs.next()) {
			System.out.println("Informations sur les Velos de la station " + IdStation + " :"+"\n");
			do {	
				System.out.println("Numero du velo : "+ rs.getString("IdVelo")+ "\n");
			} while (rs.next());
		} else {
			System.out.println("Aucun velo n'est enregistré dans la station " + IdStation + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
	}*/
	
	
	/**
	 * Ajouter un ordre à une routine
	 * 
	 * @param conn : Connexion à la BDD
	 * @param IdStation : Identifiant de la station
	 * @throws SQLException : En cas d'erreur d'accès à la BDD
	 */
	public static void ajouterOrdre(Connection conn, int idOrdre, String defOrdre, int numOrd) throws SQLException {
		// Get a statement from the connection
		Statement stmt = conn.createStatement();
		
		// Execute the query
				ResultSet rs = stmt
						.executeQuery("SELECT Id0, last(numOrd) as lastOrder "
								+ "FROM ordres "
								+ "WHERE IdO = " + idOrdre);

				// Loop through the result set

				if (rs.next()) {
					int last = rs.getInt("lastOrder");
					if (last == numOrd-1)
					System.out.println("Ajout de l'ordre dans la base?\n");
					do {
						// Add the row in ordres
						int res1 = stmt.executeUpdate("Insert into ordres values ('" + idOrdre 
								+ "', '" + defOrdre
								+ "', '" + numOrd + "')");
					}while(rs.next()); 
				} else {
					System.out.println("\n L'ordre n'est pas bien séquencé ");
				}
				// Close the result set, statement and the connection
				rs.close();

		stmt.close();
	}
}
