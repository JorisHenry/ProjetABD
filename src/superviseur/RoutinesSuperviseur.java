package superviseur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RoutinesSuperviseur {
	/**
	 * Etant donn� un achat, affiche les informations concernant l�ensemble des places achet�es.
	 * 
	 * @param conn : Connexion � la BDD
	 * @param IdStation : Identifiant de la station
	 * @throws SQLException : En cas d'erreur d'acc�s � la BDD
	 */
	public static void ConsulterVelosStation(Connection conn, int IdStation) throws SQLException {
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
}
