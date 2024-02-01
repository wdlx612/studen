CREATE DATABASE IF NOT EXISTS `dbSchool` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `dbSchool`;

CREATE TABLE IF NOT EXISTS `Students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Students` (`id`, `name`, `surname`) VALUES
(1, 'Bernal', 'Deep'),
(4, 'Lover', 'Craft'),
(5, 'Michael', 'Mounstain'),
(6, 'Dave', 'Mounstain'),
(7, 'Clance', 'Brown'),
(9, 'Arnold', 'Strong');

