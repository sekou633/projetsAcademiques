#!/bin/bash

# Vérifiez si le nombre de clients est spécifié en entrée
if [ $# -ne 1 ]; then
    echo "Utilisation : $0 <nombre de clients>"
    exit 1
fi

# Nombre de clients à lancer
N=$1

# Nettoyez l'écran
clear

# Supprimez les fichiers compilés
rm -f client server 

# Compilez le code client
gcc client.c -o client -lpthread

# Compilez tous les fichiers serveur en utilisant une boucle

# gcc constantes.c -o constantes
gcc serverBody.c -c serverBody.o -lpthread
gcc server.c -c server.o -lpthread

gcc server.o serverBody.o -o server -lpthread

# Lancez le serveur dans un terminal différent
gnome-terminal -- bash -c "./server; read -p 'Appuyez sur la touche Entrée pour quitter ! '"
# gnome-terminal -- bash -c ./server

# Attendez un peu pour que le serveur puisse démarrer
sleep 5

# Boucle pour lancer chaque client dans un terminal différent
for ((i = 1; i <= N; i++)); do
    gnome-terminal -- bash -c ./client
done

rm -f serverBody.o server.o

# Les droits d'exécution au script actuel

chmod +x run.sh # ou chmod 777 run.sh ("$0" est le nom du script actuel, ici)

# Laissez le serveur et les clients s'exécuter
# Si vous souhaitez attendre que le serveur et les clients se terminent, vous pouvez ajouter ces lignes :
# wait
# echo "Serveur et clients terminés"
