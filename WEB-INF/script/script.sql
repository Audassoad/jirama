CREATE SEQUENCE numclient;
CREATE SEQUENCE prelevnum;
CREATE SEQUENCE numfacture;
CREATE SEQUENCE abonnementId;
CREATE SEQUENCE factureannule;
CREATE SEQUENCE prelevementannule;

CREATE TABLE Client(IdClient INTEGER PRIMARY KEY,Nom VARCHAR(20),Prenom VARCHAR(20),Adresse VARCHAR(30));

CREATE TABLE Tarif(NumTarif INTEGER PRIMARY KEY,Nom VARCHAR(20),Categorie VARCHAR(20),Quantite DECIMAL,PrixU DECIMAL);

CREATE TABLE Compteur(NumCompteur VARCHAR(10) PRIMARY KEY,Categorie VARCHAR(20),Tarif VARCHAR(20),IdClient INTEGER,FOREIGN KEY (IdClient) REFERENCES Client(IdClient));

CREATE TABLE Prelevement(NumPrelev INTEGER PRIMARY KEY,DatePrelev DATE,Mois VARCHAR(10),Annee VARCHAR(10),Valeur DECIMAL,NumCompteur VARCHAR(10),FOREIGN KEY (NumCompteur) REFERENCES Compteur(NumCompteur));

CREATE TABLE PrelevementFacture(NumPrelev INTEGER,IdClient INTEGER,FOREIGN KEY (NumPrelev) REFERENCES Prelevement(NumPrelev),FOREIGN KEY (IdClient) REFERENCES Client(IdClient));

CREATE TABLE Facture(IdFacture INTEGER PRIMARY KEY,IdClient INTEGER,DateFacture DATE,Montant DECIMAL,Categorie VARCHAR(20),FOREIGN KEY (IdClient) REFERENCES Client(IdClient));

CREATE TABLE FactureDetaille(NumPrelev INTEGER,IdFacture INTEGER,NumCompteur VARCHAR(10),Categorie VARCHAR(20),Consommation DECIMAL,FOREIGN KEY (NumPrelev) REFERENCES Prelevement(NumPrelev),FOREIGN KEY (IdFacture) REFERENCES Facture(IdFacture),FOREIGN KEY (NumCompteur) REFERENCES Compteur(NumCompteur));

CREATE TABLE OffrePrepaye(IdOffre VARCHAR(10) PRIMARY KEY,OffreConsom DECIMAL,Majoration DECIMAL(3,2),DUREE INTEGER,TypeOffre VARCHAR(20),Prix DECIMAL);

CREATE TABLE Abonnement(IdAbonnement VARCHAR(10) PRIMARY KEY,NumCompteur VARCHAR(10),IdOffre VARCHAR(10),Quantite DECIMAL,DateDebut DATE,DateFin DATE,FOREIGN KEY (NumCompteur) REFERENCES Compteur(NumCompteur),FOREIGN KEY (IdOffre) REFERENCES OffrePrepaye(IdOffre));

CREATE TABLE FactureAvoir(IdFactureAvoir VARCHAR(30) PRIMARY KEY,IdFacture INTEGER,MontantAvoir DECIMAL,DateFactureAvoir DATE,FOREIGN KEY (IdFacture) REFERENCES Facture(IdFacture));

CREATE TABLE PrelevAnnule(IdPrelevAnnule INTEGER PRIMARY KEY, NumPrelev INTEGER, DateAnnulation DATE, FOREIGN KEY (NumPrelev) REFERENCES Prelevement(NumPrelev));

INSERT INTO Client VALUES(numclient.nextval,'Rakoto','Emile','A3 Bis Itaosy');
INSERT INTO Client VALUES(numclient.nextval,'Rabe','Paul','III Ter Ambohipo');
INSERT INTO Client VALUES(numclient.nextval,'Razanany','Mino','Soarano');
INSERT INTO Client VALUES(numclient.nextval,'Vonjiniaina','Fano','D3 Antanimena');
INSERT INTO Client VALUES(numclient.nextval,'Ralay','Emmanuel','E4 Bis Tsiadana');

