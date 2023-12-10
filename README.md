# Une application de messagerie instantanée

## Description

Cette application permet de discuter en temps réel avec d'autres utilisateurs.
Elle est basée sur des sockets en C et utilise le protocole TCP.

## Fonctionnalités

- Connexion à un serveur
- Envoi de messages (privés ou publics)
- Création et gestion des groupes de discussion
- Envoi de messages dans des groupes

## Pre-requis

- Système d'exploitation Linux (ou un autre système UNIX compatible avec les sockets en C)
- Compilateur C (gcc) installé

## Installation

1. Cloner le projet ou télécharger l'archive
2. Décompresser l'archive (si nécessaire)
3. Se placer dans le dossier du projet (cd chemin_vers_le_dossier_dm)
4. lancer le script `run.sh` (il faut être dans le dossier du projet et avoir les droits d'exécution sur le script). 
Exemple: 
- `chmod +x run.sh`
- `./run.sh 4` (pour lancer le serveur avec 4 clients).

## Utilisation

1. Lancer le script `run.sh` (voir Installation)
2. Suivre les instructions affichées dans le terminal (celui du serveur et ceux des clients)
3. Taper votre nom d'utilisateur (ou pseudo) dans le terminal du client
4. Utiliser la commande `/help` pour afficher la liste des commandes disponibles

## Commandes disponibles

- `/help` : Affiche la liste des commandes disponibles
- `/broadcast <message>` : Envoie un message à tous les utilisateurs connectés
- `/unicast <username> <message>` : Envoie un message à un utilisateur spécifique
- `/list` : Affiche la liste des utilisateurs connectés
- `/whoami` : Affiche votre nom d'utilisateur
- `/create <groupname>` : Crée un groupe de discussion
- `/join <groupname>` : Rejoint un groupe de discussion
- `/leave <groupname>` : Quitte un groupe de discussion
- `/sendMsg <groupname> <message>` : Envoie un message dans un groupe de discussion
- `/showMembers <groupname>` : Affiche la liste des membres d'un groupe de discussion
- `/showAllGroups` : Affiche la liste de tous les groupes de discussion existants
- `/clear` : Efface le contenu du terminal
- `/exit` : Quitte l'application

## Auteurs

- [DOUMBOUYA Sekou](https://github.com/sekou633/projetsAcademiques)
- [GUILAVOGUI David](gdaviddogbo@gmail.com)

## License

Ce projet est sous licence MIT

## Remarques

- Ce projet a été réalisé dans le cadre du cours de l'Ingéniérie de Réseaux à l'Université de Nantes (Master 1 MIAGE)
- Ce projet est à but éducatif et ne doit pas être utilisé dans un cadre professionnel
- Ce projet est en cours de développement et peut contenir des bugs
- Ce projet est open source et toute contribution est la bienvenue
- Ce projet est à but non lucratif

