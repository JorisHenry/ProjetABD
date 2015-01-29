package conducteur;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe regroupant les requ�tes effectu�es par les conducteurs sur les
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
	 *            l'id du v�hicule
	 * @throws SQLException
	 */
	public static void consulterRoutine(Connection conn, int IDVR) throws SQLException {
		// Get a statement from the connection
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		Statement stmt = conn.createStatement();
		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT Ido, defi, numord, dateR"
				+ " FROM routines natural join defini natural join ordres" + " WHERE IdVehicule = "
				+ IDVR);

		// Loop through the result set
		if (rs.next()) {
			System.out.println("Ordres � executer par le v�hicule " + IDVR + " le "
					+ rs.getString("dateR") + " :" + "\n");
			do {
				System.out.println("ID Ordre : " + rs.getString("IdO") + "\n" + "Description : "
						+ rs.getString("defi") + "\n" + "Priorit� : " + rs.getString("numOrd")
						+ "\n");
			} while (rs.next());
		} else {
			System.out.println("Aucune routine pour le v�hicule " + IDVR + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
	}

	/**
	 * Permet au conducteur de faire sa routine
	 * 
	 * @param conn
	 * @param IDVR
	 *            L'id du v�hicule
	 * @throws SQLException
	 */
	public static void effectuerRoutine(Connection conn, int IDVR, Date date) throws SQLException {
		// Get a statement from the connection
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT IdO, defi, numord, dateR"
				+ " FROM routines natural join defini natural join ordres" + " WHERE IdVehicule = "
				+ IDVR + " ORDER BY numord;");
		
		
		// Loop through the result set
		if (rs.next()) {
			System.out.println("Ordre � executer par le v�hicule " + IDVR + " le "
					+ rs.getString("dateR") + " :" + "\n");
			do {
				System.out.println("ID Ordre : " + rs.getString("IdO") + "\n" + "Description : "
						+ rs.getString("defi") + "\n" + "Priorit� : " + rs.getString("numOrd")
						+ "\n");
				
				
				
				

			} while (rs.next());
		} else {
			System.out.println("Aucune routine pour le v�hicule " + IDVR + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
	}

}
