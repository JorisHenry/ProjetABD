package Utiles;

import javax.rmi.CORBA.Util;


/**
 * Class de création des datas
 * @author Thomas
 *
 */
public class CreateDatas {
	/**
	 * Création des insetions dans la bd
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.viderfichier();
		int i = 0;
		// vélos hommes
		for (i = 1; i < 20; i++) {
			int annee = (int) (2014 + (Math.random() * (2015 - 2014)));
			String mois = Utils.randomMois();
			String jour = Utils.randomJour();
			Utils.ecrire("INSERT INTO velos VALUES ('" + i + "', 'homme', to_date('" + annee + "-" + mois + "-" + jour + "','YYYY-MM-DD'), 'OK');");
		}
		// vélos femmes
		for (i = 20; i < 40; i++) {
			int annee = (int) (2014 + (Math.random() * (2015 - 2014)));
			String mois = Utils.randomMois();
			String jour = Utils.randomJour();
			Utils.ecrire("INSERT INTO velos VALUES ('" + i + "', 'femme', to_date('" + annee + "-" + mois + "-" + jour + "','YYYY-MM-DD'), 'OK');");
		}
		// vélos enfants
		for (i = 40; i < 60; i++) {
			int annee = (int) (2014 + (Math.random() * (2015 - 2014)));
			String mois = Utils.randomMois();
			String jour = Utils.randomJour();
			Utils.ecrire("INSERT INTO velos VALUES ('" + i + "', 'enfants', to_date('" + annee + "-" + mois + "-" + jour + "','YYYY-MM-DD'), 'OK');");
		}
		// centre de maintenance
		Utils.ecrire("INSERT INTO stations VALUES ('" + 1 + "', '50 rue De Gaulle', '20', '0');");
		// stations
		Utils.ecrire("INSERT INTO stations VALUES ('" + 2 + "', '20 rue Berthelo', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 3 + "', '14 avenue Foch', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 4 + "', '2 Boulevard Lienard', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 5 + "', '23 rue Guérinidoun', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 6 + "', '3 avenue Tony Montana', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 7 + "', '15 place Victoire', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 8 + "', '11 rue des Aborigenes', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 9 + "', '56 Boulevard des Olives Vertes ', '20', '0');");
		Utils.ecrire("INSERT INTO stations VALUES ('" + 10 + "', 'Centre du Bois de Boulogne', '20', '0');");
		
		// valeurs
		for (i = 1; i < 30; i++) {
			int station = (int) (1 + (Math.random() * (10 - 1)));
			String etat;
			if (i % 3 == 0) {
				etat = "Vplus";
				Utils.ecrire("INSERT INTO valeur VALUES ('" + i + "', '" + station + "', '" + etat
						+ "', to_date('08:00:00','HH24:MI:SS'),to_date('11:00:00','HH24:MI:SS') );");
			} else if (i % 5 == 0) {
				etat = "Vmoins";
				Utils.ecrire("INSERT INTO valeur VALUES ('" + i + "', '" + station + "', '" + etat
						+ "', to_date('17:00:00','HH24:MI:SS'),to_date('19:00:00','HH24:MI:SS') );");
			} else {
				etat = "Vnul";
				Utils.ecrire("INSERT INTO valeur VALUES ('" + i + "', '" + station + "', '" + etat + "', null, null);");
			}
		}
		// bornettes
		for (i = 1; i < 30; i++) {
			int station = (int) (1 + (Math.random() * (10 - 1)));
			int velo = (int) (1 + (Math.random() * (59 - 1)));
			// Utils.ecrire("INSERT INTO bornettes VALUES ('"+i+"', '"+velo+"', '"+station+"', 'Libre' );");
			Utils.ecrire("INSERT INTO bornettes VALUES ('" + i + "', null, '" + station + "', 'Libre' );");
		}
		// clients
		for (i = 1; i <= 20; i++) {
			Utils.ecrire("INSERT INTO clients VALUES ('"+i+"','"+ Utils.randomCB()+"', '"+Utils.randomCode()+"' );");
		}
		// clients abo
		for (i = 1; i < 10; i++) {
			Utils.ecrire("INSERT INTO clientAbo VALUES ('"+i+"', 'Emile', 'Kigali', to_date('06-NOV-08', 'YYYY-MM-DD'), 'Rue du campus', 'non', to_date('06-NOV-09', 'YYYY-MM-DD'));");
		}
		// clients non abo
		for (i = 10; i <= 20; i++) {
			Utils.ecrire("INSERT INTO clientNAbo VALUES ('"+i+"');");
		}
		
		// véhicules
		for (i = 1; i <= 10; i++) {
			Utils.ecrire("INSERT INTO vehicules VALUES ('"+i+"', 'Citroen', to_date('06-NOV-08', 'YYYY-MM-DD'), '10');");
		}
		// reservations

		Utils.ecrire("INSERT INTO reservations values ('1');");
		Utils.ecrire("INSERT INTO reservations values ('2');");
		Utils.ecrire("INSERT INTO reservations values ('3');");
		Utils.ecrire("INSERT INTO reservations values ('4');");

		// reserver

		Utils.ecrire("INSERT INTO reserver values ('2','3','8',to_date('2014,NOV,10','YYYY,MM,DD'));");
		
		
		// Routines
		Utils.ecrire("INSERT INTO routines values ('1', '1', NOW());");
		Utils.ecrire("INSERT INTO routines values ('2', '2', NOW());");
		
		//Ordres
		Utils.ecrire("INSERT INTO ordres values ('1', 'Aller station 4', '1');");
		Utils.ecrire("INSERT INTO ordres values ('2', 'Deplacer 4 vélos', '2');");
		Utils.ecrire("INSERT INTO ordres values ('3', 'Aller au centre de maintenance', '3');");
		Utils.ecrire("INSERT INTO ordres values ('4', 'Aller station 4', '4');");
		
		// Defini
		Utils.ecrire("INSERT INTO defini values ('1', '1');");
		Utils.ecrire("INSERT INTO defini values ('1', '2');");
		Utils.ecrire("INSERT INTO defini values ('2', '3');");
		Utils.ecrire("INSERT INTO defini values ('2', '4');");
	
	}
}
