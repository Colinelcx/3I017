 -- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mar 26 Février 2019 à 12:41
-- Version du serveur :  5.7.23
-- Version de PHP :  7.0.33-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `lacoux_felten`
--

-- --------------------------------------------------------

--
-- Structure de la table `Friendship`
--

CREATE TABLE `Friendship` (
  `id_user1` int(11) NOT NULL,
  `id_user2` int(11) NOT NULL,
  `date_connexion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Session`
--

CREATE TABLE `Session` (
  `id_user` int(11) NOT NULL,
  `key_session` varchar(64) NOT NULL,
  `date_session` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `root` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `user_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login_user` varchar(32) NOT NULL,
  `first_name_user` varchar(32) NOT NULL,
  `family_name_user` varchar(32) NOT NULL,
  `password_user` blob NOT NULL,
  `mail_user` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `User`
--

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Friendship`
--
ALTER TABLE `Friendship`
  ADD PRIMARY KEY (`id_user1`,`id_user2`),
  ADD KEY `user2_exist` (`id_user2`);

--
-- Index pour la table `Session`
--
ALTER TABLE `Session`
  ADD KEY `user_exist` (`id_user`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD UNIQUE KEY `login_user` (`login_user`),
  ADD UNIQUE KEY `mail_user` (`mail_user`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Friendship`
--
ALTER TABLE `Friendship`
  ADD CONSTRAINT `user1_exist` FOREIGN KEY (`id_user1`) REFERENCES `User` (`user_id`),
  ADD CONSTRAINT `user2_exist` FOREIGN KEY (`id_user2`) REFERENCES `User` (`user_id`);

--
-- Contraintes pour la table `Session`
--
ALTER TABLE `Session`
  ADD CONSTRAINT `user_exist` FOREIGN KEY (`id_user`) REFERENCES `User` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;