package fonctionnement;

public class CreateDatas {

	public static void main(String[] args) {
		int i =0;
		//vélos hommes
		for(i=0; i<20; i++){
			int annee = (int) (2014+(Math.random()*(2015-2014)));
			int mois = (int) (1+(Math.random()*(12-1)));
			int jour = (int) (1+(Math.random()*(28-1)));
			System.out.println("INSERT INTO velos VALUES ('"+i+"', 'homme', '"+annee+"-"+mois+"-"+jour+"', 'OK');");
		}
		//vélos femmes
		for(i=20; i<40; i++){
			int annee = (int) (2014+(Math.random()*(2015-2014)));
			int mois = (int) (1+(Math.random()*(12-1)));
			int jour = (int) (1+(Math.random()*(28-1)));
			System.out.println("INSERT INTO velos VALUES ('"+i+"', 'femme', '"+annee+"-"+mois+"-"+jour+"', 'OK');");
		}		
		//vélos enfants
		for(i=40; i<60; i++){
			int annee = (int) (2014+(Math.random()*(2015-2014)));
			int mois = (int) (1+(Math.random()*(12-1)));
			int jour = (int) (1+(Math.random()*(28-1)));
			System.out.println("INSERT INTO velos VALUES ('"+i+"', 'enfants', '"+annee+"-"+mois+"-"+jour+"', 'OK');");
		}
		//centre de maintenance
		System.out.println("INSERT INTO stations VALUES ('"+0+"', '50 rue De Gaulle', '20', '0');");
		//stations
		System.out.println("INSERT INTO stations VALUES ('"+1+"', '50 rue De Gaulle', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+2+"', '20 rue Berthelo', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+3+"', '14 avenue Foch', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+4+"', '2 Boulevard Lienard', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+5+"', '23 rue Guérinidoun', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+6+"', '3 avenue Tony Montana', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+7+"', '15 place Victoire', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+8+"', '11 rue des Aborigenes', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+9+"', '56 Boulevard des Olives Vertes ', '20', '0');");
		System.out.println("INSERT INTO stations VALUES ('"+10+"', 'Centre du Bois de Boulogne', '20', '0');");
		//		
		for(i=0; i<20; i++){
			int annee = (int) (2014+(Math.random()*(2015-2014)));
			int mois = (int) (1+(Math.random()*(12-1)));
			int jour = (int) (1+(Math.random()*(28-1)));
			System.out.println("INSERT INTO valeur VALUES ('"+i+"', '50 rue De Gaulle', '20', '0');");
		}

	}

}
