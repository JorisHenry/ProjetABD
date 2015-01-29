package conducteur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe regroupant les requ�tes effectu�es par les conducteurs sur les v�los
 * @author Thomas
 *
 */
public class Velos {
	/**
	 * Permet de consulter les v�los presents dans un v�hicule de r�gulation
	 * 
	 * @param conn
	 * @param IDVH l'id du v�hicule
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
			System.out.println("V�los pr�sents dans le v�hicule " + IDVR + " :" + "\n");
			do {
				System.out.println("ID V�lo : " + rs.getString("IdV"));
			} while (rs.next());
		} else {
			System.out.println("Aucun v�lo dans le v�hicule " + IDVR + ".");
		}
		// Close the result set, statement and the connection
		rs.close();
		stmt.close();

	}
	
	/**
	 * @throws SQLException
	 * @param conn
	 * @param IDV l'id du v�lo
	 * 
	 */
	public static void declarerVeloEndommage(Connection conn, int IDV) throws SQLException {
		// Get a statement from the connection

		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		Statement stmt = conn.createStatement();
		int res1 = stmt.executeUpdate("UPDATE velos " + "SET etat='KO'" + "WHERE IdV = " + IDV);
		System.out.println("Le v�lo, " + IDV + " est declar� endommag�, " + res1
				+ " lignes modifi�es");
		stmt.close();

	}
}
