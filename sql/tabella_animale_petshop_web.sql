CREATE TABLE `animale` (
  `matricola` int NOT NULL,
  `nome_animale` varchar(255) NOT NULL,
  `data_acquisto` date DEFAULT NULL,
  `prezzo` float NOT NULL,
  `tipo_animale` varchar(255) NOT NULL,
  `idCliente` int DEFAULT NULL,
  PRIMARY KEY (`matricola`),
  KEY `idCliente_idx` (`idCliente`),
  CONSTRAINT `idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
  )
  