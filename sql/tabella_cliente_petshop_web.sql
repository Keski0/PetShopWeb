CREATE TABLE `petshop_web`.`cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cognome` VARCHAR(255) NOT NULL,
  `indirizzo` VARCHAR(255) NOT NULL,
  `citta` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idCliente`));
