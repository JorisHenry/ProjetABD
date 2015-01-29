package client;

import java.sql.Connection;
import java.sql.*;

public class StationVplusVmoins {

	public static void afficherStations(Connection conn, String etat) throws SQLException{

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT adresse, capMax, nbVelo FROM stations NATURAL JOIN valeur WHERE etat ='" + etat + "'");
		ResultSetMetaData rsm = rs.getMetaData();

		System.out.println("\n**********************************");

		for(int i = 1; i <= rsm.getColumnCount(); i++)
			System.out.print("\t" + rsm.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************");

		while(rs.next()){         
			for(int i = 1; i <= rsm.getColumnCount(); i++)
				System.out.print("\t" + rs.getObject(i).toString() + "\t |");

			System.out.println("\n---------------------------------");

		}
		rs.close();
		stmt.close();
	}


}
