/*
	Procedures stockés pour les Triggers correspondent à l'action de prendre un vélos
**************************************************************************************************************************************************
*/

-- Procedure pour verifier la validité de l'abonnement du client
 
-- OK
Create or replace procedure verifierAbonnement (pidclient in integer, valeur_retour out integer) as
   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from clientAbo natural join louer
	where idc = pidclient and to_number(to_char(SYSDATE,'DD')) - to_number(to_char(dateAbo,'DD'))>=360; 
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de client dont l abonnement n est pas à jour');
   END;
/


-- Procedure pour verifier si la periode de la reservation est en même temps que celle de la location

-- OK
create or replace procedure verifierReservationsEncours (pidclient in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from clients natural join reserver
	where IdC = pidclient and dateR in (select dateLoc from clients natural join louer);
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de client dont la periode de reservation est la même que celle de location'); 
	
   END;
/

-- Procedure pour la verification des amendées non régularisées

create or replace procedure verifierAmendes(pidclient, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from amendes
	where IdC = pidclient and count(IdA) = 2 or to_number(to_char(SYSDATE,'DD'))-to_number(to_char(dateAm,'DD')) >=28;
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('tous les clients ont au plus une amende non regularisee ou des amendes de moins d un mois'); 
   END;
/

/*
--Procedure pour la verification des remise pour un client donné

-- PAS OK
create or replace verifierRemise(pidclient, valeur_retour integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from clientAbo 
	where idc = pidclient and remise = 'true';
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de clients abonné qui ont une remise'); 
   END;
/



/*
-- Procedure pour verifier si le code secret attribuer au client  non abonné (pour utilise sa remise) ne deppase pas un mois

-- PAS OK
create or replace verifierCodeSecret (pidclient in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from clients natural join clientNabo
	where IdC = pidclient and DATEDIFF(month,dateRem,SYSDATE) > 1 
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de clients non abonné qui ont une remise superieur d un mois'); 
	
   END;
/
*/


/*
	Procedures stockés pour les Triggers correspondent à l'action rendre un vélos
**************************************************************************************************************************************************
*/


-- Procedure pour verifier la durée de la location.

-- OK
create or replace procedure verifier_dureeLoc(pidclient in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from clients natural join louer
	where IdC = pidclient and to_number(to_char(SYSDATE,'DD'))-to_number(to_char(dateLoc,'DD'))> 1/2; 
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas des location dont la duree a depasse 12 heures');
   END;
/

-- procedure pour verifier que le velos que le client veut rendre est bien le même que celui qu’il a emprunté.

-- OK
create or replace procedure verifier_velo(pidvelo in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from louer natural join accueil
	where IdVelo = pidvelo;
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('il n y a pas un client qui a louer le velo numero'||pidvelo);
   END;
/

-- procedure pour verifier qu'il y a une place disponible dans la station (pour qu'un client puisse rendre le vélo).

-- OK
create or replace procedure verifier_placeDispo(pidStation in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from stations natural join possede natural join bornettes
	where IdS = pidStation and etat = 'Libre';
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('il y a au moins une place disponible dans cette station');
   END;
/

-- Procedure pour verifier que Lorsqu’un véhicule veut déposer un vélo en station son état n’est pas 'HS'

-- OK
create or replace procedure verif_etat_velo(pidvelo in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from velos natural join accueil
	where IdVelo = pidvelo and etat = 'HS';
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('il n y a pas des velos dont l etat est HS');
   END;
/

-- Procedure pour verifier que Lorsqu’un véhicule prend un vélo en station, on vérifie qu’il n’est pas au max de sa capacité

-- PAS OK
create or replace procedure verif_capac_veh(pidvehicule in integer, valeur_retour out integer) as 

   BEGIN
	valeur_retour :=0;
	select count(*) into valeur_retour
	from vehicules natural join deplace
	where IdVehicule = pidvehicule and capacite = count(IdVelo);
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('capacité maximale de vehicule numero'||pidvehicule||'n est pas encore atteint');
   END;
/
