DROP TABLE Credits;
DROP TABLE Logs;
DROP TABLE Compte;
DROP TABLE Client;

CREATE TABLE Client
(
	idClient VARCHAR(10) CONSTRAINT PK_Client PRIMARY KEY,
	nom VARCHAR(50),
	prenom VARCHAR(50),
	login VARCHAR(50)
);

CREATE TABLE Compte
(
	idCompte VARCHAR(20) CONSTRAINT PK_Compte PRIMARY KEY,
	solde FLOAT,
	refClient VARCHAR(10) CONSTRAINT FK_Compte_refClient REFERENCES Client(idClient)
);

CREATE TABLE Logs
(
	idLogs VARCHAR(5) CONSTRAINT PK_Logs PRIMARY KEY,
	infos VARCHAR(50)
);

CREATE TABLE Credits
(
	idCredit VARCHAR(10) CONSTRAINT PK_Credits PRIMARY KEY,
	montant FLOAT,
	taux FLOAT,
	duree INTEGER,
	salaire FLOAT,
	chargeCredit FLOAT,
	accorde BOOLEAN,
	refClient VARCHAR(10) CONSTRAINT FK_Credits_refClient REFERENCES Client(idClient)
);
