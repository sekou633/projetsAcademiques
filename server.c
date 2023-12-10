#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>
#include <signal.h>    
#include <ctype.h>
#include <time.h> 
#include <sys/time.h>
#include <sys/types.h> 
#include <stdbool.h>

#include "serverHeader.h"

struct ChatRoom chat_rooms[MAX_CHAT_ROOMS];
int chat_room_count = 0;

struct ClientInfo clients[MAX_CLIENTS];
int client_count = 0;

// Mutex pour la gestion de la liste des clients
pthread_mutex_t clients_mutex = PTHREAD_MUTEX_INITIALIZER;
// Mutex pour la gestion de la liste des groupes créés par les clients
pthread_mutex_t chat_rooms_mutex = PTHREAD_MUTEX_INITIALIZER;


//================================================================================
//================================= MAIN PROGRAM =================================
//================================================================================

/**
 * @description: Fonction principale du serveur
 * @author <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a>
 * @author <a href="mailto:gdaviddogbo@gmail.com"> David Guilavogui </a>
 * @return int 0 si le serveur s'est terminé avec succès, 1 sinon
 */
int main() {

    signal(SIGINT, handle_server_shutdown);

    time_t start_time;
    time(&start_time);
    printf("================================= BIENVENUE DANS NOTRE SERVEUR DE MESSAGERIE ==========================\n");
    printf("\n");
    printf("================================ Serveur Lancé à %s", ctime(&start_time));
    printf("\n");
    printf("================================ POUR QUITTER LE SERVEUR TAPEZ CTRL + C ===============================\n");

    pthread_mutex_init(&clients_mutex, NULL);
    pthread_mutex_init(&chat_rooms_mutex, NULL);

    int server_socket;
    struct sockaddr_in server_addr;
    socklen_t addr_size = sizeof(server_addr);
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket < 0) {
        perror("Erreur lors de la création du socket");
        exit(1);
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(PORT);
    server_addr.sin_addr.s_addr = INADDR_ANY; // Utilisez l'adresse IP de la machine locale

    if (bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
        perror("Erreur lors de la liaison du socket");
        exit(1);
    }

    if (listen(server_socket, 10) == 0) {

        printf("\n");
        printf("========================== En attente de connexions... =======================\n");
        printf("\n");

    } else {
        printf("Erreur lors de l'écoute des connexions\n");
    }

    while (1) // Boucle infinie pour accepter les connexions 
    {
        int new_socket = accept(server_socket, (struct sockaddr*)&server_addr, &addr_size);
        pthread_t tid;
        pthread_create(&tid, NULL, handleClient, &new_socket);
    }

    close(server_socket);

    return 0;
}

//================================================================================
//================================= END OF FILE ==================================
//================================================================================
