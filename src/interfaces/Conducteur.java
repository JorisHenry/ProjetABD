package interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import conducteur.Routines;
import fonctionnement.DatabaseAccessProperties;
import fonctionnement.SQLWarningsExceptions;

public class Conducteur {
	private static final String configurationFile = "BD.properties";

	public static void main(String[] args) {
		System.out
				.println("Bienvenue dans l'interface v�hicule, veuillez saisir l'action � �xecuter (exit pour quitter)");

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
			System.out.println("Connect�");

			// Print information about connection warnings
			SQLWarningsExceptions.printWarnings(conn);

			//Routines.consulterRoutine(conn, 2);
			Routines.effectuerRoutine(conn, 2, null);

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
