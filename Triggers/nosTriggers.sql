	

--	I. Tous les triggers qui correspondent à l'action de prendre un velos par un client	--
  ----------------------------------------------------------------------------------------------

------		1.Lorsqu'un client veut louer un vélo on verifie:		

------			- si son abonnement a moins d’un an, dans le cas où c'est un client abonné.
			
		create or replace trigger louerV
			Before update or insert on louer
			for each row
			Declare
				valeurRetour integer;
			Begin
				verifierAbonnement(:new.idc,valeurRetour);
				if (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut louer un velo car l abonnement n est pas à jour');
				END IF;
			End;
			/

		create or replace trigger louerVeee
			Before update or insert on louer
			for each row
			Declare
				valeurRetour integer;
			Begin
				select count(*) into valeurRetour  
				from clientAbo
				where idc = :new.idc and (trunc(SYSDATE) - trunc(dateAbo)) >= 360 ;
				if (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut louer un velo car l abonnement n est pas à jour');
				END IF;
			End;
			/


------			- si il n’a pas déjà une réservation pour la même période.
			
			create or replace trigger louerVe
			Before update or insert on louer
			for each row
			Declare
				valeurRetour integer;
			Begin
				verifierReservationsEncours(:new.idc,valeurRetour);
				if (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut louer un velo car le client a une reservation en cour pour la même periode '); 
				END IF;
			End;
			/ 
/*
			
			create or replace trigger louerVe
			Before update or insert on louer
			for each row
			Declare
				valeurRetour integer;
			Begin
				select count(*) into valeurRetour
				from client natural join reserver
				where idc = :new.idc and dateR in (select dateloc from client natural join louer);
				if (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut louer un velo car le client a une reservation en cour pour la même periode '); 
				END IF;
			End;
			/
*/
------			- si il n’a pas 2 amendes non régularisées ou une amende de plus d’un mois

			create or replace trigger louerVel
			Before update on louer
			for each row
			Declare
				valeurRetour integer;
				pid_client integer;
			Begin
				verifierAmandes(:new.idc,valeurRetour);
				if (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut louer un velo car il y a 2 amandes non régularisées ou une amende de plus d 1 mois');
				END IF;
			End;
			/ 
/*
------			- si il ne bénéficie pas d’une remise pour un client abonné.
			
			create or replace trigger louerVelo
			Before update on station
			for each row
			Declare
				valeurRetour integer;
				pid_client integer;
			Begin
				select idc into pid_client
				from reserver natural join station 
				where ids = :new.ids;
				verifierRemise(pid_client,valeurRetour4);
				if((valeurRetour <> 0))THEN
		   			raise_application_error(-20400,'on  ');
				END IF;
			End;
			/ 
*/

/*	
------			- pour un client non abonné qui utilise un code secret (pour une remise), on vérifie s'il a moins d'un mois.

		create or replace trigger louerVelo
			Before update on station
			for each row
			Declare
				valeurRetour integer;
				valeurRetour2 integer;
				valeurRetour3 integer;
				valeurRetour4 integer;
				valeurRetour5 integer;
				pid_client integer;
			Begin
				select idc into pid_client 
				from reserver natural join station -- car même client pour la reservation et location (pour qu'on puisse verifier la bonne id)
				where ids = :new.ids;
				verifierAbonnement(pid_client,valeurRetour1);
				verifierReservationsEncours(pid_client,valeurRetour2);
				verifierAmandes(pid_client,valeurRetour3);
				verifierRemise(pid_client,valeurRetour4);
				verifierCodeSecret(pid_client,valeurRetour5); -- pour un client non abonné.
				if((valeurRetour1 <> 0)||(valeurRetour2 <> 0)||(valeurRetour3 <> 0)||(valeurRetour4<>0)||(valeurRetour<>0))THEN
		   			raise_application_error(-20400,'on ne peut louer un velo, conditions insatisfait!');
				END IF;
			End;
			/ 

*/

------		2.Lorsqu'un client veut réserve un vélo on verifie:

------			- si son abonnement a moins d’un an, dans le cas où c'est un client abonné.

		create or replace trigger reserverVel
			Before update or insert on reserver
			for each row
			Declare
				valeurRetour integer;
			Begin 
				verifierperiodeAbonement(:new.idc,valeurRetour);
				IF (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut pas reserver un velo car l abonnement n est pas à jour');
				END IF;
			End;
			/
				

------			- s'il n’a pas une réservation pour la même période, dans le cas où c'est un client abonné.


		create or replace trigger reserverVelo
			Before update or insert on reserver
			for each row
			Declare
				valeurRetour integer;
			Begin
				verifierperiodeAbonement(:new.idc,valeurRetour);
				IF (valeurRetour <> 0) THEN
		   			raise_application_error(-20400,'on ne peut reserver un velo car il y a un autre reservation pour la même periode');
				END IF;
			End;
			/

--	II.	Tous les triggers qui correspondent à l'action de Rendre un vélo --
  	----------------------------------------------------------------------------------------
		

------		1. Lorsqu' un client rend le vélo on vérifie:

------			- la durée de location (Normalement faut pas que ça depasse 12 heures).

		create or replace trigger rendreVelo
		before update on station 
		Declare 
			valeurRetour integer;
			Begin
				select idc into pid_client -- client qui veut rendre le vélo.
				from rendre natural join station
				where ids = :new.ids;
			
				select ids into pid_station -- station à la quelle on va rendre le vélo.
				from rendre natural join station 
				where ids = :new.ids;
			
				verifier_dureeLoc(pid_client,valeurRetour);
				IF (valeurRetour <> 0) THEN
		   			raise_application_error(-20380,'on ne peut pas rendre ce velo car ');
				END IF;
			END;
			/
*/

------			- que c’est bien le même vélo que celui qu’il a emprunté.

		create or replace trigger rendreVel
		before update or insert on acceuil 
		Declare 
			valeurRetour integer;
		Begin
			verifier_velo(new:idvelo,valeurRetour);
			IF (valeurRetour = 0) THEN
		   		raise_application_error(-20390,'on ne peut pas rendre ce velo car c est pas le même que celui emprunté');
			END IF;
		END;
		/

------			- qu'il y a de la place dans la station.


		create or replace trigger rendreVelo
		before update on station 
		Declare 
			valeurRetour integer;
		Begin
			verifier_placeDispo(:new.ids,valeurRetour);
			IF (valeurRetour <> 0) THEN
		   		raise_application_error(-20408,'on ne peut pas rendre ce velo parceque il y a pas assez de place dans la station');
			END IF;
		END;
		/

-------			Lorsqu’un véhicule veut déposer un vélo en station on vérifie que son état n’est pas 'HS'

		create or replace trigger deposer_velo
		before update or insert on acceuil 
		Declare 
			valeurRetour integer;
		Begin
			verif_etat_velo(:new.idv,valeurRetour);
			IF (valeurRetour <> 0) THEN
		   		raise_application_error(-20401,'on ne peut pas depose ce velo parceque son etat est HS');
			END IF;
		END;
		/

------- Lorsqu’un véhicule prend un vélo en station, on vérifie qu’il n’est pas au max de sa capacité

		create or replace trigger prendre_velo
		before insert on deplace 
		Declare 
			valeurRetour integer;
		Begin
			verif_capac_veh(:new.idvehicule,valeurRetour);
			IF (valeurRetour <> 0) THEN
		   		raise_application_error(-20402,'on ne peut pas prendre le velo numero' ||:new.idvelo ||' parceque la capacité de vehicule numero'|:new.idvehicule|'atteint sa capacité maximale');
			END IF;
		END;
		/
		
		
		--Trigger qui verifie que le numero d'un ticket de reduction n'existe pas deja

		create or replace trigger checkNumeroReduction
		before insert or update on remiseV
		FOR each ROW
		declare
			num number(2);
		begin
		select count(idRem) into num
		from remiseV
		where idRem = :new.idRem;	
		if(num > 0) then
			Raise_application_error(-2010, 'On ne peut attribuer un numero qui existe deja');
		end if;
end;
/