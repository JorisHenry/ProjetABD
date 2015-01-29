package conducteur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe regroupant les requêtes effectuées par les conducteurs sur les vélos
 * @author Thomas
 *
 */
public class Velos {
	/**
	 * Permet de consulter les vélos presents dans un véhicule de régulation
	 * 
	 * @param conn
	 * @param IDVH l'id du véhicule
	 * @throws SQLException
	 */
	public static void consulterVeloVehicule(Connection conn, int IDVR) throws SQLException {
		// Get a statement from the connection
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT IdV" + "FROM deplace " + "WHERE IdVehicule = "
				+ IDVR);
		// Loop through the result set
		if (rs.next()) {
			System.out.println("Vélos présents dans le véhicule " + IDVR + " :" + "\n");
			do {
				System.out.println("ID Vélo : " + rs.getString("IdV"));
			} while (rs.next());
		} else {
			System.out.println("Aucun vélo dans le véhicule " + IDVR + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();

	}
	
	/**
	 * @throws SQLException
	 * @param conn
	 * @param IDV l'id du vélo
	 * 
	 */
	public static void declarerVeloEndommage(Connection conn, int IDV) throws SQLException {
		// Get a statement from the connection

		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		Statement stmt = conn.createStatement();
		int res1 = stmt.executeUpdate("UPDATE velos " + "SET etat='KO'" + "WHERE IdV = " + IDV);
		System.out.println("Le vélo, " + IDV + " est declaré endommagé, " + res1
				+ " lignes modifiées");
		stmt.close();

	}
}
