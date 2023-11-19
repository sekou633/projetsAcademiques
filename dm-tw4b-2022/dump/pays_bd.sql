-- phpMyAdmin SQL Dump
-- version 5.0.4deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql.info.unicaen.fr:3306
-- Généré le : lun. 28 nov. 2022 à 23:32
-- Version du serveur :  10.5.11-MariaDB-1
-- Version de PHP : 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `doumbou211_bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
  `nom_p` varchar(255) DEFAULT NULL,
  `capitale_p` varchar(255) DEFAULT NULL,
  `continent_p` varchar(255) DEFAULT NULL,
  `langue_p` varchar(255) DEFAULT NULL,
  `superficie_p` int(11) DEFAULT NULL,
  `population_p` int(11) NOT NULL,
  `monnaie_p` varchar(15) NOT NULL,
  `image_p` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`id`, `nom_p`, `capitale_p`, `continent_p`, `langue_p`, `superficie_p`, `population_p`, `monnaie_p`, `image_p`) VALUES
(17, 'France', 'Paris', 'Europe', 'Français', 551695, 67000000, 'Euro (£)', '8c63976153f2d25f1612de59e3549d00.png'),
(18, 'Guinée', 'Conakry', 'Afrique', 'Français', 245857, 12000, 'Francs Guinéens', 'a17acb0954fce6e742589db11dfd2b80.png'),
(19, 'Royaume-Uni', 'Londres', 'Europe', 'Anglais', 153137, 8982000, 'Euro (£)', '66178f2ff50dc68a5704706d306f1f35.png'),
(20, 'Canada', 'Ottawa', 'Amerique ', 'Français et Anglais', 10000000, 38000000, 'Dollars ($)', 'ad2ea4ebb21537d823b7a869ced57e06.png'),
(21, 'Italie', 'Rome', 'Europe', 'Italien', 300000, 59000000, 'Euro (£)', 'b27bb5bc9ec38fa3b5add4b7a0b85700.png'),
(22, 'Allemagne', 'Berlin', 'Europe', 'Allemand', 357588, 83000000, 'Euro (£)', 'ebfbe4ee41061dedb7fa293e44e5c517.png'),
(23, 'Espagne', 'Madrid', 'Europe', 'Espagnol', 505990, 47000000, 'Euro (£)', '7ab2f5f632486de90e31d532fe0f4c45.png'),
(24, 'Belgique', 'Bruxelles', 'Europe', 'Français Allemand Nerlandais', 30688, 12000000, 'Euro (£)', '9bb196319dc20dc03239531d1b088175.png'),
(25, 'Maroc', 'Rabat', 'Afrique', 'Arabe Anglais', 710850, 37000000, 'Dirham Marocain', 'a5a630e8092b9e1ad1cfd8f572ce7a7d.png'),
(27, 'Etat-Unis', 'Washington', 'Amerique', 'Anglais', 10000000, 332000000, 'Dollars ($)', '669f480305327362c1a8bd71ff0b9215.png');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `pays`
--
ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
