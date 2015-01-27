/************************************************************/
/*****************SUPRESSION DES TABLES**********************/
/************************************************************/

drop table possede;
drop table feedback;
drop table defini;
drop table accueil;
drop table deplacer;
drop table louer ;
drop table reserver ;
drop table resaPeriode ;
drop table resaJourRepete ;
drop table resaJour ;
drop table reservations ;
drop table routines ;
drop table ordres ;
drop table vehicules ;
drop table amendes ;
drop table remise ;
drop table clientNabo ;
drop table clientAbo ;
drop table clients ;
drop table bornettes ;
drop table valeur ;
drop table stations ;
drop table velos ;

/************************************************************/
/******************CREATION DES TABLES***********************/
/************************************************************/

create table velos(IdV number (4) not null, 
	modele varchar2 (16) not null,
	dateMS date not null,
	etat varchar2 (2) not null,
	constraint vel_pk1 primary key(IdV),
	constraint vel_chk1 check (etat in ('OK','HS'))
);
create table stations(IdS number (4) not null,
	adresse varchar2 (50) not null,
	capMax number (4) not null, 
	nbVelo number (4) not null,
	constraint sta_pk1 primary key (IdS)
);
create table valeur (IdV number (4) not null,
	IdS number (4) not null,
	etat varchar2 (6) not null,
	dateDeb date not null, 
	dateFin date,
	constraint val_pk1 primary key (IdV),
	constraint val_fk1 foreign key (IdS) references stations (IdS),
	constraint val_chk1 check (etat in ('Vplus','Vmoins','Vnul'))
);	
create table bornettes(IdB number (5) not null,
	IdVel number (4) not null,
	IdS number (4) not null,
	etat varchar2 (6) not null,
	constraint bor_pk1 primary key (IdB),
	constraint bor_fk1 foreign key (IdS) references stations (IdS),
	constraint bor_fk2 foreign key (IdVel) references velos (IdV),
	constraint bor_chk1 check (etat in ('Libre','Occupe','HS'))
);
create table clients(IdC number (4) not null,
	Cb number (16) not null,
	code number (8) not null,
	constraint cli_pk1 primary key(IdC)
);
create table clientAbo(IdC number (4) not null,
	nom varchar2 (16) not null,
	prenom varchar2 (16) not null,
	dateNais date not null,
	adr varchar2 (50) not null,
	remise varchar2 (5) not null,
	dateAbo date not null,
	constraint cab_pk1 primary key (IdC),
	constraint cab_fk1 foreign key (IdC) references clients (IdC),
	constraint cab_chk1 check (remise in ('true','false'))
);
create table clientNabo(IdC number (4) not null,
	constraint cna_fk1 foreign key(IdC) references clients(IdC)
);
create table remise(IdR number (4) not null,
	code number (8) not null,
	dateDeb date not null,
	dateFin date,
	constraint rem_pk1 primary key (IdR)
);
create table amendes(IdA number (4) not null,
	IdC number (4) not null,
	dateAm date not null,
	statut varchar2(6) not null,
	constraint am_pk1 primary key (IdA),
	constraint am_fk1 foreign key (IdC) references clientAbo(IdC),
	constraint am_chk1 check (statut in ('Paye','Impaye'))
);
create table vehicules(IdV number (4) not null,
	modele varchar2 (16) not null,
	dateMx date not null,
	capacite number (2) not null,
	constraint veh_pk2 primary key (IdV)
);
create table ordres(IdO number (4) not null, 
	defi varchar2(140) not null, 
	numOrd number (4) not null,
	constraint ord_pk1 primary key (IdO)
);
create table routines(IdR number (4) not null,
	IdVeh number (4) not null, 
	dateR date not null,
	constraint rou_pk1 primary key (IdR),
	constraint rou_fk1 foreign key (IdVeh) references vehicules (IdV)
);
create table reservations(IdR number (4) not null,
	dateR date not null,
	constraint res_pk1 primary key (IdR)
);
create table resaJour (IdR number (4) not null,
	dateRes date not null,
	constraint res_fk1 foreign key (IdR) references reservations (IdR)
);
create table resaJourRepete (IdR number (4) not null, 
	dateJour date not null, 
	dateDeb date not null, 
	dateFin date,
	constraint rjr_fk1 foreign key (IdR) references reservations (IdR)
);
create table resaPeriode (IdR number (4) not null,
	dateDeb date not null, 
	dateFin date not null,
	constraint rp_fk1 foreign key (IdR) references reservations (IdR)
);
create table reserver (IdR number(4) not null,
	IdC number(4) not null,
	IdS number (4) not null,
	constraint rsv_pk1 primary key (IdR, IdC, IdS),
	constraint rsv_fk1 foreign key (IdR) references reservations (IdR),
	constraint rsv_fk2 foreign key (IdC) references clients (IdC),
	constraint rsv_fk3 foreign key (IdS) references stations (IdS)
);
create table louer(IdC number (4) not null,
	IdV number (4) not null,
	dateLoc date not null,
	constraint lou_pk1 primary key (IdC, IdV, dateLoc),
	constraint lou_fk1 foreign key (IdC) references clients (IdC),
	constraint lou_fk2 foreign key (IdV) references velos (IdV)
);
create table deplacer(IdVeh number (4)not null,
	IdVel number (4) not null,
	constraint dep_pk1 primary key (IdVeh, IdVel),
	constraint dep_fk1 foreign key (IdVel) references velos (IdV),
	constraint dep_fk2 foreign key (IdVeh) references vehicules (IdV)
);
create table accueil(IdV number (4) not null,
	IdB number (4) not null,
	constraint acc_pk1 primary key (IdV, IdB),
	constraint acc_fk1 foreign key (IdV) references velos (IdV),
	constraint acc_fk2 foreign key (IdB) references bornettes (IdB)
);
create table defini(IdR number (4) not null,
	IdO number (4) not null,
	constraint def_pk1 primary key (IdR, IdO),
	constraint def_fk1 foreign key (IdR) references routines (IdR),
	constraint def_fk2 foreign key (IdO) references ordres (IdO)
);
create table feedback (IdO number (4) not null,
	IdVeh number (4) not null,
	etat varchar2 (6) not null,
	notification varchar (140),
	constraint fee_pk1 primary key (IdO, IdVeh),
	constraint fee_fk1 foreign key (Ido) references ordres (IdO),
	constraint fee_fk2 foreign key (IdVeh) references vehicules (IdV),
	constraint fee_chk1 check (etat in ('Valide','Annule'))
);
create table possede(IdS number (4) not null,
	IdB number (4) not null,
	constraint pos_pk1 primary key (IdS, IdB),
	constraint pos_fk1 foreign key (IdS) references stations (IdS),
	constraint pos_fk2 foreign key (IdB) references bornettes (IdB)
);

