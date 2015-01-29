package conducteur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe regroupant les requêtes effectuées par les conducteurs sur les
 * routines
 * 
 * @author Thomas
 *
 */
public class Routines {
	/**
	 * Permet au conducteur d'afficher les ordres de sa routine
	 * 
	 * @param conn
	 * @param IDVR
	 *            l'id du véhicule
	 * @throws SQLException
	 */
	public static void consulterRoutine(Connection conn, int IDVR) throws SQLException {
		// Get a statement from the connection
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		Statement stmt = conn.createStatement();
		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT IdO, defi, numOrd"
				+ "FROM routines natural join defini  " + "WHERE IdVeh = " + IDVR);

		// Loop through the result set
		if (rs.next()) {
			System.out.println("Ordres à executer par le véhicule " + IDVR + " :" + "\n");
			do {
				System.out.println("ID Ordre : " + rs.getString("IdO") + "\n" + "Description : "
						+ rs.getString("defi") + "\n" + "Priorité : " + rs.getString("numOrd")
						+ "\n");
			} while (rs.next());
		} else {
			System.out.println("Aucune routine pour le véhicule " + IDVR + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
	}

	/**
	 * @throws SQLException 
	 * 
	 * 
	 */
	public static void declarerVeloEndommage(Connection conn, int IDV) throws SQLException {
		// Get a statement from the connection
	
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			Statement stmt = conn.createStatement();
			int res1 = stmt.executeUpdate("UPDATE velos " + "SET etat='KO'" + "WHERE IdV = " + IDV);
			System.out.println("Le vélo, " + IDV + " est declaré endommagé, "+ res1+" lignes modifiées");
			stmt.close();
		

		

	}
}
