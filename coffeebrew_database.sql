CREATE DATABASE `CoffeeBrew`;
USE `CoffeeBrew`;

CREATE TABLE `Utente` (
  `idUtente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(32) DEFAULT NULL,
  `cognome` varchar(32) DEFAULT NULL,
  `email` varchar(48) NOT NULL UNIQUE,
  `password` varchar(128) NOT NULL,
  `centesimiCredito` int DEFAULT NULL,
  PRIMARY KEY (`idUtente`)
)

CREATE TABLE `Tecnico` (
  `idTecnico` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(32) DEFAULT NULL,
  `cognome` varchar(32) DEFAULT NULL,
  `email` varchar(48) NOT NULL UNIQUE,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`idTecnico`)
)

CREATE TABLE `CartaDiCredito` (
    `idCarta` int NOT NULL AUTO_INCREMENT,
    `numeroCarta` varchar(16) NOT NULL,
    `nomeSullaCarta` varchar(32) NOT NULL,
    `dataScadenza` DATE NOT NULL,
    `idUtente` int NOT NULL,
    PRIMARY KEY (`idCarta`),
    FOREIGN KEY (`idUtente`) REFERENCES Utente(`idUtente`)
)

CREATE TABLE `DistributoreAutomatico` (
	`idDistributore` INT NOT NULL AUTO_INCREMENT,
	`locazione` VARCHAR(128) NOT NULL,
	`occupanteUtente` INT DEFAULT NULL,
	`occupanteTecnico` INT DEFAULT NULL,
	PRIMARY KEY (`idDistributore`),
	FOREIGN KEY (`occupanteUtente`) REFERENCES Utente(`idUtente`),
	FOREIGN KEY (`occupanteTecnico`) REFERENCES Tecnico(`idTecnico`)
)

CREATE TABLE `Amministratore` (
  `idAmministratore` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(32) DEFAULT NULL,
  `cognome` VARCHAR(32) DEFAULT NULL,
  `email` VARCHAR(48) NOT NULL UNIQUE,
  `password` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`idAmministratore`)
)

CREATE TABLE `Prodotto` (
  `idProdotto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(64) NOT NULL UNIQUE,
  `costo` INT NOT NULL,
  `qtaVendute` INT NOT NULL,
  PRIMARY KEY (`idProdotto`) 
)