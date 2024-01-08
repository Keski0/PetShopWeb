use petshop_web;

CREATE TABLE `utente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cellulare` varchar(10) NOT NULL,
  `data_nascita` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `ruolo` enum('G','M') NOT NULL DEFAULT 'G',
  `stato` enum('A','D') NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
)
