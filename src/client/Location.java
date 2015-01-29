package client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Requ�tes en rapport avec la location d'un v�lo
 * @author Thomas
 *
 */
public class Location {
	/**
	 * Abonne le client au service et ins�rant dans la table client abonn� et supprimant de client non abonn� si besoin
	 * @param conn
	 */
	public static void abonnement(Connection conn){
		// Get a statement from the connection
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				Statement stmt = conn.createStatement();
				// Execute the query
				ResultSet rs = stmt.executeQuery("");

				// Loop through the result set
				if (rs.next()) {
					System.out.println("Ordres � executer par le v�hicule " + IDVR + " :" + "\n");
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
	
	/**
	 * Permet au client de louer un v�lo, abonn� ou non abonn�
	 * @param conn
	 */
	public static void louer(Connection conn){
		
	}
	
	
	
}
