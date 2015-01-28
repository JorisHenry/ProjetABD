package fonctionnement;

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
		for (i = 0; i < 20; i++) {
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
		Utils.ecrire("INSERT INTO stations VALUES ('" + 0 + "', '50 rue De Gaulle', '20', '0');");
		// stations
		Utils.ecrire("INSERT INTO stations VALUES ('" + 1 + "', '50 rue De Gaulle', '20', '0');");
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
		for (i = 0; i < 30; i++) {
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
		for (i = 0; i < 30; i++) {
			int station = (int) (1 + (Math.random() * (10 - 1)));
			int velo = (int) (1 + (Math.random() * (59 - 1)));
			// Utils.ecrire("INSERT INTO bornettes VALUES ('"+i+"', '"+velo+"', '"+station+"', 'Libre' );");
			Utils.ecrire("INSERT INTO bornettes VALUES ('" + i + "', null, '" + station + "', 'Libre' );");
		}
		// clients
		for (i = 1; i <= 20; i++) {
			Utils.ecrire("INSERT INTO clients VALUES ('"+i+"','"+ Utils.randomCB()+"'"+Utils.randomCode()+"' );");
		}
		// clients abo
		for (i = 1; i <= 10; i++) {
			Utils.ecrire("INSERT INTO clientAbo VALUES ('"+i+"', 'Emile', 'Kigali', '06-NOV-08', 'Rue du campus', 'non', '06-NOV-09');");
		}
		// clients non abo
		
		
	
	}
}