INSERT INTO Tarif VALUES(1,'1er tranche','Electricite',100,300);
INSERT INTO Tarif VALUES(2,'2e tranche','Electricite',125,450);
INSERT INTO Tarif VALUES(3,'3e tranche','Electricite',150,600);
INSERT INTO Tarif VALUES(4,'Plus','Electricite',-1,700);
INSERT INTO Tarif VALUES(5,'Eau','Eau',-1,100);

INSERT INTO Compteur VALUES('01248','Electricite','14',1);
INSERT INTO Compteur VALUES('74583','Eau','Particulier',1);
INSERT INTO Compteur VALUES('00457','Electricite','16',2);
INSERT INTO Compteur VALUES('41240','Eau','Particulier',2);
INSERT INTO Compteur VALUES('56010','Electricite','22',3);
INSERT INTO Compteur VALUES('13686','Eau','Societe',3);
INSERT INTO Compteur VALUES('65222','Electricite','16',4);
INSERT INTO Compteur VALUES('03147','Eau','Particulier',4);
INSERT INTO Compteur VALUES('33140','Electricite','14',5);
INSERT INTO Compteur VALUES('94010','Eau','Particulier',5);

INSERT INTO Prelevement VALUES(prelevnum.nextval,'14/11/2018','Novembre','2018',1254,'56010');
INSERT INTO Prelevement VALUES(prelevnum.nextval,'16/12/2018','Decembre','2018',1300,'56010');
INSERT INTO Prelevement VALUES(prelevnum.nextval,'12/10/2018','Octobre','2018',7500,'65222');
INSERT INTO Prelevement VALUES(prelevnum.nextval,'20/12/2018','Decembre','2018',410,'33140');
INSERT INTO Prelevement VALUES(prelevnum.nextval,'21/12/2018','Decembre','2018',65120,'13686');
INSERT INTO Prelevement VALUES(prelevnum.nextval,'16/11/2018','Novembre','2018',4121,'94010');


INSERT INTO OffrePrepaye VALUES('OFR1',20,1.5,30,'Electricite',5500);
INSERT INTO OffrePrepaye VALUES('OFR2',50,1.3,30,'Electricite',14000);
INSERT INTO OffrePrepaye VALUES('OFR3',100,1.2,60,'Electricite',27000);
INSERT INTO OffrePrepaye VALUES('OFR4',200,1.15,90,'Electricite',60000);

CREATE VIEW prelevNonFacture AS SELECT Prelevement.NumPrelev IdPrelev, Prelevement.DatePrelev DatePrelev, Prelevement.Mois MoisPrelev, Prelevement.Annee AnneePrelev, Compteur.IdClient Client FROM Prelevement, Compteur WHERE Prelevement.NumCompteur = Compteur.NumCompteur AND Prelevement.NumPrelev NOT IN (SELECT PrelevementFacture.NumPrelev FROM PrelevementFacture) AND Prelevement.NumPrelev NOT IN (SELECT PrelevAnnule.NumPrelev FROM PrelevAnnule);

CREATE VIEW compteurPrelev AS SELECT Prelevement.NumPrelev IdPrelev, Prelevement.DatePrelev DatePrelev, Prelevement.Mois MoisPrelev, Prelevement.Annee AnneePrelev, Compteur.IdClient Client, Compteur.Numcompteur Compteur FROM Prelevement, Compteur WHERE Prelevement.NumCompteur = Compteur.NumCompteur;

CREATE VIEW factureReel AS SELECT * FROM Facture WHERE IdFacture NOT IN (SELECT FactureAvoir.IdFacture FROM FactureAvoir);
CREATE VIEW FactureDetailleReel AS SELECT * FROM FactureDetaille WHERE IdFacture NOT IN (SELECT FactureAvoir.IdFacture FROM FactureAvoir);

DELETE FROM FactureDetaille;
DELETE FROM Facture;
DELETE FROM PrelevementFacture;
commit;

