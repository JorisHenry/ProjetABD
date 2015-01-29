/*
	Procedures stockés pour les Triggers correspondent à l'action de prendre un vélos
**************************************************************************************************************************************************
*/

-- Procedure pour verifier la validité de l'abonnement du client
 
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

create or replace procedure verifierReservationsEncours (pidclient in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from client natural join reserver
	where idc = pidclient and dateR in (select dateloc from client natural join louer);
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de client dont la periode de reservation est la même que celle de location'); 
	
   END;
/

-- Procedure pour la verification des amendées non régularisées

create or replace procedure verifierAmandes(pidclient, valeur_retour integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from sanction natural join amande
	where idc = pidclient and count(numA) = 2 or to_number(to_char(SYSDATE,'DD'))-to_number(to_char(dateA,'DD')) >=28;
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('tous les clients ont au plus une amandée non régularisée ou des amandes de moins d un mois'); 
   END;
/

/*
--Procedure pour la verification des remise pour un client donné

create or replace verifierRemise(pidclient, valeur_retour integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from clientAbo 
	where idc = pidclient and remise = 'ok';
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de clients abonné qui ont une remise'); 
   END;
/

*/

/*
-- Procedure pour verifier si le code secret attribuer au client  non abonné (pour utilise sa remise) ne deppase pas un mois

create or replace verifierCodeSecret (pidclient in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from client natural join clientNabo natural join obtient
	where idc = pidclient and DATEDIFF(month,dateRem,SYSDATE) > 1 
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas de clients non abonné qui ont une remise superieur d un mois'); 
	
   END;
/

/*
***********************************************************************************************************************************************
*/


/*
	Procedures stockés pour les Triggers correspondent à l'action rendre un vélos
**************************************************************************************************************************************************
*/


-- Procedure pour verifier la durée de la location.

create or replace procedure verifier_dureeLoc(pidclient in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from client natural join louer
	where idc = pidclient and to_number(to_char(SYSDATE,'DD'))-to_number(to_char(dateloc,'DD'))> 1/2; 
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('pas des location dont la durée a depasse 12 heures');
   END;
/

-- procedure pour verifier que le velos que le client veut rendre est bien le même que celui qu’il a emprunté.

create or replace procedure verifier_velo(pidvelo in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from louer natural join acceuil
	where idv = pidvelo;
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('il n y a pas un client qui a louer le velo numero'||pidvelo);
   END;
/

-- procedure pour verifier qu'il y a une place disponible dans la station (pour qu'un client puisse rendre le vélo).

create or replace procedure verifier_placeDispo(pidStation in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from station natural join possede natural join bornettes
	where ids = pidStation and etat = 'libre';
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('il y a au moins une place disponible dans cette station');
   END;
/

-- Procedure pour verifier que Lorsqu’un véhicule veut déposer un vélo en station son état n’est pas 'HS'

create or replace procedure verif_etat_velo(pidvelo in integer, valeur_retour out integer) as 

   BEGIN	
	valeur_retour :=0;
	select count(*) into valeur_retour
	from velos natural join acceuil
	where idv = pidvelo and etat = 'HS';
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('il n y a pas des velos dont l etat est HS');
   END;
/

-- Procedure pour verifier que Lorsqu’un véhicule prend un vélo en station, on vérifie qu’il n’est pas au max de sa capacité

create or replace procedure verif_capac_veh(pidvehicule in integer, valeur_retour out integer) as 

   BEGIN
	valeur_retour :=0;
	select count(*) into valeur_retour
	from vehicule natural join deplace
	where idvehicule = pidvehicule and capaciteV = count(idv);
   EXCEPTION
   	when NO_DATA_FOUND then
		dbms_output.put_line('capacité maximale de vehicule numero'||pidvehicule||'n'est pas encore atteint);
   END;
/