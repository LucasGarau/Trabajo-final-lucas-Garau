CREATE TABLE `placasip` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Computerip` varchar(255) DEFAULT NULL,
  `Placaid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `placastopic` (
  `IDPlaca` varchar(255) NOT NULL,
  `Topic` varchar(255) DEFAULT NULL,
  `Luz` tinyint(1) DEFAULT NULL,
  `Presencia` tinyint(1) DEFAULT NULL,
  `Temperatura` tinyint(1) DEFAULT NULL,
  `Humedad` tinyint(1) DEFAULT NULL,
  `Dispositivos` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDPlaca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
